package pizzaNation.contoller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController extends BaseController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public ModelAndView error() {
        return super.constructStaticModelAndViewResponse("error/error-404");
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}