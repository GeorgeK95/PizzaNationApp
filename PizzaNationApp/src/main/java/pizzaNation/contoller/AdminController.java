package pizzaNation.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.util.WebConstants.ADMIN_PANEL_PAGE_TITLE;
import static pizzaNation.util.WebConstants.ADMIN_URL;
import static pizzaNation.util.WebConstants.PAGE_TITLE_STR;

/**
 * Created by George-Lenovo on 25/03/2018.
 */
@Controller
public class AdminController extends BaseController {

    @GetMapping(ADMIN_URL)
    public ModelAndView admin() {
        return super.view(null, Map.ofEntries(entry(PAGE_TITLE_STR, ADMIN_PANEL_PAGE_TITLE)));
    }
}
