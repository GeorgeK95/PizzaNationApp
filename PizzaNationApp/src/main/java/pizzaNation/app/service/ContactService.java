package pizzaNation.app.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.app.service.api.IContactService;
import pizzaNation.app.util.DTOConverter;
import pizzaNation.user.model.entity.User;
import pizzaNation.user.model.request.ContactUsRequestModel;
import pizzaNation.user.service.BaseService;
import pizzaNation.user.service.api.IUserService;

import java.security.Principal;

import static pizzaNation.app.util.WebConstants.*;

@Service
@Transactional
public class ContactService extends BaseService implements IContactService {

    private final IUserService userService;

    private final JmsTemplate jmsTemplate;

    @Autowired
    public ContactService(IUserService userService, JmsTemplate jmsTemplate) {
        this.userService = userService;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public boolean sendMessage(ContactUsRequestModel requestModel, BindingResult bindingResult, RedirectAttributes attributes) {
        attributes.addFlashAttribute(MODEL_STR, requestModel);

        if (super.containErrors(bindingResult, attributes, CONTACT_FORM_ERROR)) return false;

        this.sendEmail(requestModel);

        attributes.addFlashAttribute(CONTACT_FORM_SUCCESS, SUCCESSFULLY_SENT_FEEDBACK_MESSAGE);

        return true;
    }

    private void sendEmail(ContactUsRequestModel requestModel) {
        jmsTemplate.convertAndSend(SEND_EMAIL_DESTINATION, new Gson().toJson(requestModel));
    }

    @Override
    public ContactUsRequestModel constructModel(Principal principal) {
        if (principal == null) return new ContactUsRequestModel();

        User user = this.userService.findUserByEmail(principal.getName());

        return DTOConverter.convert(user, ContactUsRequestModel.class);
    }
}
