package pizzaNation.app.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.*;

@Controller
public class AboutController extends BaseController {

    @GetMapping(ABOUT_URL)
    public ModelAndView about() {
        return super.view(null, Map.ofEntries(entry(PAGE_TITLE_STR, ABOUT_US_PAGE_TITLE)));
    }
}
