package pizzaNation.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static pizzaNation.app.util.WebConstants.PRODUCT_EXCEPTION_MESSAGE;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = PRODUCT_EXCEPTION_MESSAGE)
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {
        super(PRODUCT_EXCEPTION_MESSAGE);
    }

}
