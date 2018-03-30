package pizzaNation.app.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.CART_URL;
import static pizzaNation.app.util.WebConstants.PAGE_TITLE_STR;
import static pizzaNation.app.util.WebConstants.SHOPPING_CART_PAGE_TITLE;

/**
 * Created by George-Lenovo on 15/03/2018.
 */
@Controller
public class CartController extends BaseController {

    @GetMapping(CART_URL)
    public ModelAndView cart() {
        return super.view(null,Map.ofEntries(entry(PAGE_TITLE_STR, SHOPPING_CART_PAGE_TITLE)));
    }
}
