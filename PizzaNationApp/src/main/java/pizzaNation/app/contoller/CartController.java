package pizzaNation.app.contoller;

import com.google.gson.Gson;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pizzaNation.app.annotation.LoggerAction;
import pizzaNation.app.enums.Action;
import pizzaNation.app.enums.TableEnum;
import pizzaNation.app.model.entity.Product;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.*;
import static pizzaNation.app.util.WebConstants.FAILED_STR;
import static pizzaNation.app.util.WebConstants.SAVED_STR;

/**
 * Created by George-Lenovo on 15/03/2018.
 */
@Controller
public class CartController extends BaseController {

    /*
    jsessionid=123 <-> cartid=456
    cartid=456 <-> products
    */

    private Map<String, String> sessionCartData;

    private Map<String, Set<Product>> cartProductsSessionData;

    public CartController() {
        this.sessionCartData = new HashMap<>();
        this.cartProductsSessionData = new HashMap<>();
    }

    @GetMapping(CART_URL)
    public ModelAndView cart() {
        return super.view(null, Map.ofEntries(entry(PAGE_TITLE_STR, SHOPPING_CART_PAGE_TITLE)));
    }

    //will get here from ajax call
    @RequestMapping(value = CART_ADD_PRODUCT_URL, method = RequestMethod.POST/*, produces = APPLICATION_JSON_MIME*/)
    public @ResponseBody
    String haha(@RequestParam String name, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String cartId = UUID.randomUUID().toString();

        Optional<Cookie> jsessionid = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("JSESSIONID")).findFirst();

        if (jsessionid.isPresent())
            response.addCookie(new Cookie(CART_ID_STR, cartId));

        String sessionId = session.getId();
        this.sessionCartData.putIfAbsent(sessionId, cartId);

        Set<Product> products = new HashSet<>();

        if (this.cartProductsSessionData.containsKey(cartId)) {
            products = this.cartProductsSessionData.get(cartId);
        }

        products.add(new Product());

        this.cartProductsSessionData.put(cartId, products);

        return PRODUCT_ADDED_SUCCESSFULLY_MESSAGE;
    }
}
