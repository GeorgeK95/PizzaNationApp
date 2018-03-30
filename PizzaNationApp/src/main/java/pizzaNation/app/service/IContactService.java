package pizzaNation.app.service;

import org.springframework.validation.BindingResult;
import pizzaNation.user.model.request.ContactUsRequestModel;

import java.security.Principal;

/**
 * Created by George-Lenovo on 30/03/2018.
 */
public interface IContactService {

    void sendMessage(ContactUsRequestModel requestModel, BindingResult bindingResult);

    ContactUsRequestModel constructModel(Principal principal);
}