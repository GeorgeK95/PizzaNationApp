package pizzaNation.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.admin.repository.MenuRepository;
import pizzaNation.app.model.entity.Menu;
import pizzaNation.app.model.entity.Product;
import pizzaNation.app.model.request.AddMenuRequestModel;
import pizzaNation.app.model.request.EditMenuRequestModel;
import pizzaNation.app.model.view.MenuViewModel;
import pizzaNation.app.service.IProductService;
import pizzaNation.app.util.DTOConverter;
import pizzaNation.user.service.BaseService;

import java.util.ArrayList;
import java.util.List;

import static pizzaNation.app.util.WebConstants.*;

/**
 * Created by George-Lenovo on 01/04/2018.
 */
@Service
@Transactional
public class MenuService extends BaseService implements IMenuService {

    private final MenuRepository menuRepository;

    private final IProductService productService;

    @Autowired
    public MenuService(MenuRepository menuRepository, IProductService productService) {
        this.menuRepository = menuRepository;
        this.productService = productService;
    }


    @Override
    public boolean addMenu(AddMenuRequestModel requestModel, RedirectAttributes attributes, BindingResult bindingResult) {
        attributes.addFlashAttribute(ADD_MENU_REQUEST_MODEL, requestModel);

        if (super.containErrors(bindingResult, attributes, ADD_MENU_ERROR)) return false;

        return this.persistMenu(requestModel);
    }

    @Override
    public boolean persistMenu(AddMenuRequestModel addMenuRequestModel) {
        Menu menu = DTOConverter.convert(addMenuRequestModel, Menu.class);

        menu.setProducts(this.productService.getAllByIds(addMenuRequestModel.getProductIds()));

        this.menuRepository.saveAndFlush(menu);

        return true;
    }

    @Override
    public List<MenuViewModel> findAll() {
        return new ArrayList<>(DTOConverter.convert(this.menuRepository.findAll(), MenuViewModel.class));
    }

    @Override
    public <T> T findById(String id, Class<T> requestModel) {
        return DTOConverter.convert(this.menuRepository.findById(id).get(), requestModel);
    }

    @Override
    public boolean editMenu(EditMenuRequestModel editMenuRequestModel, RedirectAttributes attributes, BindingResult bindingResult,
                            String id) {
        attributes.addFlashAttribute(EDIT_MENU_REQUEST_MODEL, editMenuRequestModel);

        if (super.containErrors(bindingResult, attributes, EDIT_MENU_ERROR)) return false;

        Menu menu = this.menuRepository.findById(id).get();
        menu.setDescription(editMenuRequestModel.getDescription());
        menu.setImagePath(editMenuRequestModel.getImagePath());
        menu.setName(editMenuRequestModel.getName());
        menu.setPriority(editMenuRequestModel.getPriority());
        menu.setProducts(DTOConverter.convertToSet(this.productService.findAll(), Product.class));

        this.menuRepository.saveAndFlush(menu);

        return true;
    }
}
