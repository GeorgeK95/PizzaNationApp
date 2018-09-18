package pizzaNation.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static pizzaNation.app.util.WebConstants.MENU_EXCEPTION_MESSAGE;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = MENU_EXCEPTION_MESSAGE)
public class MenuNotFoundException extends RuntimeException {

    public MenuNotFoundException() {
        super(MENU_EXCEPTION_MESSAGE);
    }

}
