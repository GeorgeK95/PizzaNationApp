package pizzaNation.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

/**
 * Created by George-Lenovo on 15/03/2018.
 */
@Controller
public class AboutController extends BaseController {

    @GetMapping("/about")
    public ModelAndView about() {
        ModelAndView modelAndView = super.constructStaticModelAndViewResponse(Map.ofEntries(
                entry("layout", "base-layout"),
                entry("view", "about/about"),
                entry("pageTitle", "About us")
                )
        );
        modelAndView.getModelMap().addAttribute("headCssIncludeFiles", List.of("/css/staticStyles.css"));
        return modelAndView;
    }
}
