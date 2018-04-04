package pizzaNation.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.app.contoller.BaseController;
import pizzaNation.app.model.request.AddProductRequestModel;
import pizzaNation.app.model.request.EditProductRequestModel;
import pizzaNation.app.model.response.IngredientResponseModel;
import pizzaNation.app.service.IIngredientService;
import pizzaNation.app.service.IProductService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;
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

    @ModelAttribute(name = "ingredientsList")
    public List<IngredientResponseModel> getProducts() {
        return this.ingredientService.findAllByDate();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @GetMapping(ALL_PRODUCTS_URL)
    public ModelAndView allProducts() {
        return super.view(this.productService.findAllByDate(), Map.ofEntries(entry(PAGE_TITLE_STR, ADMIN_PANEL_PAGE_TITLE)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @GetMapping(ADD_PRODUCTS_URL)
    public ModelAndView addProduct(AddProductRequestModel addProductRequestModel) {
        return super.view(addProductRequestModel, Map.ofEntries(entry(PAGE_TITLE_STR, ADMIN_PANEL_PAGE_TITLE)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @PostMapping(ADD_PRODUCTS_URL)
    public ModelAndView addProductProcess(@ModelAttribute @Valid AddProductRequestModel addProductRequestModel,
                                          BindingResult bindingResult, RedirectAttributes attributes) {
        if (!this.productService.addProduct(addProductRequestModel, attributes, bindingResult))
            return super.redirect(ADMIN_ADD_PRODUCTS_URL);
        return super.redirect(ADMIN_ALL_PRODUCTS_URL);
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @GetMapping(EDIT_PRODUCTS_URL)
    public ModelAndView editProduct(@PathVariable String name) {
        return super.view(this.productService.findByName(name), Map.ofEntries(entry(PAGE_TITLE_STR, ADMIN_PANEL_PAGE_TITLE)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @PostMapping(EDIT_PRODUCTS_URL)
    public ModelAndView editProductProcess(@ModelAttribute @Valid EditProductRequestModel editProductRequestModel,
                                           BindingResult bindingResult, @PathVariable String name, RedirectAttributes attributes) {
        if (!this.productService.editProduct(editProductRequestModel, attributes, bindingResult, name))
            return super.redirect(ADMIN_EDIT_PRODUCTS_URL.concat(SLASH_STR + name));
        return super.redirect(ADMIN_ALL_PRODUCTS_URL);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @GetMapping(DELETE_PRODUCTS_URL)
    public ModelAndView deleteProduct(@PathVariable String name) {
        return super.view(this.productService.findByName(name), Map.ofEntries(entry(PAGE_TITLE_STR, ADMIN_PANEL_PAGE_TITLE)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @PostMapping(DELETE_PRODUCTS_URL)
    public ModelAndView deleteProductProcess(@PathVariable String name) {
        if (!this.productService.deleteProduct(name))
            return super.redirect(ADMIN_EDIT_PRODUCTS_URL);
        return super.redirect(ADMIN_ALL_PRODUCTS_URL);
    }
}
