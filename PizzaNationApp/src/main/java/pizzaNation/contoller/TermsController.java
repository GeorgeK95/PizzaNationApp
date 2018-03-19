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
public class TermsController extends BaseController {

    @GetMapping("/terms")
    public ModelAndView terms() {
        ModelAndView modelAndView = super.constructStaticModelAndViewResponse(
                "base-layout",
                Map.ofEntries(
                entry("view", "terms/terms"),
                entry("pageTitle", "Terms and Conditions")
                )
        );
        modelAndView.getModelMap().addAttribute("pageStyles", List.of("/css/page/terms.css"));
        return modelAndView;
    }
}
