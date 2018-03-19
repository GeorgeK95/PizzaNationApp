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
public class MenuController extends BaseController {
    @GetMapping("menu")
    public ModelAndView menu() {
        ModelAndView modelAndView = super.constructStaticModelAndViewResponse(Map.ofEntries(
                entry("layout", "base-layout"),
                entry("view", "menu/menu"),
                entry("pageTitle", "Menu")
                )
        );
        modelAndView.getModelMap().addAttribute("headCssIncludeFiles", List.of("/css/menu.css"));
        return modelAndView;
    }

}
