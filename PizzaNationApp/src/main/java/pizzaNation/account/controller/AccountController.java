package pizzaNation.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.account.service.IAccountService;
import pizzaNation.app.contoller.BaseController;
import pizzaNation.user.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.*;

/**
 * Created by George-Lenovo on 06/04/2018.
 */
@Controller
public class AccountController extends BaseController {

    private final IUserService userService;

    private final IAccountService accountService;

    @Autowired
    public AccountController(IUserService userService, IAccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(ACCOUNT_URL)
    public ModelAndView account() {
        return super.view(null, Map.ofEntries(entry(PAGE_TITLE_STR, MY_PIZZA_NATION)));
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping(CONFIRM_URL)
    public ModelAndView confirm(HttpServletRequest request, RedirectAttributes attributes) {
        this.accountService.tryConfirmAccount(request.getQueryString(), attributes);

        return super.redirect(LOGIN_URL);
//        return super.view(model, Map.ofEntries(entry(PAGE_TITLE_STR, CONFIRM_PAGE_TITLE)));
    }

    /*@PreAuthorize("isAnonymous()")
    @PostMapping(CONFIRM_URL)
    public ModelAndView confirmProcess(ConfirmAccountRequestModel model, RedirectAttributes attributes) {
        if (!this.userService.confirmAccount(model.getToken(), attributes))
            return super.redirect(CONFIRM_URL);
        return super.redirect(LOGIN_URL);
    }*/

    /*@PreAuthorize("isAnonymous()")
    @GetMapping("/test")
    public ModelAndView confirm(HttpServletRequest request, RedirectAttributes attributes) {
        this.accountService.tryConfirmAccount(request.getQueryString(), attributes);

        return super.redirect(LOGIN_URL);
    }*/

}
