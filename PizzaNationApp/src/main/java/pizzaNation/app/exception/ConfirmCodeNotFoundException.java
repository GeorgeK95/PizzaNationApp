package pizzaNation.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static pizzaNation.app.util.WebConstants.USER_WITH_GIVEN_CODE_EXCEPTION_MESSAGE;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = USER_WITH_GIVEN_CODE_EXCEPTION_MESSAGE)
public class ConfirmCodeNotFoundException extends RuntimeException {

    public ConfirmCodeNotFoundException() {
        super(USER_WITH_GIVEN_CODE_EXCEPTION_MESSAGE);
    }
}
