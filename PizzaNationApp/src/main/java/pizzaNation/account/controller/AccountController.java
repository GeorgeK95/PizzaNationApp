package pizzaNation.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.account.service.IAccountService;
import pizzaNation.app.contoller.BaseController;
import pizzaNation.app.service.contract.IOrderService;
import pizzaNation.user.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.*;

@Controller
public class AccountController extends BaseController {

    private final IUserService userService;

    private final IAccountService accountService;

    private final IOrderService orderService;

    @Autowired
    public AccountController(IUserService userService, IAccountService accountService, IOrderService orderService) {
        this.userService = userService;
        this.accountService = accountService;
        this.orderService = orderService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(ACCOUNT_URL)
    public ModelAndView account() {
        return super.view(null, Map.ofEntries(entry(PAGE_TITLE_STR, MY_PIZZA_NATION)));
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping(VERIFY_EMAIL)
    public ModelAndView confirm(HttpServletRequest request, RedirectAttributes attributes) {
        this.accountService.tryConfirmAccount(request.getQueryString(), attributes);

        return super.redirect(LOGIN_URL);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(ACCOUNT_ORDERS_URL)
    public ModelAndView orders() {
        return super.view(this.orderService.getUserOrders(), Map.ofEntries(entry(PAGE_TITLE_STR, MY_PIZZA_NATION)));
    }

}
