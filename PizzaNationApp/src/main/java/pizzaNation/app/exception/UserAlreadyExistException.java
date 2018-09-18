package pizzaNation.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static pizzaNation.app.util.WebConstants.EMAIL_ALREADY_TAKEN_MESSAGE;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = EMAIL_ALREADY_TAKEN_MESSAGE)
public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException() {
        super(EMAIL_ALREADY_TAKEN_MESSAGE);
    }
}
