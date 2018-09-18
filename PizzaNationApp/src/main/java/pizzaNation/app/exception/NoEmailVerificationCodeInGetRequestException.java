package pizzaNation.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static pizzaNation.app.util.WebConstants.INVALID_EMAIL_CONFIRM_REQUEST_MESSAGE;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = INVALID_EMAIL_CONFIRM_REQUEST_MESSAGE)
public class NoEmailVerificationCodeInGetRequestException extends RuntimeException {

    public NoEmailVerificationCodeInGetRequestException() {
        super(INVALID_EMAIL_CONFIRM_REQUEST_MESSAGE);
    }
}
