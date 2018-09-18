package pizzaNation.app.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pizzaNation.app.annotation.LoggerAction;
import pizzaNation.app.enums.Action;
import pizzaNation.app.enums.TableEnum;
import pizzaNation.app.service.ICartService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.*;

@Controller
public class CartController extends BaseController {

    private final ICartService cartService;

    @Autowired
    public CartController(ICartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(CART_URL)
    public ModelAndView cart(HttpServletRequest request) {
        if (!this.cartService.validateCartRequest(request))
            return super.view(null, Map.ofEntries(entry(PAGE_TITLE_STR, SHOPPING_CART_PAGE_TITLE)));

        return super.view(this.cartService.getProducts(request), Map.ofEntries(entry(PAGE_TITLE_STR, SHOPPING_CART_PAGE_TITLE)));
    }

    @LoggerAction(action = Action.PRODUCT_ORDERED, table = TableEnum.ORDER)
    @RequestMapping(value = CART_ADD_PRODUCT_URL, method = RequestMethod.POST, produces = APPLICATION_JSON_MIME)
    public @ResponseBody
    String addProduct(@RequestParam String productName, HttpServletRequest request, HttpServletResponse response) {
        if (!this.cartService.addProduct(productName, request, response))
            return PRODUCT_ADD_FAILED_MESSAGE;

        return String.format("{\"cartSize\": %d}", this.cartService.getCartSize(request));
    }

    @RequestMapping(value = CART_REMOVE_PRODUCT_URL, method = RequestMethod.POST, produces = APPLICATION_JSON_MIME)
    public @ResponseBody
    String removeProduct(@RequestParam String productName, HttpServletRequest request, HttpServletResponse response) {
        if (!this.cartService.removeProduct(productName, request, response))
            return PRODUCT_REMOVE_FAILED_MESSAGE;

        return this.cartService.getProductsAsJson(request);
    }

    @RequestMapping(value = CART_PRODUCTS_SIZE_URL, method = RequestMethod.GET/*, produces = APPLICATION_JSON_MIME*/)
    public @ResponseBody
    String getCartSize(HttpServletRequest request) {
        return String.valueOf(this.cartService.getCartSize(request));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(CART_CHECKOUT_URL)
    public ModelAndView checkout(HttpServletRequest request) {
        if (this.cartService.prepareOrder(request) != null)
            return super.view(this.cartService.prepareOrder(request), Map.ofEntries(entry(PAGE_TITLE_STR, SHOPPING_CART_PAGE_TITLE)));
        return super.redirect(MENU_URL);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(CART_CHECKOUT_URL)
    @LoggerAction(action = Action.PRODUCT_ORDERED, table = TableEnum.ORDER)
    public ModelAndView checkoutProcess(HttpServletRequest request) {
        this.cartService.confirmOrder(request);

        return super.redirectAndLog(SLASH_STR);
    }

}
