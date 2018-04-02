/*
package pizzaNation.app.contoller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.ERROR_URL;
import static pizzaNation.app.util.WebConstants.NOT_FOUND_PAGE_TITLE;
import static pizzaNation.app.util.WebConstants.PAGE_TITLE_STR;

@RestController
public class Error404Controller extends BaseController implements ErrorController {

    @RequestMapping(value = ERROR_URL)
    public ModelAndView error404() {
        return super.view(null, Map.ofEntries(entry(PAGE_TITLE_STR, NOT_FOUND_PAGE_TITLE)));
    }

    @Override
    public String getErrorPath() {
        return ERROR_URL;
    }
}
*/
