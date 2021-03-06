package pizzaNation.app.validator;

import org.springframework.web.multipart.MultipartFile;
import pizzaNation.app.annotation.Image;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ImageValidator implements ConstraintValidator<Image, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        if (multipartFile == null) return false;
        return !multipartFile.getOriginalFilename().isEmpty();
    }
}
