package pizzaNation.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.app.contoller.BaseController;
import pizzaNation.app.model.request.EditDetailsRequestModel;
import pizzaNation.app.model.request.EditSignInRequestModel;
import pizzaNation.user.enumeration.Gender;
import pizzaNation.user.service.api.IUserEditService;
import pizzaNation.user.service.api.IUserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.*;

@Controller
public class AccountSettingsController extends BaseController {

    private final IUserService userService;
    private final IUserEditService userEditService;

    @Autowired
    public AccountSettingsController(IUserService userService, IUserEditService userEditService) {
        this.userService = userService;
        this.userEditService = userEditService;
    }

    @ModelAttribute(name = GENDERS_LIST)
    public List<String> getCreators() {
        return Arrays.stream(Gender.values()).map(Enum::toString).collect(Collectors.toList());
    }

    @PreAuthorize(IS_AUTHENTICATED)
    @GetMapping(ACCOUNT_SETTINGS_URL)
    public ModelAndView settings() {
        return super.view(null, Map.ofEntries(entry(PAGE_TITLE_STR, MY_ACCOUNT_PAGE_TITLE)));
    }

    @PreAuthorize(IS_AUTHENTICATED)
    @GetMapping(ACCOUNT_SETTINGS_EMAIL_URL)
    public ModelAndView signIn(Principal principal) {
        return super.view(this.userService.constructEditEmailModel(principal), Map.ofEntries(entry(PAGE_TITLE_STR, MY_PIZZA_NATION)));
    }

    @PreAuthorize(IS_AUTHENTICATED)
    @PostMapping(ACCOUNT_SETTINGS_EMAIL_URL)
    public ModelAndView signInProcess(@ModelAttribute @Valid EditSignInRequestModel editSignInRequestModel, BindingResult result,
                                      RedirectAttributes attributes, Principal principal) {
        if (!this.userEditService.editSignInInfo(editSignInRequestModel, attributes, result, principal))
            return super.redirect(ACCOUNT_SETTINGS_EMAIL_URL);
        return super.redirect(LOGOUT_URL);
    }

    @PreAuthorize(IS_AUTHENTICATED)
    @GetMapping(ACCOUNT_SETTINGS_DETAILS_URL)
    public ModelAndView details() {
        return super.view(this.userService.constructEditDetailsModel(), Map.ofEntries(entry(PAGE_TITLE_STR, MY_PIZZA_NATION)));
    }

    @PreAuthorize(IS_AUTHENTICATED)
    @PostMapping(ACCOUNT_SETTINGS_DETAILS_URL)
    public ModelAndView detailsProcess(@ModelAttribute @Valid EditDetailsRequestModel requestModel, BindingResult result,
                                       RedirectAttributes attributes, Principal principal) {
        if (!this.userEditService.editUserDetails(principal.getName(), requestModel, attributes, result))
            return super.redirect(ACCOUNT_SETTINGS_DETAILS_URL);
        return super.redirect(ACCOUNT_URL);
    }
}
