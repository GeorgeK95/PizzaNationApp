package pizzaNation.user.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.app.config.PizzaNationSecurityConfiguration;
import pizzaNation.app.exception.*;
import pizzaNation.app.model.request.EditDetailsRequestModel;
import pizzaNation.app.model.request.EditSignInRequestModel;
import pizzaNation.app.model.transfer.EmailVerification;
import pizzaNation.app.model.view.UserViewModel;
import pizzaNation.app.repository.LoggerRepository;
import pizzaNation.user.model.entity.Role;
import pizzaNation.user.model.entity.User;
import pizzaNation.user.model.request.EditUserRequestModel;
import pizzaNation.user.model.request.UserRegisterRequestModel;
import pizzaNation.user.repository.RoleRepository;
import pizzaNation.user.repository.UserRepository;
import pizzaNation.app.util.DTOConverter;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

import static pizzaNation.app.util.WebConstants.*;

/**
 * Created by George-Lenovo on 27/03/2018.
 */
@Service
@Transactional
public class UserService extends BaseService implements IUserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final LoggerRepository loggerRepository;

    private final BCryptPasswordEncoder encoder;

    private final JmsTemplate jmsTemplate;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, LoggerRepository loggerRepository, BCryptPasswordEncoder encoder, JmsTemplate jmsTemplate) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.loggerRepository = loggerRepository;
        this.encoder = encoder;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = this.userRepository.findByEmail(email);

        if (user == null || !user.isEnabled()) {
            throw new UsernameNotFoundException(WRONG_LOGIN_DATA_MESSAGE);
        } else {
            Set<GrantedAuthority> grantedAuthorities = user.getAuthorities()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                    .collect(Collectors.toSet());

            return new org
                    .springframework
                    .security
                    .core
                    .userdetails
                    .User(user.getEmail(), user.getPassword(), grantedAuthorities);
        }
    }

    @Override
    public boolean addUser(UserRegisterRequestModel requestModel, BindingResult bindingResult, RedirectAttributes attributes) {
        attributes.addFlashAttribute(USER_REGISTER_REQUEST_MODEL, requestModel);

        if (!this.validateRequest(bindingResult, attributes, requestModel)) return false;

        return this.tryPersistUser(requestModel, attributes);
    }

    private boolean validateRequest(BindingResult bindingResult, RedirectAttributes attributes,
                                    UserRegisterRequestModel requestModel) {
        if (super.containErrors(bindingResult, attributes, USER_REGISTER_ERROR)) return false;

        if (super.passwordsMismatch(requestModel, attributes)) return false;

        attributes.addFlashAttribute(USER_REGISTER_SUCCESS_MESSAGE, REGISTERED_SUCCESSFULLY_MESSAGE);

        return true;
    }

    @Override
    public User findUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public List<UserViewModel> findAll() {
        return DTOConverter.convert(this.userRepository.findAllExceptAdmin(), UserViewModel.class);
    }

    @Override
    public boolean editUser(String id, EditUserRequestModel requestModel, BindingResult bindingResult, RedirectAttributes attributes) {
        if (super.containErrors(bindingResult, attributes, USER_EDIT_ERROR)) return false;

        Optional<User> toEdit = this.userRepository.findById(id);

        if (!toEdit.isPresent()) throw new UserNotFoundException();

        User user = toEdit.get();

        if (user.getEmail().equals(ADMIN_EMAIL)) throw new AdminModifyException();

        this.editAndSave(user, requestModel, bindingResult, attributes);

        return true;
    }

    @Override
    public EditUserRequestModel findById(String id) {
        Optional<User> optionalUser = this.userRepository.findById(id);

        if (!optionalUser.isPresent()) throw new UserNotFoundException();

        User user = optionalUser.get();

        if (user.getEmail().equals(ADMIN_EMAIL)) throw new AdminModifyException();

        EditUserRequestModel requestModel = DTOConverter.convert(optionalUser.get(), EditUserRequestModel.class);

        if (user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet())
                .contains(ROLE_MODERATOR))
            requestModel.setModerator(true);

        return requestModel;
    }

    @Override
    public boolean disableUser(String id) {
        Optional<User> byId = this.userRepository.findById(id);

        if (!byId.isPresent()) throw new UserNotFoundException();

        User user = byId.get();

        if (user.getEmail().equals(ADMIN_EMAIL)) throw new AdminModifyException();

        user.setEnabled(false);

        this.userRepository.saveAndFlush(user);

        return true;
    }

    @Override
    public EditSignInRequestModel constructEditEmailModel(Principal principal) {
        return DTOConverter.convert(this.userRepository.findByEmail(principal.getName()), EditSignInRequestModel.class);
    }

    @Override
    public EditDetailsRequestModel constructEditDetailsModel() {
        String email = PizzaNationSecurityConfiguration.getCurrentlyLoggedInUserEmail();
        return DTOConverter.convert(this.userRepository.findByEmail(email), EditDetailsRequestModel.class);
    }

    @Override
    public boolean editSignInInfo(EditSignInRequestModel editSignInRequestModel, RedirectAttributes attributes, BindingResult result,
                                  Principal principal) {
        if (super.containErrors(result, attributes, USER_EDIT_ERROR)) return false;

        if (super.passwordsMismatch(editSignInRequestModel, attributes)) return false;

        if (this.userRepository.existsByEmail(editSignInRequestModel.getEmail())) {
            attributes.addFlashAttribute(USER_EDIT_ERROR, EMAIL_ALREADY_TAKEN_MESSAGE);
            return false;
        }

        User userToEdit = this.userRepository.findByEmail(principal.getName());

        if (!this.encoder.matches(editSignInRequestModel.getCurrentPassword(), userToEdit.getPassword())) {
            attributes.addFlashAttribute(USER_EDIT_ERROR, INVALID_CURRENT_PASSWORD_MESSAGE);
            return false;
        }

        userToEdit.setPassword(this.encoder.encode(editSignInRequestModel.getPassword()));
        userToEdit.setEmail(editSignInRequestModel.getEmail());

        this.userRepository.saveAndFlush(userToEdit);

        attributes.addFlashAttribute(USER_EDIT_SUCCESS, CHANGES_MADE_SUCCESSFULLY);

        return true;
    }

    @Override
    public boolean editUserDetails(String currentEmail, EditDetailsRequestModel requestModel, RedirectAttributes attributes,
                                   BindingResult result) {
        Optional<User> toEdit = this.userRepository.findById(this.userRepository.findByEmail(currentEmail).getId());

        if (!toEdit.isPresent()) throw new UserNotFoundException();

        if (super.containErrors(result, attributes, USER_EDIT_ERROR)) return false;

        this.userRepository.saveAndFlush(this.editUser(requestModel, toEdit));

        attributes.addFlashAttribute(USER_EDIT_SUCCESS, CHANGES_MADE_SUCCESSFULLY);

        return true;
    }

    @Override
    public boolean confirmAccount(String code, RedirectAttributes attributes) {
        User accToConfirm = this.userRepository.findByEmailVerificationCode(code);

        if (accToConfirm == null) throw new ConfirmCodeNotFoundException();

        accToConfirm.setEnabled(true);

        accToConfirm.setEmailVerificationCode(UUID.randomUUID().toString());

        attributes.addFlashAttribute(ACCOUNT_CONFIRMED_STR, ACCOUNT_CONFIRMED_SUCCESSFULLY_MESSAGE);

        return true;
    }

    /*@Override
    public List<UserViewModel> findAllSubscribed() {
        return DTOConverter.convert(this.userRepository.findAllSubscribed(), UserViewModel.class);
    }*/

    private User editUser(EditDetailsRequestModel requestModel, Optional<User> toEdit) {
        User user = toEdit.get();

        user.setFirstName(requestModel.getFirstName());
        user.setLastName(requestModel.getLastName());
        user.setGender(requestModel.getGender());
        user.setGender(requestModel.getGender());
        user.setPhone(requestModel.getPhone());
        user.setEmailNewsletters(requestModel.getEmailNewsletters());

        return user;
    }

    private void editAndSave(User user, EditUserRequestModel requestModel, BindingResult bindingResult, RedirectAttributes attributes) {
        user.setFirstName(requestModel.getFirstName());
        user.setLastName(requestModel.getLastName());
        user.setAddress(requestModel.getAddress());
        user.setCity(requestModel.getCity());
        user.setGender(requestModel.getGender());
        user.setPhone(requestModel.getPhone());
        user.setEmailNewsletters(requestModel.getEmailNewsletters());
        Set<Role> roles = this.provideAuthorities(requestModel.getModerator());
        user.setAuthorities(roles);

        this.userRepository.saveAndFlush(user);
    }

    private HashSet<Role> provideAuthorities(boolean isModerator) {
        HashSet<Role> roles = new HashSet<>();
        roles.add(this.roleRepository.findByAuthority(ROLE_USER));
        if (isModerator) roles.add(this.roleRepository.findByAuthority(ROLE_MODERATOR));
        return roles;
    }

    private boolean tryPersistUser(UserRegisterRequestModel requestModel, RedirectAttributes attributes) {
        User user = DTOConverter.convert(requestModel, User.class);

        if (this.userRepository.findByEmail(user.getEmail()) != null) {
            attributes.addFlashAttribute(USER_REGISTER_ERROR, EMAIL_ALREADY_TAKEN_MESSAGE);
            return false;
        }

        user.addRole(this.roleRepository.findByAuthority(ROLE_USER));

        this.userRepository.saveAndFlush(user);

        this.sendConfirmEmail(requestModel.getEmail(), user.getEmailVerificationCode());

        return true;
    }

    private void sendConfirmEmail(String email, String code) {
        new Thread(() -> jmsTemplate.convertAndSend(USER_ARRIVED_DESTINATION,
                new Gson().toJson(new EmailVerification(email, String.format(VERIFICATION_MESSAGE, code))))
        ).start();
    }

}
