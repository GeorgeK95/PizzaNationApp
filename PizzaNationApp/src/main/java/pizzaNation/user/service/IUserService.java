package pizzaNation.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.app.model.request.EditDetailsRequestModel;
import pizzaNation.app.model.request.EditSignInRequestModel;
import pizzaNation.app.model.view.UserViewModel;
import pizzaNation.user.model.entity.User;
import pizzaNation.user.model.request.EditUserRequestModel;
import pizzaNation.user.model.request.UserRegisterRequestModel;

import java.security.Principal;
import java.util.List;

/**
 * Created by George-Lenovo on 27/03/2018.
 */
public interface IUserService extends UserDetailsService {
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;

    boolean addUser(UserRegisterRequestModel requestModel, BindingResult bindingResult, RedirectAttributes attributes);

    User findUserByEmail(String email);

    List<UserViewModel> findAll();

    boolean editUser(String id, EditUserRequestModel requestModel, BindingResult bindingResult, RedirectAttributes attributes);

    EditUserRequestModel findById(String id);

    boolean deleteUser(String id);

    EditSignInRequestModel constructEditEmailModel(Principal principal);

    EditDetailsRequestModel constructEditDetailsModel();

    boolean editSignInInfo(EditSignInRequestModel editSignInRequestModel, RedirectAttributes attributes, BindingResult result, Principal principal);

    boolean editUserDetails(String currentEmail, EditDetailsRequestModel requestModel, RedirectAttributes attributes, BindingResult result);
}
