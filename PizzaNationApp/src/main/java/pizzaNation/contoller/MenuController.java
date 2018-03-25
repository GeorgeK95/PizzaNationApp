package pizzaNation.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.util.WebConstants.MENU_PAGE_TITLE;
import static pizzaNation.util.WebConstants.MENU_URL;
import static pizzaNation.util.WebConstants.PAGE_TITLE_STR;

/**
 * Created by George-Lenovo on 15/03/2018.
 */
@Controller
public class MenuController extends BaseController {

    @GetMapping(MENU_URL)
    public ModelAndView menu() {
        return super.view(null,Map.ofEntries(entry(PAGE_TITLE_STR, MENU_PAGE_TITLE)));
    }

}
