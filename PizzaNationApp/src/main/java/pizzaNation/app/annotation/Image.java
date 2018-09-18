package pizzaNation.app.annotation;

import pizzaNation.app.validator.ImageValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ImageValidator.class)
public @interface Image {
    String message() default "Invalid or missing image file.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
