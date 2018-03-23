package pizzaNation.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static java.util.Map.entry;

/**
 * Created by George-Lenovo on 15/03/2018.
 */
@Controller
public class CartController extends BaseController {

    @GetMapping("/cart")
    public ModelAndView cart() {
        return super.view(null,Map.ofEntries(entry("pageTitle", "Shopping Cart")));
    }
}
