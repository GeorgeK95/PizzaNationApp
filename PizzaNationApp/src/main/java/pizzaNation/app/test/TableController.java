package pizzaNation.app.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pizzaNation.app.contoller.BaseController;

import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.PAGE_TITLE_STR;

/**
 * Created by George-Lenovo on 12/04/2018.
 */
@Controller
public class TableController extends BaseController {

    @GetMapping("/table")
    public ModelAndView table() {
        return super.view(null, Map.ofEntries(entry(PAGE_TITLE_STR, "Table Test")));
    }
}
