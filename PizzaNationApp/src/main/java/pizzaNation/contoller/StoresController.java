package pizzaNation.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

/**
 * Created by George-Lenovo on 15/03/2018.
 */
@Controller
public class StoresController extends BaseController {

    @GetMapping("/stores")
    public ModelAndView stores() {
        return super.constructStaticModelAndViewResponse(
                "stores/stores",
                Map.ofEntries(
                        entry("view", "stores/map"),
                        entry("pageTitle", "Our Stores"),
                        entry("lat", 34.031601),
                        entry("long", -118.685997)
                )
        );
    }
}
