package pizzaNation.service;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.model.request.UserRegisterRequestModel;

/**
 * Created by George-Lenovo on 27/03/2018.
 */
public interface IUserService {

    boolean addUser(UserRegisterRequestModel requestModel, BindingResult bindingResult, RedirectAttributes attributes);
}
