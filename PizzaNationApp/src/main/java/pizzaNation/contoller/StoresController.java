package pizzaNation.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pizzaNation.model.response.StoreResponseModel;

import java.util.Map;

import static java.util.Map.entry;

/**
 * Created by George-Lenovo on 15/03/2018.
 */
@Controller
public class StoresController extends BaseController {


    @GetMapping("/stores")
    public ModelAndView stores() {
        StoreResponseModel m = new StoreResponseModel(42.156429, 24.731036);
        return super.view(m, Map.ofEntries(entry("pageTitle", "Our Stores"), entry("mapView", "stores/map")));
    }
}
