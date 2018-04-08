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
import pizzaNation.app.exception.AdminModifyException;
import pizzaNation.app.exception.UserNotFoundException;
import pizzaNation.app.model.view.UserViewModel;
import pizzaNation.user.model.entity.Role;
import pizzaNation.user.model.entity.User;
import pizzaNation.user.model.request.EditUserRequestModel;
import pizzaNation.user.model.request.UserRegisterRequestModel;
import pizzaNation.user.repository.RoleRepository;
import pizzaNation.user.repository.UserRepository;
import pizzaNation.app.util.DTOConverter;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(email);

        if (user == null) {
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

        return this.persistUser(requestModel);
    }

    private boolean validateRequest(BindingResult bindingResult, RedirectAttributes attributes,
                                    UserRegisterRequestModel requestModel) {
        if (super.containErrors(bindingResult, attributes, USER_REGISTER_ERROR)) return false;

        if (this.passwordsMismatch(requestModel, attributes)) return false;

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

        return false;
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
    public boolean deleteUser(String id) {
        User user = DTOConverter.convert(this.findById(id), User.class);

        if (user == null) throw new UserNotFoundException();

        if (user.getEmail().equals(ADMIN_EMAIL)) throw new AdminModifyException();

        this.userRepository.delete(user);

        return true;
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

    private boolean persistUser(UserRegisterRequestModel requestModel) {
        User user = DTOConverter.convert(requestModel, User.class);

        user.addRole(this.roleRepository.findByAuthority(ROLE_USER));

        this.userRepository.saveAndFlush(user);

        return true;
    }

    private boolean passwordsMismatch(UserRegisterRequestModel requestModel, RedirectAttributes attributes) {
        if (!requestModel.getPassword().equals(requestModel.getConfirmPassword())) {
            attributes.addFlashAttribute(USER_REGISTER_ERROR, PASSWORD_MISMATCH_MESSAGE);
            return true;
        }

        return false;
    }
}
