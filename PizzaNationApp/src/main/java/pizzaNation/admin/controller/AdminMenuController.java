package pizzaNation.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.admin.service.IMenuService;
import pizzaNation.app.annotation.LoggerAction;
import pizzaNation.app.contoller.BaseController;
import pizzaNation.app.enums.Action;
import pizzaNation.app.enums.TableEnum;
import pizzaNation.app.model.request.AddMenuRequestModel;
import pizzaNation.app.model.request.EditMenuRequestModel;
import pizzaNation.app.model.view.ProductViewModel;
import pizzaNation.app.model.view.MenuViewModel;
import pizzaNation.app.service.contract.IProductService;

import javax.validation.Valid;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.admin.controller.AdminController.ADMIN_PAGE_TITLE_MAP_ENTRY;
import static pizzaNation.app.util.WebConstants.*;
import static pizzaNation.app.util.WebConstants.ADMIN_ALL_MENUS_URL;
import static pizzaNation.app.util.WebConstants.ADMIN_EDIT_MENUS_URL;

/**
 * Created by George-Lenovo on 02/04/2018.
 */
@Controller
@RequestMapping(ADMIN_URL)
public class AdminMenuController extends BaseController {

    private final IProductService productService;

    private final IMenuService menuService;

    @Autowired
    public AdminMenuController(IProductService productService, IMenuService menuService) {
        this.productService = productService;
        this.menuService = menuService;
    }

    @ModelAttribute(name = PRODUCTS_LIST)
    public List<ProductViewModel> getProducts() {
        return this.productService.findAll();
    }

    @ModelAttribute(name = MENUS_LIST)
    public List<MenuViewModel> getMenus() {
        return this.menuService.findAll();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @RequestMapping(method = RequestMethod.GET, value = {ALL_MENUS_URL, MENUS_URL})
    public ModelAndView allMenus() {
        return super.view(this.menuService.findAllByDateDesc(), ADMIN_PAGE_TITLE_MAP_ENTRY);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @GetMapping(ADD_MENUS_URL)
    public ModelAndView addMenu(AddMenuRequestModel addMenuRequestModel) {
        return super.view(addMenuRequestModel, ADMIN_PAGE_TITLE_MAP_ENTRY);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @PostMapping(ADD_MENUS_URL)
    @LoggerAction(table = TableEnum.MENU, action = Action.ADD)
    public ModelAndView addMenuProcess(@ModelAttribute @Valid AddMenuRequestModel addMenuRequestModel, BindingResult bindingResult,
                                       RedirectAttributes attributes) {
        if (!this.menuService.addMenu(addMenuRequestModel, attributes, bindingResult))
            return super.redirect(ADMIN_ADD_MENUS_URL);
        return super.redirectAndLog(ADMIN_ALL_MENUS_URL);
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @GetMapping(EDIT_MENUS_URL)
    public ModelAndView editMenu(@PathVariable String name) {
        return super.view(this.menuService.findByName(name), ADMIN_PAGE_TITLE_MAP_ENTRY);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @PostMapping(EDIT_MENUS_URL)
    @LoggerAction(table = TableEnum.MENU, action = Action.EDIT)
    public ModelAndView editMenuProcess(@ModelAttribute @Valid EditMenuRequestModel editMenuRequestModel, BindingResult bindingResult,
                                        @PathVariable String name, RedirectAttributes attributes) {
        if (!this.menuService.editMenu(editMenuRequestModel, attributes, bindingResult, name))
            return super.redirect(ADMIN_EDIT_MENUS_URL.concat(SLASH_STR + name));
        return super.redirectAndLog(ADMIN_ALL_MENUS_URL);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @GetMapping(DELETE_MENUS_URL)
    public ModelAndView deleteMenu(@PathVariable String name) {
        return super.view(this.menuService.findByName(name), ADMIN_PAGE_TITLE_MAP_ENTRY);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @PostMapping(DELETE_MENUS_URL)
    @LoggerAction(table = TableEnum.MENU, action = Action.DELETE)
    public ModelAndView deleteMenuProcess(@PathVariable String name) {
        this.menuService.deleteMenu(name);
        return super.redirectAndLog(ADMIN_ALL_MENUS_URL);
    }
}
