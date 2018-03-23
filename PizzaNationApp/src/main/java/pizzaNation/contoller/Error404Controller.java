package pizzaNation.contoller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static java.util.Map.entry;

@RestController
public class Error404Controller extends BaseController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public ModelAndView error404() {
        return super.view(null,Map.ofEntries(entry("pageTitle", "Page Not Found")));
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}