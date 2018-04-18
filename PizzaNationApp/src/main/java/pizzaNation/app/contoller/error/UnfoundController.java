/*
package pizzaNation.app.contoller.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import pizzaNation.app.contoller.BaseController;
import pizzaNation.app.service.contract.IStoreService;

import javax.persistence.Access;
import java.security.Principal;
import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.*;

@RestController
public class UnfoundController extends BaseController implements ErrorController {

    @RequestMapping(value = UNAUTHORIZED_URL)
    public ModelAndView unauthorized() {
        return super.view(null, Map.ofEntries(entry(PAGE_TITLE_STR, UNAUTHORIZED_PAGE_TITLE)));
    }

    @RequestMapping(value = ERROR_URL)
    public ModelAndView notFound() {
        return super.view(null, Map.ofEntries(entry(PAGE_TITLE_STR, NOT_FOUND_PAGE_TITLE)));
    }

    @Override
    public String getErrorPath() {
        return UNAUTHORIZED_URL;
    }

}
*/
