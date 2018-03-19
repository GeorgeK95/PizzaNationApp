package pizzaNation.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by George-Lenovo on 15/03/2018.
 */
@Controller
public abstract class BaseController {

    protected ModelAndView constructStaticModelAndViewResponse(Map<String, String> attributesMap) {
        ModelAndView modelAndView = new ModelAndView(attributesMap.get("layout"));
        attributesMap.forEach((key, value) -> modelAndView.getModelMap().addAttribute(key, value));
        return modelAndView;
    }
}
