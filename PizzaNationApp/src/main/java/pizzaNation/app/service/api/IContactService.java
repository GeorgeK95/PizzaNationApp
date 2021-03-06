package pizzaNation.app.service.api;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.user.model.request.ContactUsRequestModel;

import java.security.Principal;

public interface IContactService {

    boolean sendMessage(ContactUsRequestModel requestModel, BindingResult bindingResult, RedirectAttributes attributes);

    ContactUsRequestModel constructModel(Principal principal);
}
