package pizzaNation.app.service.contract;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.user.model.request.ContactUsRequestModel;

import java.security.Principal;

/**
 * Created by George-Lenovo on 30/03/2018.
 */
public interface IContactService {

    boolean sendMessage(ContactUsRequestModel requestModel, BindingResult bindingResult, RedirectAttributes attributes);

    ContactUsRequestModel constructModel(Principal principal);
}
