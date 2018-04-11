package pizzaNation.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;


import static pizzaNation.app.util.WebConstants.USER_DISABLE_EXCEPTION_MESSAGE;

/**
 * Created by George-Lenovo on 12/04/2018.
 */
public class UserNotConfirmedException extends AuthenticationException {
    public UserNotConfirmedException() {
        super(USER_DISABLE_EXCEPTION_MESSAGE);
    }
}
