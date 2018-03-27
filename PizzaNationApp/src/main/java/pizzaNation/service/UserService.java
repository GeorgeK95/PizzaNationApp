package pizzaNation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.enumeration.Gender;
import pizzaNation.model.entity.User;
import pizzaNation.model.request.UserRegisterRequestModel;
import pizzaNation.repository.UserRepository;
import pizzaNation.util.DTOConverter;

import static pizzaNation.util.WebConstants.*;

/**
 * Created by George-Lenovo on 27/03/2018.
 */
@Service
@Transactional
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean addUser(UserRegisterRequestModel requestModel, BindingResult bindingResult, RedirectAttributes attributes) {
        attributes.addFlashAttribute(USER_REGISTER_REQUEST_MODEL, requestModel);

        if (this.containErrors(bindingResult, attributes)) return false;

        if (this.passwordsMismatch(requestModel, attributes)) return false;

        return this.persistUser(requestModel);
    }

    private boolean persistUser(UserRegisterRequestModel requestModel) {
        User user = DTOConverter.convert(requestModel, User.class);

        Gender.valueOf(requestModel.getGender().toUpperCase());
//        this.userRepository.saveAndFlush(user);

        return true;
    }

    private boolean containErrors(BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            ObjectError error = bindingResult.getAllErrors().get(0);
            String errorMessage = this.getDefaultMessage(error.getDefaultMessage());
            attributes.addFlashAttribute(USER_REGISTER_ERROR, errorMessage);
            return true;
        }

        return false;
    }

    private String getDefaultMessage(String message) {
        if (message.equals(MUST_NOT_BE_BLANK_MESSAGE) || message.equals(MUST_NOT_BE_NULL_MESSAGE)) {
            message = COMPLETE_ALL_FIELDS_MESSAGE;
        }
        return message;
    }

    private boolean passwordsMismatch(UserRegisterRequestModel requestModel, RedirectAttributes attributes) {
        if (!requestModel.getPassword().equals(requestModel.getConfirmPassword())) {
            attributes.addFlashAttribute(USER_REGISTER_ERROR, PASSWORD_MISMATCH_MESSAGE);
            return true;
        }

        return false;
    }
}
