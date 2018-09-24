package pizzaNation.user.service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.app.model.request.EditSignInRequestModel;
import pizzaNation.user.model.request.UserRegisterRequestModel;
import pizzaNation.user.service.api.IBaseService;

import static pizzaNation.app.util.WebConstants.*;

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

    protected boolean passwordsMismatch(Object requestModel, RedirectAttributes attributes) {
        if (requestModel instanceof UserRegisterRequestModel) {
            return this.passwordsMismatch((UserRegisterRequestModel) requestModel, attributes);
        } else {
            return this.passwordsMismatch((EditSignInRequestModel) requestModel, attributes);
        }
    }

    private boolean passwordsMismatch(EditSignInRequestModel requestModel, RedirectAttributes attributes) {
        if (!requestModel.getPassword().equals(requestModel.getConfirm())) {
            attributes.addFlashAttribute(USER_EDIT_ERROR, PASSWORD_MISMATCH_MESSAGE);
            return true;
        }

        return false;
    }

    private boolean passwordsMismatch(UserRegisterRequestModel requestModel, RedirectAttributes attributes) {
        if (!requestModel.getPassword().equals(requestModel.getConfirmPassword())) {
            attributes.addFlashAttribute(USER_REGISTER_ERROR, PASSWORD_MISMATCH_MESSAGE);
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
