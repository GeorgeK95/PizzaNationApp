package pizzaNation.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.app.annotation.LoggerAction;
import pizzaNation.app.contoller.BaseController;
import pizzaNation.app.enums.Action;
import pizzaNation.app.enums.TableEnum;
import pizzaNation.app.enums.Unit;
import pizzaNation.app.model.request.AddProductRequestModel;
import pizzaNation.app.model.request.EditProductRequestModel;
import pizzaNation.app.model.response.IngredientResponseModel;
import pizzaNation.app.service.contract.IIngredientService;
import pizzaNation.app.service.contract.IProductService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

import static pizzaNation.admin.controller.AdminController.ADMIN_PAGE_TITLE_MAP_ENTRY;
import static pizzaNation.app.util.WebConstants.*;

/**
 * Created by George-Lenovo on 02/04/2018.
 */
@Controller
@RequestMapping(ADMIN_URL)
public class AdminProductController extends BaseController {

    private final IProductService productService;
    private final IIngredientService ingredientService;

    @Autowired
    public AdminProductController(IProductService productService, IIngredientService ingredientService) {
        this.productService = productService;
        this.ingredientService = ingredientService;
    }

    @ModelAttribute(name = INGREDIENTS_LIST)
    public List<IngredientResponseModel> getProducts() {
        return this.ingredientService.findAllByDateDesc();
    }

    @ModelAttribute(name = UNITS_LIST)
    public List<Unit> getUnits() {
        return Arrays.asList(Unit.values());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @RequestMapping(method = RequestMethod.GET, value = {ALL_PRODUCTS_URL, PRODUCTS_URL})
    public ModelAndView allProducts() {
        return super.view(this.productService.findAllByDate(), ADMIN_PAGE_TITLE_MAP_ENTRY);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @GetMapping(ADD_PRODUCTS_URL)
    public ModelAndView addProduct(AddProductRequestModel addProductRequestModel) {
        return super.view(addProductRequestModel, ADMIN_PAGE_TITLE_MAP_ENTRY);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @PostMapping(ADD_PRODUCTS_URL)
    @LoggerAction(table = TableEnum.PRODUCT, action = Action.ADD)
    public ModelAndView addProductProcess(@ModelAttribute @Valid AddProductRequestModel addProductRequestModel,
                                          BindingResult bindingResult, RedirectAttributes attributes) {
        if (!this.productService.addProduct(addProductRequestModel, attributes, bindingResult))
            return super.redirect(ADMIN_ADD_PRODUCTS_URL);
        return super.redirectAndLog(ADMIN_ALL_PRODUCTS_URL);
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @GetMapping(EDIT_PRODUCTS_URL)
    public ModelAndView editProduct(@PathVariable String name) {
        return super.view(this.productService.findByName(name), ADMIN_PAGE_TITLE_MAP_ENTRY);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @PostMapping(EDIT_PRODUCTS_URL)
    @LoggerAction(table = TableEnum.PRODUCT, action = Action.EDIT)
    public ModelAndView editProductProcess(@ModelAttribute @Valid EditProductRequestModel editProductRequestModel,
                                           BindingResult bindingResult, @PathVariable String name, RedirectAttributes attributes) {
        if (!this.productService.editProduct(editProductRequestModel, attributes, bindingResult, name))
            return super.redirect(ADMIN_EDIT_PRODUCTS_URL.concat(SLASH_STR + name));
        return super.redirectAndLog(ADMIN_ALL_PRODUCTS_URL);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @GetMapping(DELETE_PRODUCTS_URL)
    public ModelAndView deleteProduct(@PathVariable String name) {
        return super.view(this.productService.findByName(name), ADMIN_PAGE_TITLE_MAP_ENTRY);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @PostMapping(DELETE_PRODUCTS_URL)
    @LoggerAction(table = TableEnum.PRODUCT, action = Action.DELETE)
    public ModelAndView deleteProductProcess(@PathVariable String name) {
        if (!this.productService.deleteProduct(name))
            return super.redirectAndLog(DELETE_PRODUCTS_URL.concat(name));
        return super.redirectAndLog(ADMIN_ALL_PRODUCTS_URL);
    }

}
