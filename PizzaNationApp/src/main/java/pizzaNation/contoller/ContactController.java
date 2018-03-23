package pizzaNation.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pizzaNation.model.request.ContactUsRequestModel;

import javax.validation.Valid;

import java.util.Map;

import static java.util.Map.entry;

/**
 * Created by George-Lenovo on 14/03/2018.
 */
@Controller
public class ContactController extends BaseController {

    @GetMapping("/contactUs")
    public ModelAndView contactUs() {
        return super.view(null,Map.ofEntries(entry("pageTitle", "Contact us")));
    }

    @PostMapping("/contactUs")
    public ModelAndView contactUsProcess(@Valid @ModelAttribute ContactUsRequestModel bindingModel, BindingResult bindingResult) {
        return new ModelAndView();
    }
}
