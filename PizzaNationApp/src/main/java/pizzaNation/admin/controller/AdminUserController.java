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
import pizzaNation.user.enumeration.Gender;
import pizzaNation.user.model.request.EditUserRequestModel;
import pizzaNation.user.service.api.IUserDeleteService;
import pizzaNation.user.service.api.IUserEditService;
import pizzaNation.user.service.api.IUserService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static pizzaNation.admin.controller.AdminController.ADMIN_PAGE_TITLE_MAP_ENTRY;
import static pizzaNation.app.util.WebConstants.*;

@Controller
@RequestMapping(ADMIN_URL)
public class AdminUserController extends BaseController {

    private final IUserService userService;
    private final IUserEditService userEditService;
    private final IUserDeleteService userDeleteService;

    @Autowired
    public AdminUserController(IUserService userService, IUserEditService userEditService, IUserDeleteService userDeleteService) {
        this.userService = userService;
        this.userEditService = userEditService;
        this.userDeleteService = userDeleteService;
    }

    @ModelAttribute(name = GENDERS_LIST)
    public List<String> getCreators() {
        return Arrays.stream(Gender.values()).map(Enum::toString).collect(Collectors.toList());
    }

    @PreAuthorize(HAS_ROLE_ROLE_ADMIN)
    @RequestMapping(method = RequestMethod.GET, value = {ALL_USERS_URL, USERS_URL})
    public ModelAndView allUsers() {
        return super.view(this.userService.findAll(), ADMIN_PAGE_TITLE_MAP_ENTRY);
    }

    @PreAuthorize(HAS_ROLE_ROLE_ADMIN)
    @GetMapping(EDIT_USERS_URL)
    public ModelAndView editUser(@PathVariable String id) {
        return super.view(this.userService.findById(id), ADMIN_PAGE_TITLE_MAP_ENTRY);
    }

    @PreAuthorize(HAS_ROLE_ROLE_ADMIN)
    @PostMapping(EDIT_USERS_URL)
    @LoggerAction(table = TableEnum.USER, action = Action.EDIT)
    public ModelAndView editUserProcess(@PathVariable String id, @ModelAttribute @Valid EditUserRequestModel requestModel,
                                        BindingResult bindingResult, RedirectAttributes attributes) {
        if (!this.userEditService.editUser(id, requestModel, bindingResult, attributes))
            return super.redirect(ADMIN_EDIT_USERS_URL);
        return super.redirectAndLog(ADMIN_ALL_USERS_URL);
    }

    @PreAuthorize(HAS_ROLE_ROLE_ADMIN)
    @GetMapping(DELETE_USERS_URL)
    public ModelAndView deleteUser(@PathVariable String id) {
        return super.view(this.userService.findById(id), ADMIN_PAGE_TITLE_MAP_ENTRY);
    }

    @PreAuthorize(HAS_ROLE_ROLE_ADMIN)
    @PostMapping(DELETE_USERS_URL)
    @LoggerAction(table = TableEnum.USER, action = Action.DELETE)
    public ModelAndView deleteUserProcess(@PathVariable String id) {
        if (!this.userDeleteService.disableUser(id))
            return super.redirectAndLog(DELETE_USERS_URL.concat(id));
        return super.redirectAndLog(ADMIN_ALL_USERS_URL);
    }
}
