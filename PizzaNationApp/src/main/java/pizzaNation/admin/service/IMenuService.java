package pizzaNation.admin.service;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.app.model.entity.Menu;
import pizzaNation.app.model.request.AddMenuRequestModel;
import pizzaNation.app.model.request.EditMenuRequestModel;
import pizzaNation.app.model.view.MenuViewModel;

import java.util.List;
import java.util.Set;

public interface IMenuService {
    boolean addMenu(AddMenuRequestModel addMenuRequestModel, RedirectAttributes attributes, BindingResult bindingResult);

    boolean persistMenu(AddMenuRequestModel addMenuRequestModel);

    List<MenuViewModel> findAll();

    List<MenuViewModel> findAllByPriority();

    boolean editMenu(EditMenuRequestModel addMenuRequestModel, RedirectAttributes attributes, BindingResult bindingResult, String name);

    boolean deleteMenu(String name);

    EditMenuRequestModel findByName(String menuName);

    List<MenuViewModel> findAllByDateDesc();

    boolean persistMenuEntity(Menu menu);
}
