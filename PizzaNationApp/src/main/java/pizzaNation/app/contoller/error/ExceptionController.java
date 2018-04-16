package pizzaNation.app.contoller.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import pizzaNation.app.contoller.BaseController;
import pizzaNation.app.exception.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.*;

/**
 * Created by George-Lenovo on 02/04/2018.
 */
@Component
public class ExceptionController extends BaseController {

    private static final Map<String, Object> NOT_FOUND_PAGE_TITLE_MAP = Map.ofEntries(entry(PAGE_TITLE_STR, NOT_FOUND_PAGE_TITLE));

    private static final int STATUS_CODE = 404;

    //    @ExceptionHandler(value = {IngredientNotFoundException.class})
    public ModelAndView ingredient() {
        return super.view(INGREDIENT_EXCEPTION_MESSAGE, NOT_FOUND_PAGE_TITLE_MAP, STATUS_CODE);
    }

    //    @ExceptionHandler(value = {UserNotFoundException.class})
    public ModelAndView user() {
        return super.view(USER_EXCEPTION_MESSAGE, NOT_FOUND_PAGE_TITLE_MAP, STATUS_CODE);
    }

    //    @ExceptionHandler(value = {MenuNotFoundException.class})
    public ModelAndView menu() {
        return this.view(MENU_EXCEPTION_MESSAGE, NOT_FOUND_PAGE_TITLE_MAP, STATUS_CODE);
    }

    //    @ExceptionHandler(value = {ProductNotFoundException.class})
    public ModelAndView product() {
        return this.view(PRODUCT_EXCEPTION_MESSAGE, NOT_FOUND_PAGE_TITLE_MAP, STATUS_CODE);
    }

    //    @ExceptionHandler(value = {ConfirmCodeNotFoundException.class})
    public ModelAndView notConfirmed() {
        return super.view(USER_WITH_GIVEN_CODE_EXCEPTION_MESSAGE, NOT_FOUND_PAGE_TITLE_MAP, STATUS_CODE);
    }

      /*@ExceptionHandler(value = {UserNotConfirmedException.class})
    public ModelAndView disable() {
        return super.view(USER_DISABLE_EXCEPTION_MESSAGE, Map.ofEntries(entry(PAGE_TITLE_STR, FORBIDDEN_PAGE_TITLE)));
    }*/
}
