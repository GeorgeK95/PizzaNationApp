package pizzaNation.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.app.config.PizzaNationSecurityConfiguration;
import pizzaNation.app.exception.AdminModifyException;
import pizzaNation.app.exception.ConfirmCodeNotFoundException;
import pizzaNation.app.exception.UserNotFoundException;
import pizzaNation.app.model.request.EditDetailsRequestModel;
import pizzaNation.app.model.request.EditSignInRequestModel;
import pizzaNation.app.model.view.UserViewModel;
import pizzaNation.app.util.DTOConverter;
import pizzaNation.email.factory.EmailFactory;
import pizzaNation.email.model.EmailVerification;
import pizzaNation.email.service.api.IEmailService;
import pizzaNation.user.model.entity.User;
import pizzaNation.user.model.request.EditUserRequestModel;
import pizzaNation.user.model.request.UserRegisterRequestModel;
import pizzaNation.user.repository.RoleRepository;
import pizzaNation.user.repository.UserRepository;
import pizzaNation.user.service.api.IUserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static pizzaNation.app.util.WebConstants.*;

@Service
@Transactional
public class UserService extends BaseService implements IUserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final IEmailService emailService;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, IEmailService emailService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.emailService = emailService;
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

    @Override
    public User findUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public List<UserViewModel> findAll() {
        return DTOConverter.convert(this.userRepository.findAllExceptAdmin(), UserViewModel.class);
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
    public EditSignInRequestModel constructEditEmailModel(Principal principal) {
        return DTOConverter.convert(this.userRepository.findByEmail(principal.getName()), EditSignInRequestModel.class);
    }

    @Override
    public EditDetailsRequestModel constructEditDetailsModel() {
        String email = PizzaNationSecurityConfiguration.getCurrentlyLoggedInUserEmail();
        return DTOConverter.convert(this.userRepository.findByEmail(email), EditDetailsRequestModel.class);
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

    @Override
    public Set<String> getSubscribersEmails() {
        return this.userRepository.findAllSubscribed().stream().map(User::getEmail).collect(Collectors.toSet());
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
        EmailVerification emailVerification = EmailFactory.generateEmailVerificationObject(email, code);
        this.emailService.sendSimpleMessage(emailVerification);
    }

    private boolean validateRequest(BindingResult bindingResult, RedirectAttributes attributes,
                                    UserRegisterRequestModel requestModel) {
        if (super.containErrors(bindingResult, attributes, USER_REGISTER_ERROR)) return false;

        if (super.passwordsMismatch(requestModel, attributes)) return false;

        attributes.addFlashAttribute(USER_REGISTER_SUCCESS_MESSAGE, REGISTERED_SUCCESSFULLY_MESSAGE);

        return true;
    }

}
