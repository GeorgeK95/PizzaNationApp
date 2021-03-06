package pizzaNation.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static pizzaNation.app.util.WebConstants.CART_NOT_FOUND_EXCEPTION_MESSAGE;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = CART_NOT_FOUND_EXCEPTION_MESSAGE)
public class CartNotFoundException extends RuntimeException {

    public CartNotFoundException() {
        super(CART_NOT_FOUND_EXCEPTION_MESSAGE);
    }
}
