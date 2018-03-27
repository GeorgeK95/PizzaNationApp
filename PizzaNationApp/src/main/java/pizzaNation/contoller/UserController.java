package pizzaNation.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.model.request.UserRegisterRequestModel;
import pizzaNation.service.IUserService;

import javax.validation.Valid;
import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.util.WebConstants.*;

/**
 * Created by George-Lenovo on 15/03/2018.
 */
@Controller
public class UserController extends BaseController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(REGISTER_URL)
    public ModelAndView register(UserRegisterRequestModel requestModel) {
        return super.view(requestModel, Map.ofEntries(entry(PAGE_TITLE_STR, REGISTER_PAGE_TITLE)));
    }

    @PostMapping(REGISTER_URL)
    public ModelAndView registerProcess(@ModelAttribute @Valid UserRegisterRequestModel requestModel, BindingResult bindingResult,
                                        RedirectAttributes attributes) {
        if (!this.userService.addUser(requestModel, bindingResult, attributes)) return super.redirect(REGISTER_URL);
        return super.redirect(LOGIN_URL);
    }

    @GetMapping(LOGIN_URL)
    public ModelAndView login() {
        return super.view(null, Map.ofEntries(entry(PAGE_TITLE_STR, LOG_IN_PAGE_TITLE)));
    }
}
