package pizzaNation.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static pizzaNation.app.util.WebConstants.PRODUCT_EXCEPTION_MESSAGE;

/**
 * Created by George-Lenovo on 03/04/2018.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Product with given name was not found.")
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {
        super(PRODUCT_EXCEPTION_MESSAGE);
    }

}
