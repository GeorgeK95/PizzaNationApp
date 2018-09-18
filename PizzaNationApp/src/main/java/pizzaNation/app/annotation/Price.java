package pizzaNation.app.annotation;

import org.springframework.stereotype.Component;
import pizzaNation.app.validator.PriceValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NotNull
@Component
@Constraint(validatedBy = PriceValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Price {
    String message() default "Missing or invalid price.";

    int digitsAfterDecPlate() default 2;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
