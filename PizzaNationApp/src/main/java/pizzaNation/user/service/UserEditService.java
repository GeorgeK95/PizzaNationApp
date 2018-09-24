package pizzaNation.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.app.exception.AdminModifyException;
import pizzaNation.app.exception.UserNotFoundException;
import pizzaNation.app.model.request.EditDetailsRequestModel;
import pizzaNation.app.model.request.EditSignInRequestModel;
import pizzaNation.user.model.entity.Role;
import pizzaNation.user.model.entity.User;
import pizzaNation.user.model.request.EditUserRequestModel;
import pizzaNation.user.repository.RoleRepository;
import pizzaNation.user.repository.UserRepository;
import pizzaNation.user.service.api.IUserEditService;

import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static pizzaNation.app.util.WebConstants.*;

@Service
@Transactional
public class UserEditService extends BaseService implements IUserEditService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    private final RoleRepository roleRepository;

    @Autowired
    public UserEditService(UserRepository userRepository, BCryptPasswordEncoder encoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }


    @Override
    public boolean editUser(String id, EditUserRequestModel requestModel, BindingResult bindingResult, RedirectAttributes attributes) {
        if (super.containErrors(bindingResult, attributes, USER_EDIT_ERROR)) return false;

        Optional<User> toEdit = this.userRepository.findById(id);

        if (!toEdit.isPresent()) throw new UserNotFoundException();

        User user = toEdit.get();

        if (user.getEmail().equals(ADMIN_EMAIL)) throw new AdminModifyException();

        this.editAndSave(user, requestModel);

        return true;
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

    private void editAndSave(User user, EditUserRequestModel requestModel) {
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

    private HashSet<Role> provideAuthorities(boolean isModerator) {
        HashSet<Role> roles = new HashSet<>();
        roles.add(this.roleRepository.findByAuthority(ROLE_USER));
        if (isModerator) roles.add(this.roleRepository.findByAuthority(ROLE_MODERATOR));
        return roles;
    }
}
