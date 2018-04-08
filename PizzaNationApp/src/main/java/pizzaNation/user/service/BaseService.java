package pizzaNation.user.service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.app.model.request.AddIngredientRequestModel;

import static pizzaNation.app.util.WebConstants.*;

/**
 * Created by George-Lenovo on 01/04/2018.
 */
public abstract class BaseService implements IBaseService {

    @Override
    public boolean containErrors(BindingResult bindingResult, RedirectAttributes attributes,
                                 String errorFlashAttribute) {
        if (bindingResult.hasErrors()) {
            ObjectError error = bindingResult.getAllErrors().get(0);
            String errorMessage = this.getDefaultMessage(error.getDefaultMessage());
            attributes.addFlashAttribute(errorFlashAttribute, errorMessage);
            return true;
        }

        return false;
    }

    private String getDefaultMessage(String message) {
        if (message.equals(MUST_NOT_BE_BLANK_MESSAGE) || message.equals(MUST_NOT_BE_NULL_MESSAGE)) {
            message = COMPLETE_ALL_FIELDS_MESSAGE;
        }
        if (message.equals(PRIORITY_VALIDATION_MESSAGE)) {
            message = INVALID_PRIORITY_CONSTAINT_MESSAGE;
        }
        return message;
    }

    }
