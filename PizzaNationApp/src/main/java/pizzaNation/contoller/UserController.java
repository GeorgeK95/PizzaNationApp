package pizzaNation.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static java.util.Map.entry;

/**
 * Created by George-Lenovo on 15/03/2018.
 */
@Controller
public class UserController extends BaseController {

    @GetMapping("/register")
    public ModelAndView register() {
        return super.view(null, Map.ofEntries(entry("pageTitle", "Register")));
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return super.view(null, Map.ofEntries(entry("pageTitle", "Log In")));
    }
}
