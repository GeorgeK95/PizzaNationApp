package pizzaNation.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.admin.service.IMenuService;
import pizzaNation.app.contoller.BaseController;
import pizzaNation.app.model.request.AddMenuRequestModel;
import pizzaNation.app.model.request.EditMenuRequestModel;
import pizzaNation.app.model.response.ProductViewModel;
import pizzaNation.app.model.view.MenuViewModel;
import pizzaNation.app.service.IProductService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.*;

/**
 * Created by George-Lenovo on 25/03/2018.
 */
@Controller
@RequestMapping(ADMIN_URL)
public class AdminController extends BaseController {

    private final IProductService productService;
    private final IMenuService menuService;

    @Autowired
    public AdminController(IProductService productService, IMenuService menuService) {
        this.productService = productService;
        this.menuService = menuService;
    }

    @ModelAttribute(name = "productsList")
    public List<ProductViewModel> getCapitals() {
        return this.productService.findAll();
    }

    @ModelAttribute(name = "menusList")
    public List<MenuViewModel> getMenus() {
        return this.menuService.findAll();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @GetMapping(EMPTY_URL)
    public ModelAndView admin() {
        return super.view(null, Map.ofEntries(entry(PAGE_TITLE_STR, ADMIN_PANEL_PAGE_TITLE)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @GetMapping(ALL_MENUS_URL)
    public ModelAndView allMenus() {
        List<MenuViewModel> menus = this.menuService.findAll();

        return super.view(menus, Map.ofEntries(entry(PAGE_TITLE_STR, ADMIN_PANEL_PAGE_TITLE)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @GetMapping(ADD_MENUS_URL)
    public ModelAndView addMenu(AddMenuRequestModel addMenuRequestModel) {
        return super.view(addMenuRequestModel, Map.ofEntries(entry(PAGE_TITLE_STR, ADMIN_PANEL_PAGE_TITLE)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @PostMapping(ADD_MENUS_URL)
    public ModelAndView addMenuProcess(@ModelAttribute @Valid AddMenuRequestModel addMenuRequestModel, BindingResult bindingResult,
                                       RedirectAttributes attributes) {
        if (!this.menuService.addMenu(addMenuRequestModel, attributes, bindingResult))
            return super.redirect(ADMIN_ADD_MENUS_URL);
        return super.redirect(ADMIN_ALL_MENUS_URL);
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @GetMapping(EDIT_MENUS_URL)
    public ModelAndView editMenu(@PathVariable String id) {
        return super.view(this.menuService.findById(id, EditMenuRequestModel.class), Map.ofEntries(entry(PAGE_TITLE_STR, ADMIN_PANEL_PAGE_TITLE)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @PostMapping(EDIT_MENUS_URL)
    public ModelAndView editMenuProcess(@PathVariable String id, @ModelAttribute @Valid EditMenuRequestModel editMenuRequestModel, BindingResult bindingResult,
                                        RedirectAttributes attributes) {
        if (!this.menuService.editMenu(editMenuRequestModel, attributes, bindingResult, id))
            return super.redirect(ADMIN_EDIT_MENUS_URL);
        return super.redirect(ADMIN_ALL_MENUS_URL);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @GetMapping(DELETE_MENUS_URL)
    public ModelAndView deleteMenu(@PathVariable String id) {
        return super.view(null, Map.ofEntries(entry(PAGE_TITLE_STR, ADMIN_PANEL_PAGE_TITLE)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @PostMapping(DELETE_MENUS_URL)
    public ModelAndView deleteMenuProcess(@PathVariable String id, @ModelAttribute @Valid EditMenuRequestModel editMenuRequestModel, BindingResult bindingResult,
                                          RedirectAttributes attributes) {
        if (!this.menuService.editMenu(editMenuRequestModel, attributes, bindingResult, id))
            return super.redirect(ADMIN_EDIT_MENUS_URL);
        return super.redirect(ADMIN_ALL_MENUS_URL);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = ADD_STORES_URL, method = RequestMethod.POST)
    public @ResponseBody
    String addStoresProcess(@RequestParam Double lat, @RequestParam Double lng) throws Exception {
//        throw new Exception("Pderasdsad");
        return "Saved.";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(ALL_USERS_URL)
    public ModelAndView allUsers() {
        return super.view(null, Map.ofEntries(entry(PAGE_TITLE_STR, ADMIN_PANEL_PAGE_TITLE)));
    }
}
