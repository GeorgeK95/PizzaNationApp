package pizzaNation.admin.service;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.app.model.request.AddMenuRequestModel;
import pizzaNation.app.model.request.EditMenuRequestModel;
import pizzaNation.app.model.view.MenuViewModel;

import java.util.List;

/**
 * Created by George-Lenovo on 01/04/2018.
 */

public interface IMenuService {
    boolean addMenu(AddMenuRequestModel addMenuRequestModel, RedirectAttributes attributes, BindingResult bindingResult);

    boolean persistMenu(AddMenuRequestModel addMenuRequestModel);

    List<MenuViewModel> findAll();

    <T> T findById(String id, Class<T> editMenuRequestModel);

    boolean editMenu(EditMenuRequestModel addMenuRequestModel, RedirectAttributes attributes, BindingResult bindingResult, String id);
}
