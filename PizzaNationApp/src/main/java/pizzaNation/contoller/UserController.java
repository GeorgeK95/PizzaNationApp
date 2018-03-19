package pizzaNation.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pizzaNation.model.request.LoginRequestModel;

import javax.validation.Valid;

import java.util.Map;

import static java.util.Map.entry;

/**
 * Created by George-Lenovo on 15/03/2018.
 */
@Controller
public class UserController extends BaseController {

    @GetMapping("/register")
    public ModelAndView register() {
        return super.constructStaticModelAndViewResponse(Map.ofEntries(
                entry("layout", "base-layout"),
                entry("view", "user/register"),
                entry("pageTitle", "Register")
                )
        );
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return super.constructStaticModelAndViewResponse(Map.ofEntries(
                entry("layout", "base-layout"),
                entry("view", "user/login"),
                entry("pageTitle", "Log In")
                )
        );
    }

    @PostMapping("/login")
    public ModelAndView loginProcess(@ModelAttribute @Valid LoginRequestModel bindingModel, BindingResult result) {
        return new ModelAndView("redirect:/menu");
    }
}
