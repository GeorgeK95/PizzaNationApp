package pizzaNation.app.service.api;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.app.model.request.EditProductRequestModel;

public interface IProductEditService {

    boolean editProduct(EditProductRequestModel editProductRequestModel, RedirectAttributes attributes, BindingResult bindingResult, String name);
}
