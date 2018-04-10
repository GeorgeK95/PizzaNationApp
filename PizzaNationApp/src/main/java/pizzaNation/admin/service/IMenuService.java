package pizzaNation.admin.service;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.app.exception.MenuNotFoundException;
import pizzaNation.app.model.entity.Menu;
import pizzaNation.app.model.request.AddMenuRequestModel;
import pizzaNation.app.model.request.EditMenuRequestModel;
import pizzaNation.app.model.view.MenuViewModel;
import pizzaNation.app.test.UploadImageRequestModel;

import java.util.List;

/**
 * Created by George-Lenovo on 01/04/2018.
 */

public interface IMenuService {
    boolean addMenu(AddMenuRequestModel addMenuRequestModel, RedirectAttributes attributes, BindingResult bindingResult);

    boolean persistMenu(AddMenuRequestModel addMenuRequestModel);

    List<MenuViewModel> findAll();

    boolean editMenu(EditMenuRequestModel addMenuRequestModel, RedirectAttributes attributes, BindingResult bindingResult, String name);

//    boolean deleteMenu(EditMenuRequestModel editMenuRequestModel, RedirectAttributes attributes, BindingResult bindingResult, String name);
    boolean deleteMenu(String name);

    EditMenuRequestModel findByName(String menuName);

    Menu findMenuEntityByName(String menuName);

    EditMenuRequestModel findById(String id);

    void uploadImage(UploadImageRequestModel requestModel);
}
