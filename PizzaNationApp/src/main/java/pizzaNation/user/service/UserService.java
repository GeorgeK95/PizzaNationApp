package pizzaNation.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.user.model.entity.User;
import pizzaNation.user.model.request.UserRegisterRequestModel;
import pizzaNation.user.repository.RoleRepository;
import pizzaNation.user.repository.UserRepository;
import pizzaNation.app.util.DTOConverter;

import java.util.Set;
import java.util.stream.Collectors;

import static pizzaNation.app.util.WebConstants.*;

/**
 * Created by George-Lenovo on 27/03/2018.
 */
@Service
@Transactional
public class UserService implements IUserService {

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

        if (this.containErrors(bindingResult, attributes)) return false;

        if (this.passwordsMismatch(requestModel, attributes)) return false;

        return this.persistUser(requestModel);
    }

    @Override
    public User findUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    private boolean persistUser(UserRegisterRequestModel requestModel) {
        User user = DTOConverter.convert(requestModel, User.class);

        user.addRole(this.roleRepository.findByAuthority(ROLE_USER));

        this.userRepository.saveAndFlush(user);

        return true;
    }

    private boolean containErrors(BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            ObjectError error = bindingResult.getAllErrors().get(0);
            String errorMessage = this.getDefaultMessage(error.getDefaultMessage());
            attributes.addFlashAttribute(USER_REGISTER_ERROR, errorMessage);
            return true;
        }

        return false;
    }

    private String getDefaultMessage(String message) {
        if (message.equals(MUST_NOT_BE_BLANK_MESSAGE) || message.equals(MUST_NOT_BE_NULL_MESSAGE)) {
            message = COMPLETE_ALL_FIELDS_MESSAGE;
        }
        return message;
    }

    private boolean passwordsMismatch(UserRegisterRequestModel requestModel, RedirectAttributes attributes) {
        if (!requestModel.getPassword().equals(requestModel.getConfirmPassword())) {
            attributes.addFlashAttribute(USER_REGISTER_ERROR, PASSWORD_MISMATCH_MESSAGE);
            return true;
        }

        return false;
    }
}
