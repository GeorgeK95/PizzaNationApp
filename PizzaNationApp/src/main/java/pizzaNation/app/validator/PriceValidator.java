package pizzaNation.app.validator;

import pizzaNation.app.annotation.Price;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

/**
 * Created by George-Lenovo on 25/04/2018.
 */
public class PriceValidator implements ConstraintValidator<Price, BigDecimal> {
    private static final int ZERO = 0;

    int digits;

    @Override
    public void initialize(Price price) {
        this.digits = price.digitsAfterDecPlate();
    }

    private int getDigitsAfterDecPlate(String price) {
        String[] split = price.split("\\.");
        return split[1].length();
    }

    @Override
    public boolean isValid(BigDecimal s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) return false;

        if (getDigitsAfterDecPlate(s.toString()) != this.digits && getDigitsAfterDecPlate(s.toString()) != 1 ||
                s.compareTo(new BigDecimal(ZERO)) <= 0) {
            return false;
        }

        return true;
    }
}
