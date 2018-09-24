package pizzaNation.user.service.api;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface IBaseService {

    boolean containErrors(BindingResult bindingResult, RedirectAttributes attributes, String errorFlashAttribute);
}
