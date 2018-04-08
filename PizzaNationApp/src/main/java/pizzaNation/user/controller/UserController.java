package pizzaNation.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.app.contoller.BaseController;
import pizzaNation.user.enumeration.Gender;
import pizzaNation.user.model.request.UserRegisterRequestModel;
import pizzaNation.user.service.IUserService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.*;

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

    @ModelAttribute(name = GENDERS_LIST)
    public List<String> getCreators() {
        return Arrays.stream(Gender.values()).map(Enum::toString).collect(Collectors.toList());
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping(REGISTER_URL)
    public ModelAndView register(UserRegisterRequestModel requestModel) {
        return super.view(requestModel, Map.ofEntries(entry(PAGE_TITLE_STR, REGISTER_PAGE_TITLE)));
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping(REGISTER_URL)
    public ModelAndView registerProcess(@ModelAttribute @Valid UserRegisterRequestModel requestModel, BindingResult bindingResult,
                                        RedirectAttributes attributes) {
        if (!this.userService.addUser(requestModel, bindingResult, attributes)) return super.redirect(REGISTER_URL);
        return super.redirect(LOGIN_URL);
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping(LOGIN_URL)
    public ModelAndView login() {
        return super.view(null, Map.ofEntries(entry(PAGE_TITLE_STR, LOG_IN_PAGE_TITLE)));
    }
}
