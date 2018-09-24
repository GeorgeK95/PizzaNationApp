package pizzaNation.app.validator;

import pizzaNation.app.annotation.Price;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

import static pizzaNation.app.util.WebConstants.PRICE_SEPARATOR;

public class PriceValidator implements ConstraintValidator<Price, BigDecimal> {

    private int digits;

    @Override
    public void initialize(Price price) {
        this.digits = price.digitsAfterDecPlate();
    }

    private int getDigitsAfterDecPlate(String price) {
        if (!price.contains(PRICE_SEPARATOR)) return 0;
        return price.split(PRICE_SEPARATOR)[1].length();
    }

    @Override
    public boolean isValid(BigDecimal s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) return false;

        return this.getDigitsAfterDecPlate(s.toString()) <= this.digits;
        /*return (getDigitsAfterDecPlate(s.toString()) == this.digits || getDigitsAfterDecPlate(s.toString()) == 1) &&
                s.compareTo(new BigDecimal(ZERO)) > 0;*/
    }
}
