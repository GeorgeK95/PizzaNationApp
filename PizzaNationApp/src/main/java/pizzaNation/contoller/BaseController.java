package pizzaNation.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by George-Lenovo on 15/03/2018.
 */
@Controller
public abstract class BaseController {

    protected ModelAndView constructStaticModelAndViewResponse(String viewName, Map<String, Object> attributes) {
        ModelAndView modelAndView = new ModelAndView(viewName);
        attributes.forEach((key, value) -> modelAndView.getModelMap().addAttribute(key, value));
        return modelAndView;
    }
}
