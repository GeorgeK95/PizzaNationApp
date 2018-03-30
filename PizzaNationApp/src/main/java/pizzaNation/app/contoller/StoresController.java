package pizzaNation.app.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pizzaNation.app.model.response.StoreResponseModel;

import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.*;

/**
 * Created by George-Lenovo on 15/03/2018.
 */
@Controller
public class StoresController extends BaseController {

    @GetMapping(STORES_URL)
    public ModelAndView stores() {
        StoreResponseModel m = new StoreResponseModel(42.156429, 24.731036);
        return super.view(m, Map.ofEntries(entry(PAGE_TITLE_STR, OUR_STORES_PAGE_TITLE), entry(MAP_VIEW_STR, STORES_MAP_PAGE_TITLE)));
    }
}
