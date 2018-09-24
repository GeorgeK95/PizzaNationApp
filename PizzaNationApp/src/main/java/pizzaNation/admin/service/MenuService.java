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
import pizzaNation.app.model.request.api.MenuRequestModel;
import pizzaNation.app.model.view.MenuViewModel;
import pizzaNation.app.service.api.IImageService;
import pizzaNation.app.service.api.IProductService;
import pizzaNation.app.util.DTOConverter;
import pizzaNation.cloudinary.enumerations.Folder;
import pizzaNation.user.service.BaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static pizzaNation.app.util.WebConstants.*;

@Service
@Transactional
public class MenuService extends BaseService implements IMenuService {

    private final MenuRepository menuRepository;

    private final IProductService productService;

    private final IImageService imageService;

    @Autowired
    public MenuService(MenuRepository menuRepository, IProductService productService, IImageService imageService) {
        this.menuRepository = menuRepository;
        this.productService = productService;
        this.imageService = imageService;
    }


    @Override
    public boolean addMenu(AddMenuRequestModel requestModel, RedirectAttributes attributes, BindingResult bindingResult) {
        attributes.addFlashAttribute(ADD_MENU_REQUEST_MODEL, requestModel);

        if (super.containErrors(bindingResult, attributes, ADD_MENU_ERROR)) return false;

        if (this.checkForDuplicateProperties(requestModel, ADD_MENU_ERROR, attributes)) return false;

        return this.persistMenu(requestModel);
    }

    @Override
    public boolean persistMenu(AddMenuRequestModel addMenuRequestModel) {
        Menu menu = DTOConverter.convert(addMenuRequestModel, Menu.class);

        menu.setProducts(this.productService.getAllByIds(addMenuRequestModel.getProductsIds()));
        menu.setImage(this.imageService.uploadImage(addMenuRequestModel.getImage(), Folder.MENU));

        this.menuRepository.saveAndFlush(menu);

        return true;
    }

    @Override
    public List<MenuViewModel> findAll() {
        return new ArrayList<>(DTOConverter.convert(this.menuRepository.findAll(), MenuViewModel.class));
    }

    @Override
    public List<MenuViewModel> findAllByPriority() {
        return new ArrayList<>(DTOConverter.convert(this.menuRepository.findAllOrderedByPriorityAsc(), MenuViewModel.class));
    }

    @Override
    public boolean editMenu(EditMenuRequestModel editMenuRequestModel, RedirectAttributes attributes, BindingResult bindingResult,
                            String name) {
        attributes.addFlashAttribute(EDIT_MENU_REQUEST_MODEL, editMenuRequestModel);

        Menu menu = this.menuRepository.findByName(name);

        if (this.hasErrors(menu, editMenuRequestModel, bindingResult, attributes)) return false;

        menu.setDescription(editMenuRequestModel.getDescription());
        if (!editMenuRequestModel.getImage().getOriginalFilename().isEmpty())
            menu.setImage(this.imageService.uploadImage(editMenuRequestModel.getImage(), Folder.MENU));
        menu.setName(editMenuRequestModel.getName());
        menu.setPriority(editMenuRequestModel.getPriority());
        menu.setProducts(DTOConverter.convertToSet(this.productService.getAllByIds(
                editMenuRequestModel.getProductsIds().toArray(new String[0])), Product.class)
        );

        this.menuRepository.saveAndFlush(menu);

        return true;
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
    public List<MenuViewModel> findAllByDateDesc() {
        return DTOConverter.convert(this.menuRepository.findAllByDateDesc(), MenuViewModel.class);
    }

    @Override
    public boolean persistMenuEntity(Menu menu) {
        this.menuRepository.saveAndFlush(menu);

        return true;
    }

    private boolean hasErrors(Menu menu, EditMenuRequestModel editMenuRequestModel, BindingResult bindingResult,
                              RedirectAttributes attributes) {
        if (menu == null) throw new MenuNotFoundException();

        if (this.checkForDuplicateProperties(editMenuRequestModel, EDIT_MENU_ERROR, attributes)) return true;

        return super.containErrors(bindingResult, attributes, EDIT_MENU_ERROR);
    }

    private boolean checkForDuplicateProperties(MenuRequestModel requestModel, String error, RedirectAttributes attributes) {
        boolean result = this.checkForDuplicatePriority(requestModel, error, attributes);
        boolean result1 = this.checkForDuplicateName(requestModel, error, attributes);

        return result || result1;
    }

    private boolean checkForDuplicateName(MenuRequestModel requestModel, String error, RedirectAttributes attributes) {
        Menu byName = this.menuRepository.getByName(requestModel.getName());

        return this.checkForDuplicateObject(byName, requestModel, error, MENU_NAME_ALREADY_TAKEN_MESSAGE, attributes);
    }

    private boolean checkForDuplicatePriority(MenuRequestModel requestModel, String error, RedirectAttributes attributes) {
        Menu byPriority = this.menuRepository.getByPriority(requestModel.getPriority());

        return this.checkForDuplicateObject(byPriority, requestModel, error, MENU_PRIORITY_ALREADY_TAKEN_MESSAGE, attributes);
    }

    private boolean checkForDuplicateObject(Menu byName, MenuRequestModel requestModel, String error, String message,
                                            RedirectAttributes attributes) {
        if (byName != null) {
            if (requestModel.getId() == null) {
                attributes.addFlashAttribute(error, message);
                return true;
            }

            if (!requestModel.getId().equals(byName.getId())) {
                attributes.addFlashAttribute(error, message);
                return true;
            }
        }

        return false;
    }
}
