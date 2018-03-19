package pizzaNation.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static java.util.Map.entry;

/**
 * Created by George-Lenovo on 15/03/2018.
 */
@Controller
public class StoresController extends BaseController {

    @GetMapping("/stores")
    public ModelAndView stores() {
        return super.constructStaticModelAndViewResponse(Map.ofEntries(
                entry("layout", "base-layout"),
                entry("view", "store/store"),
                entry("pageTitle", "Our Stores")
                )
        );
    }
}
