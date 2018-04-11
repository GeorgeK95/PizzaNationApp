package pizzaNation.app.contoller.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import pizzaNation.app.contoller.BaseController;
import pizzaNation.app.exception.*;

import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.*;

/**
 * Created by George-Lenovo on 02/04/2018.
 */
@ControllerAdvice
public class ExceptionController extends BaseController {

    @ExceptionHandler(value = {MenuNotFoundException.class})
    public ModelAndView menu() {
        return super.view(MENU_EXCEPTION_MESSAGE, Map.ofEntries(entry(PAGE_TITLE_STR, NOT_FOUND_PAGE_TITLE)));
    }

    @ExceptionHandler(value = {IngredientNotFoundException.class})
    public ModelAndView ingredient() {
        return super.view(INGREDIENT_EXCEPTION_MESSAGE, Map.ofEntries(entry(PAGE_TITLE_STR, NOT_FOUND_PAGE_TITLE)));
    }

    @ExceptionHandler(value = {ProductNotFoundException.class})
    public ModelAndView product() {
        return super.view(PRODUCT_EXCEPTION_MESSAGE, Map.ofEntries(entry(PAGE_TITLE_STR, NOT_FOUND_PAGE_TITLE)));
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ModelAndView user() {
        return super.view(USER_EXCEPTION_MESSAGE, Map.ofEntries(entry(PAGE_TITLE_STR, NOT_FOUND_PAGE_TITLE)));
    }

    /*@ExceptionHandler(value = {UserNotConfirmedException.class})
    public ModelAndView disable() {
        return super.view(USER_DISABLE_EXCEPTION_MESSAGE, Map.ofEntries(entry(PAGE_TITLE_STR, FORBIDDEN_PAGE_TITLE)));
    }*/
}
