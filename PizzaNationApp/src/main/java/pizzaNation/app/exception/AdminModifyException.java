package pizzaNation.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static pizzaNation.app.util.WebConstants.ADMIN_EXCEPTION_MESSAGE;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = ADMIN_EXCEPTION_MESSAGE)
public class AdminModifyException extends RuntimeException {

    public AdminModifyException() {
        super(ADMIN_EXCEPTION_MESSAGE);
    }
}
