package pizzaNation.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.util.WebConstants.*;

/**
 * Created by George-Lenovo on 15/03/2018.
 */
@Controller
public class UserController extends BaseController {

    @GetMapping(REGISTER_URL)
    public ModelAndView register() {
        return super.view(null, Map.ofEntries(entry(PAGE_TITLE_STR, REGISTER_PAGE_TITLE)));
    }

    @GetMapping(LOGIN_URL)
    public ModelAndView login() {
        return super.view(null, Map.ofEntries(entry(PAGE_TITLE_STR, LOG_IN_PAGE_TITLE)));
    }
}
