package pizzaNation.user.service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static pizzaNation.app.util.WebConstants.*;

public interface IBaseService {

    boolean containErrors(BindingResult bindingResult, RedirectAttributes attributes, String errorFlashAttribute);
}
