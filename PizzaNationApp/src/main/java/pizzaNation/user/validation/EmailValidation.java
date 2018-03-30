package pizzaNation.user.validation;

import org.apache.commons.validator.routines.EmailValidator;
import pizzaNation.user.annotation.Email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by George-Lenovo on 27/03/2018.
 */
public class EmailValidation implements ConstraintValidator<Email, String> {
    @Override
    public void initialize(Email constraintAnnotation) {

    }

    @Override
    public boolean isValid(String emailCandidate, ConstraintValidatorContext constraintValidatorContext) {
        //for local email addresses, give argument true on getInstance() method
        return EmailValidator.getInstance().isValid(emailCandidate);
    }
}
