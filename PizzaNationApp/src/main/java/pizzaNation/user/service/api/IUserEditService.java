package pizzaNation.user.service.api;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.app.model.request.EditDetailsRequestModel;
import pizzaNation.app.model.request.EditSignInRequestModel;
import pizzaNation.user.model.request.EditUserRequestModel;

import java.security.Principal;

public interface IUserEditService {
    boolean editUser(String id, EditUserRequestModel requestModel, BindingResult bindingResult, RedirectAttributes attributes);

    boolean editSignInInfo(EditSignInRequestModel editSignInRequestModel, RedirectAttributes attributes, BindingResult result, Principal principal);

    boolean editUserDetails(String currentEmail, EditDetailsRequestModel requestModel, RedirectAttributes attributes, BindingResult result);
}
