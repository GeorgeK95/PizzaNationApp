package pizzaNation.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.app.util.DTOConverter;
import pizzaNation.user.model.entity.User;
import pizzaNation.user.model.request.ContactUsRequestModel;
import pizzaNation.user.service.BaseService;
import pizzaNation.user.service.IUserService;

import javax.validation.constraints.Null;
import java.security.Principal;

import static pizzaNation.app.util.WebConstants.*;

/**
 * Created by George-Lenovo on 30/03/2018.
 */
@Service
@Transactional
public class ContactService extends BaseService implements IContactService {

    private final IUserService userService;

    @Autowired
    public ContactService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean sendMessage(ContactUsRequestModel requestModel, BindingResult bindingResult, RedirectAttributes attributes) {
        attributes.addFlashAttribute(CONTACT_REQUEST_MODEL, requestModel);

        if (super.containErrors(bindingResult, attributes, CONTACT_FORM_ERROR)) return false;

        this.sendEmail(requestModel); //asynch

        return true;
    }

    private void sendEmail(ContactUsRequestModel requestModel) {
        //TODO
        System.out.println();
    }

    @Override
    public ContactUsRequestModel constructModel(Principal principal) {
        if (principal == null) return new ContactUsRequestModel();

        User user = this.userService.findUserByEmail(principal.getName());

        return DTOConverter.convert(user, ContactUsRequestModel.class);
    }
}
