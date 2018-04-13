package pizzaNation.app.validator;

import org.springframework.web.multipart.MultipartFile;
import pizzaNation.app.annotation.Image;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by George-Lenovo on 13/04/2018.
 */
public class ImageValidator implements ConstraintValidator<Image, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        return !multipartFile.getOriginalFilename().isEmpty();
    }
}
