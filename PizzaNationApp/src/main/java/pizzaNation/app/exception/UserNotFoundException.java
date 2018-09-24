package pizzaNation.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static pizzaNation.app.util.WebConstants.USER_EXCEPTION_MESSAGE;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = USER_EXCEPTION_MESSAGE)
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super(USER_EXCEPTION_MESSAGE);
    }

}
