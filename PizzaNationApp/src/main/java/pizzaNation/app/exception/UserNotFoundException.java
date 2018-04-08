package pizzaNation.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static pizzaNation.app.util.WebConstants.MENU_EXCEPTION_MESSAGE;
import static pizzaNation.app.util.WebConstants.USER_EXCEPTION_MESSAGE;

/**
 * Created by George-Lenovo on 02/04/2018.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User with given id was not found.")
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super(USER_EXCEPTION_MESSAGE);
    }

}
