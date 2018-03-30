package pizzaNation.app.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pizzaNation.app.service.IContactService;
import pizzaNation.user.model.request.ContactUsRequestModel;

import javax.validation.Valid;

import java.security.Principal;
import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.CONTACT_US_PAGE_TITLE;
import static pizzaNation.app.util.WebConstants.CONTACT_US_URL;
import static pizzaNation.app.util.WebConstants.PAGE_TITLE_STR;

/**
 * Created by George-Lenovo on 14/03/2018.
 */
@Controller
public class ContactController extends BaseController {

    private final IContactService contactService;

    @Autowired
    public ContactController(IContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping(CONTACT_US_URL)
    public ModelAndView contactUs(@ModelAttribute ContactUsRequestModel requestModel, Principal principal) {
        ContactUsRequestModel model = this.contactService.constructModel(principal);

        return super.view(model, Map.ofEntries(entry(PAGE_TITLE_STR, CONTACT_US_PAGE_TITLE)));
    }

    @PostMapping(CONTACT_US_URL)
    public ModelAndView contactUsProcess(@Valid @ModelAttribute ContactUsRequestModel requestModel,
                                         BindingResult bindingResult) {
        this.contactService.sendMessage(requestModel, bindingResult);

        return super.redirect("/");
    }
}
