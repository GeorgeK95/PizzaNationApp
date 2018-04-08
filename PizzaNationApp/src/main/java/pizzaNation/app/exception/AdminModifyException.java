package pizzaNation.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static pizzaNation.app.util.WebConstants.ADMIN_EXCEPTION_MESSAGE;

/**
 * Created by George-Lenovo on 07/04/2018.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Admin account cannot be modified.")
public class AdminModifyException extends RuntimeException {

    public AdminModifyException() {
        super(ADMIN_EXCEPTION_MESSAGE);
    }
}
