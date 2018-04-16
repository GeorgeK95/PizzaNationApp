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
import pizzaNation.app.model.request.EditIngredientRequestModel;
import pizzaNation.app.model.request.IngredientsRequestModelWrapper;
import pizzaNation.app.service.contract.IIngredientService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.admin.controller.AdminController.ADMIN_PAGE_TITLE_MAP_ENTRY;
import static pizzaNation.app.util.WebConstants.*;

/**
 * Created by George-Lenovo on 02/04/2018.
 */
@Controller
@RequestMapping(ADMIN_URL)
public class AdminIngredientController extends BaseController {

    private final IIngredientService ingredientService;

    @Autowired
    public AdminIngredientController(IIngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @ModelAttribute(name = UNITS_LIST)
    public List<Unit> getProducts() {
        return Arrays.asList(Unit.values());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @RequestMapping(method = RequestMethod.GET, value = {ALL_INGREDIENTS_URL, INGREDIENTS_URL})
    public ModelAndView allIngredients() {
        return super.view(this.ingredientService.findAllByDateDesc(), ADMIN_PAGE_TITLE_MAP_ENTRY);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @GetMapping(ADD_PRODUCT_INGREDIENTS_URL)
    public ModelAndView addIngredients(@PathVariable String productName) {
        return super.view(this.ingredientService.getRequestModels(), Map.ofEntries(
                ADMIN_PAGE_TITLE_MAP_ENTRY.entrySet().stream().findFirst().get(),
                entry(PRODUCT_NAME_STR, productName)
        ));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @PostMapping(ADD_PRODUCT_INGREDIENTS_URL)
    @LoggerAction(table = TableEnum.INGREDIENT, action = Action.ADD)
    public ModelAndView addIngredientsProcess(@PathVariable String productName,
                                              @ModelAttribute @Valid IngredientsRequestModelWrapper model,
                                              BindingResult bindingResult,
                                              RedirectAttributes attributes) {
        if (!this.ingredientService.addIngredientsAndSetThemToProduct(productName, model, bindingResult, attributes))
            return super.redirect(ADMIN_SET_PRODUCT_INGREDIENTS_URL);
        return super.redirectAndLog(ADMIN_ALL_PRODUCTS_URL);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @GetMapping(EDIT_INGREDIENTS_URL)
    public ModelAndView editIngredient(@PathVariable String id) {
        return super.view(this.ingredientService.findOne(id), ADMIN_PAGE_TITLE_MAP_ENTRY);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @PostMapping(EDIT_INGREDIENTS_URL)
    @LoggerAction(table = TableEnum.INGREDIENT, action = Action.EDIT)
    public ModelAndView editIngredientProcess(@ModelAttribute @Valid EditIngredientRequestModel editIngredientRequestModel,
                                              BindingResult bindingResult, @PathVariable String id, RedirectAttributes attributes) {
        if (!this.ingredientService.editIngredient(editIngredientRequestModel, attributes, bindingResult, id))
            return super.redirect(ADMIN_EDIT_INGREDIENTS_URL.concat(SLASH_STR.concat(id)));
        return super.redirectAndLog(ADMIN_ALL_INGREDIENTS_URL);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @GetMapping(DELETE_INGREDIENTS_URL)
    public ModelAndView deleteIngredient(@PathVariable String id) {
        return super.view(this.ingredientService.findById(id), ADMIN_PAGE_TITLE_MAP_ENTRY);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @PostMapping(DELETE_INGREDIENTS_URL)
    @LoggerAction(table = TableEnum.INGREDIENT, action = Action.DELETE)
    public ModelAndView deleteIngredientProcess(@PathVariable String id, RedirectAttributes attributes) {
        if (!this.ingredientService.deleteIngredient(id, attributes))
            return super.redirect(DELETE_INGREDIENTS_URL.concat(SLASH_STR.concat(id)));
        return super.redirectAndLog(ADMIN_ALL_INGREDIENTS_URL);
    }

    /*@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @GetMapping(ADD_INGREDIENTS_URL)
    public ModelAndView addIngredient(AddIngredientRequestModel addIngredientRequestModel) {
        return super.view(addIngredientRequestModel, Map.ofEntries(entry(PAGE_TITLE_STR, ADMIN_PANEL_PAGE_TITLE)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @PostMapping(ADD_INGREDIENTS_URL)
    @LoggerAction(table = TableEnum.INGREDIENT, action = Action.ADD)
    public ModelAndView addIngredientProcess(@ModelAttribute @Valid AddIngredientRequestModel addIngredientRequestModel,
                                             BindingResult bindingResult, RedirectAttributes attributes) {
        if (!this.ingredientService.addIngredient(addIngredientRequestModel, attributes, bindingResult))
            return super.redirect(ADMIN_ADD_INGREDIENTS_URL);
        return super.redirect(ADMIN_ALL_INGREDIENTS_URL);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @GetMapping(DELETE_PRODUCT_INGREDIENTS_URL)
    public ModelAndView deleteIngredients(@PathVariable String productName) {
        return super.view(this.ingredientService.findProductIngredients(productName), Map.ofEntries(
                entry(PAGE_TITLE_STR, ADMIN_PANEL_PAGE_TITLE),
                entry(PRODUCT_NAME_STR, productName)
        ));
    }
      @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @PostMapping(DELETE_PRODUCT_INGREDIENTS_URL)
    public ModelAndView deleteIngredientsProcess(@PathVariable String productName,
                                                 @ModelAttribute @Valid IngredientsRequestModelWrapper model,
                                                 BindingResult bindingResult,
                                                 RedirectAttributes attributes) {
        if (this.ingredientService.addIngredientsAndSetThemToProduct(productName, model, bindingResult, attributes))
            return super.redirect(ADMIN_SET_PRODUCT_INGREDIENTS_URL);
        return super.redirect(ADMIN_ALL_PRODUCTS_URL);
    }*/
}
