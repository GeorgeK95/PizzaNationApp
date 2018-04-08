package pizzaNation.app.contoller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.*;

/**
 * Created by George-Lenovo on 06/04/2018.
 */
@Controller
public class AccountController extends BaseController {

    @PreAuthorize("isAuthenticated()")
    @GetMapping(ACCOUNT_URL)
    public ModelAndView account() {
        return super.view(null, Map.ofEntries(entry(PAGE_TITLE_STR, MY_PIZZA_NATION)));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(ACCOUNT_SETTINGS_URL)
    public ModelAndView settings() {
        return super.view(null, Map.ofEntries(entry(PAGE_TITLE_STR, MY_ACCOUNT_PAGE_TITLE)));
    }
}
