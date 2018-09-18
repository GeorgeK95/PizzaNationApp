package pizzaNation.app.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.*;

@Controller
public class StoresController extends BaseController {

    @Autowired
    public StoresController() {
    }

    @GetMapping(STORES_URL)
    public ModelAndView stores() {
        return super.view(Map.ofEntries(entry(PAGE_TITLE_STR, OUR_STORES_PAGE_TITLE), entry(MAP_VIEW_STR, STORES_MAP_PAGE_TITLE)));
    }
}
