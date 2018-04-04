package pizzaNation.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.admin.repository.MenuRepository;
import pizzaNation.app.exception.MenuNotFoundException;
import pizzaNation.app.model.entity.Menu;
import pizzaNation.app.model.entity.Product;
import pizzaNation.app.model.request.AddMenuRequestModel;
import pizzaNation.app.model.request.EditMenuRequestModel;
import pizzaNation.app.model.view.MenuViewModel;
import pizzaNation.app.service.IProductService;
import pizzaNation.app.util.DTOConverter;
import pizzaNation.user.service.BaseService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

        if (this.checkForDuplicateName(requestModel.getName(), ADD_MENU_ERROR, attributes)) return false;

        return this.persistMenu(requestModel);
    }

    private boolean checkForDuplicateName(String name, String addMenuError, RedirectAttributes attributes) {
        if (this.menuRepository.existsByName(name)) {
            attributes.addFlashAttribute(addMenuError, MENU_NAME_ALREADY_TAKEN_MESSAGE);
            return true;
        }

        return false;
    }

    @Override
    public boolean persistMenu(AddMenuRequestModel addMenuRequestModel) {
        Menu menu = DTOConverter.convert(addMenuRequestModel, Menu.class);

        menu.setProducts(this.productService.getAllByIds(addMenuRequestModel.getProductsIds()));

        this.menuRepository.saveAndFlush(menu);

        return true;
    }

    @Override
    public List<MenuViewModel> findAll() {
        return new ArrayList<>(DTOConverter.convert(this.menuRepository.findAll(), MenuViewModel.class));
    }

    @Override
    public boolean editMenu(EditMenuRequestModel editMenuRequestModel, RedirectAttributes attributes, BindingResult bindingResult,
                            String name) {
        attributes.addFlashAttribute(EDIT_MENU_REQUEST_MODEL, editMenuRequestModel);

        Menu menu = this.menuRepository.findByName(name);

        if (this.hasErrors(menu, editMenuRequestModel, bindingResult, attributes)) return false;

        menu.setDescription(editMenuRequestModel.getDescription());
        menu.setImagePath(editMenuRequestModel.getImagePath());
        menu.setName(editMenuRequestModel.getName());
        menu.setPriority(editMenuRequestModel.getPriority());
        menu.setProducts(DTOConverter.convertToSet(this.productService.getAllByIds(
                editMenuRequestModel.getProductsIds().toArray(new String[0])), Product.class)
        );

        this.menuRepository.saveAndFlush(menu);

        return true;
    }

    private boolean hasErrors(Menu menu, EditMenuRequestModel editMenuRequestModel, BindingResult bindingResult,
                              RedirectAttributes attributes) {
        if (menu == null) throw new MenuNotFoundException();

        if (this.checkForDuplicateName(editMenuRequestModel.getName(), EDIT_MENU_ERROR, attributes) &&
                !menu.getName().equals(editMenuRequestModel.getName())) return true;

        return super.containErrors(bindingResult, attributes, EDIT_MENU_ERROR);
    }

    @Override
    public boolean deleteMenu(String name) {
        Menu menu = this.menuRepository.findByName(name);

        if (menu == null) throw new MenuNotFoundException();

        menu.setProducts(null); //release products so they wont be deleted

        this.menuRepository.delete(menu);

        return true;
    }

    @Override
    public EditMenuRequestModel findByName(String menuName) {
        Menu menu = this.menuRepository.findByName(menuName);

        if (menu == null) throw new MenuNotFoundException();

        EditMenuRequestModel editMenuRequestModel = DTOConverter.convert(menu, EditMenuRequestModel.class);

        editMenuRequestModel.setProductsIds(menu.getProducts().stream().map(Product::getId).collect(Collectors.toSet()));

        return editMenuRequestModel;
    }

    @Override
    public Menu findMenuEntityByName(String menuName) {
        return this.menuRepository.findByName(menuName);
    }

    @Override
    public EditMenuRequestModel findById(String id) {
        Menu menu = this.menuRepository.findById(id).get();
        EditMenuRequestModel model = DTOConverter.convert(menu, EditMenuRequestModel.class);
        model.setProductsIds(new HashSet<>(menu.getProducts().stream().map(Product::getName).collect(Collectors.toSet())));
        return model;
    }
}
