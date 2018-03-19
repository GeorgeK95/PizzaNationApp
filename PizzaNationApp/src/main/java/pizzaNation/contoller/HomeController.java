package pizzaNation.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

/**
 * Created by George-Lenovo on 13/03/2018.
 */
@Controller
public class HomeController extends BaseController {

    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = super.constructStaticModelAndViewResponse(
                "base-layout",
                Map.ofEntries(
                entry("view", "home/home"),
                entry("pageTitle", "Pizza Nation")
                )
        );
        modelAndView.getModelMap().addAttribute("pageStyles", List.of("/css/page/home.css"));
        return modelAndView;
    }
}
