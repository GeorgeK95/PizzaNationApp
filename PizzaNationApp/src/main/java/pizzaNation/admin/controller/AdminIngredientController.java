package pizzaNation.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.app.contoller.BaseController;
import pizzaNation.app.model.enums.Unit;
import pizzaNation.app.model.request.AddIngredientRequestModel;
import pizzaNation.app.model.request.EditIngredientRequestModel;
import pizzaNation.app.model.request.EditProductRequestModel;
import pizzaNation.app.service.IIngredientService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;
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

    @ModelAttribute(name = "unitsList")
    public List<Unit> getProducts() {
        return Arrays.asList(Unit.values());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @GetMapping(ALL_INGREDIENTS_URL)
    public ModelAndView allIngredients() {
        return super.view(this.ingredientService.findAllByDate(), Map.ofEntries(entry(PAGE_TITLE_STR, ADMIN_PANEL_PAGE_TITLE)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @GetMapping(ADD_INGREDIENTS_URL)
    public ModelAndView addIngredient(AddIngredientRequestModel addIngredientRequestModel) {
        return super.view(addIngredientRequestModel, Map.ofEntries(entry(PAGE_TITLE_STR, ADMIN_PANEL_PAGE_TITLE)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @PostMapping(ADD_INGREDIENTS_URL)
    public ModelAndView addIngredientProcess(@ModelAttribute @Valid AddIngredientRequestModel addIngredientRequestModel,
                                             BindingResult bindingResult, RedirectAttributes attributes) {
        if (!this.ingredientService.addIngredient(addIngredientRequestModel, attributes, bindingResult))
            return super.redirect(ADMIN_ADD_INGREDIENTS_URL);
        return super.redirect(ADMIN_ALL_INGREDIENTS_URL);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @GetMapping(EDIT_INGREDIENTS_URL)
    public ModelAndView editIngredient(@PathVariable String name) {
        return super.view(this.ingredientService.findByName(name), Map.ofEntries(entry(PAGE_TITLE_STR, ADMIN_PANEL_PAGE_TITLE)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @PostMapping(EDIT_INGREDIENTS_URL)
    public ModelAndView editIngredientProcess(@ModelAttribute @Valid EditIngredientRequestModel editIngredientRequestModel,
                                              BindingResult bindingResult, @PathVariable String name, RedirectAttributes attributes) {
        if (!this.ingredientService.editIngredient(editIngredientRequestModel, attributes, bindingResult, name))
            return super.redirect(ADMIN_EDIT_INGREDIENTS_URL.concat(SLASH_STR + name));
        return super.redirect(ADMIN_ALL_INGREDIENTS_URL);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @GetMapping(DELETE_INGREDIENTS_URL)
    public ModelAndView deleteIngredient(@PathVariable String name) {
        return super.view(this.ingredientService.findByName(name), Map.ofEntries(entry(PAGE_TITLE_STR, ADMIN_PANEL_PAGE_TITLE)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @PostMapping(DELETE_INGREDIENTS_URL)
    public ModelAndView deleteIngredientProcess(@PathVariable String name) {
        this.ingredientService.deleteIngredient(name);
        return super.redirect(ADMIN_ALL_INGREDIENTS_URL);
    }
}
