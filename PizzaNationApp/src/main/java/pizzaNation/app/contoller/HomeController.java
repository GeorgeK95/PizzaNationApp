package pizzaNation.app.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pizzaNation.app.service.api.IProductService;

import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.*;

@Controller
public class HomeController extends BaseController {

    private final IProductService productService;

    @Autowired
    public HomeController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping(SLASH_STR)
    public ModelAndView home() {
        return super.view(this.productService.constructHomeModel(), Map.ofEntries(entry(PAGE_TITLE_STR, PIZZA_NATION_PAGE_TITLE)));
    }
}
