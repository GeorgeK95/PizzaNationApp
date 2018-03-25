package pizzaNation.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.util.WebConstants.PAGE_TITLE_STR;
import static pizzaNation.util.WebConstants.PIZZA_NATION_PAGE_TITLE;
import static pizzaNation.util.WebConstants.SLASH_STR;

/**
 * Created by George-Lenovo on 13/03/2018.
 */
@Controller
public class HomeController extends BaseController {

    @GetMapping(SLASH_STR)
    public ModelAndView home() {
        return super.view(null,Map.ofEntries(entry(PAGE_TITLE_STR, PIZZA_NATION_PAGE_TITLE)));
    }
}
