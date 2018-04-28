package pizzaNation.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static pizzaNation.app.util.WebConstants.JSESSIONID_NOT_FOUND_EXCEPTION_MESSAGE;

/**
 * Created by George-Lenovo on 27/04/2018.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = JSESSIONID_NOT_FOUND_EXCEPTION_MESSAGE)
public class SessionNotFoundException extends RuntimeException {

    public SessionNotFoundException() {
        super(JSESSIONID_NOT_FOUND_EXCEPTION_MESSAGE);
    }
}
