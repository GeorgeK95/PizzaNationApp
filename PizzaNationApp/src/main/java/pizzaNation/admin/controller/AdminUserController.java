package pizzaNation.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.app.contoller.BaseController;
import pizzaNation.user.enumeration.Gender;
import pizzaNation.user.model.request.EditUserRequestModel;
import pizzaNation.user.service.IUserService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.*;

/**
 * Created by George-Lenovo on 02/04/2018.
 */
@Controller
@RequestMapping(ADMIN_URL)
public class AdminUserController extends BaseController {

    private final IUserService userService;

    @Autowired
    public AdminUserController(IUserService userService) {
        this.userService = userService;
    }

    @ModelAttribute(name = GENDERS_LIST)
    public List<String> getCreators() {
        return Arrays.stream(Gender.values()).map(Enum::toString).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @GetMapping(ALL_USERS_URL)
    @RequestMapping(method = RequestMethod.GET, value = {ALL_USERS_URL, USERS_URL})
    public ModelAndView allUsers() {
        return super.view(this.userService.findAll(), Map.ofEntries(entry(PAGE_TITLE_STR, ADMIN_PANEL_PAGE_TITLE)));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(EDIT_USERS_URL)
    public ModelAndView editUser(@PathVariable String id) {
        return super.view(this.userService.findById(id), Map.ofEntries(entry(PAGE_TITLE_STR, ADMIN_PANEL_PAGE_TITLE)));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(EDIT_USERS_URL)
    public ModelAndView editUserProcess(@PathVariable String id, @ModelAttribute @Valid EditUserRequestModel requestModel,
                                        BindingResult bindingResult, RedirectAttributes attributes) {
        if (!this.userService.editUser(id, requestModel, bindingResult, attributes))
            return super.redirect(ADMIN_EDIT_USERS_URL);
        return super.redirect(ADMIN_ALL_USERS_URL);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(DELETE_USERS_URL)
    public ModelAndView deleteUser(@PathVariable String id) {
        return super.view(this.userService.findById(id), Map.ofEntries(entry(PAGE_TITLE_STR, ADMIN_PANEL_PAGE_TITLE)));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(DELETE_USERS_URL)
    public ModelAndView deleteUserProcess(@PathVariable String id) {
        this.userService.deleteUser(id);
        return super.redirect(ADMIN_ALL_USERS_URL);
    }
}
