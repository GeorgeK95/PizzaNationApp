package pizzaNation.user.service.api;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
import java.util.Set;

public interface IUserService extends UserDetailsService {
    UserDetails loadUserByUsername(String s);

    boolean addUser(UserRegisterRequestModel requestModel, BindingResult bindingResult, RedirectAttributes attributes);

    User findUserByEmail(String email);

    List<UserViewModel> findAll();

    EditUserRequestModel findById(String id);

    EditSignInRequestModel constructEditEmailModel(Principal principal);

    EditDetailsRequestModel constructEditDetailsModel();

    boolean confirmAccount(String token, RedirectAttributes attributes);

    Set<String> getSubscribersEmails();
}
