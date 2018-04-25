package pizzaNation.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static pizzaNation.app.util.WebConstants.INGREDIENT_EXCEPTION_MESSAGE;
import static pizzaNation.app.util.WebConstants.MENU_EXCEPTION_MESSAGE;

/**
 * Created by George-Lenovo on 04/04/2018.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = INGREDIENT_EXCEPTION_MESSAGE)
public class IngredientNotFoundException extends RuntimeException {

    public IngredientNotFoundException() {
        super(INGREDIENT_EXCEPTION_MESSAGE);
    }
}
