package pizzaNation.app.contoller.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import pizzaNation.app.contoller.BaseController;

import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.*;

/**
 * Created by George-Lenovo on 02/04/2018.
 */
@RestController
public class ExceptionController extends BaseController implements ErrorController {

    private static final Map<String, Object> NOT_FOUND_PAGE_TITLE_MAP = Map.ofEntries(entry(PAGE_TITLE_STR, NOT_FOUND_PAGE_TITLE));
    private static final Map<String, Object> BAD_REQUEST_PAGE_TITLE_MAP = Map.ofEntries(entry(PAGE_TITLE_STR, BAD_REQUEST_PAGE_TITLE));

    private static final int NOT_FOUND_CODE = 404;

    private static final int BAD_REQUEST_CODE = 400;

    public ModelAndView ingredient() {
        return super.view(INGREDIENT_EXCEPTION_MESSAGE, NOT_FOUND_PAGE_TITLE_MAP, NOT_FOUND_CODE);
    }

    public ModelAndView user() {
        return super.view(USER_EXCEPTION_MESSAGE, NOT_FOUND_PAGE_TITLE_MAP, NOT_FOUND_CODE);
    }

    public ModelAndView menu() {
        return this.view(MENU_EXCEPTION_MESSAGE, NOT_FOUND_PAGE_TITLE_MAP, NOT_FOUND_CODE);
    }

    public ModelAndView product() {
        return this.view(PRODUCT_EXCEPTION_MESSAGE, NOT_FOUND_PAGE_TITLE_MAP, NOT_FOUND_CODE);
    }

    public ModelAndView notConfirmed() {
        return super.view(USER_WITH_GIVEN_CODE_EXCEPTION_MESSAGE, NOT_FOUND_PAGE_TITLE_MAP, BAD_REQUEST_CODE);
    }

    public ModelAndView invalidVerificationCodeRequest() {
        return super.view(INVALID_EMAIL_CONFIRM_REQUEST_MESSAGE, BAD_REQUEST_PAGE_TITLE_MAP, BAD_REQUEST_CODE);
    }

    @RequestMapping(value = UNAUTHORIZED_URL)
    public ModelAndView unauthorized() {
        return super.view(null, Map.ofEntries(entry(PAGE_TITLE_STR, UNAUTHORIZED_PAGE_TITLE)));
    }

    @RequestMapping(value = ERROR_URL)
    public ModelAndView notFound() {
        return super.view(null, Map.ofEntries(entry(PAGE_TITLE_STR, NOT_FOUND_PAGE_TITLE)), NOT_FOUND_CODE);
    }

    @Override
    public String getErrorPath() {
        return UNAUTHORIZED_URL;
    }

    public ModelAndView badRequest() {
        return super.view(CART_NOT_FOUND_EXCEPTION_MESSAGE, BAD_REQUEST_PAGE_TITLE_MAP, BAD_REQUEST_CODE);
    }
}
