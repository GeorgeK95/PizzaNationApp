package pizzaNation.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import pizzaNation.app.util.DTOConverter;
import pizzaNation.user.model.entity.User;
import pizzaNation.user.model.request.ContactUsRequestModel;
import pizzaNation.user.service.IUserService;

import javax.validation.constraints.Null;
import java.security.Principal;

/**
 * Created by George-Lenovo on 30/03/2018.
 */
@Service
@Transactional
public class ContactService implements IContactService {

    private final IUserService userService;

    @Autowired
    public ContactService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void sendMessage(ContactUsRequestModel requestModel, BindingResult bindingResult) {
        System.out.println();
    }

    @Override
    public ContactUsRequestModel constructModel(Principal principal) {
        if (principal == null) return null;

        User u = this.userService.findUserByEmail(principal.getName());

        ContactUsRequestModel convert = DTOConverter.convert(u, ContactUsRequestModel.class);

        return convert;
    }
}
