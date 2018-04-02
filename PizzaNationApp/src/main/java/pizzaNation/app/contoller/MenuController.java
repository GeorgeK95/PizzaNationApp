package pizzaNation.app.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import pizzaNation.admin.service.IMenuService;
import pizzaNation.app.service.IProductService;

import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.*;

/**
 * Created by George-Lenovo on 15/03/2018.
 */
@Controller
public class MenuController extends BaseController {

    private final IMenuService menuService;

    private final IProductService productService;

    @Autowired
    public MenuController(IMenuService menuService, IProductService productService) {
        this.menuService = menuService;
        this.productService = productService;
    }

    @GetMapping(MENU_URL)
    public ModelAndView menu() {
        return super.view(null, Map.ofEntries(entry(PAGE_TITLE_STR, MENU_PAGE_TITLE)));
    }

    @GetMapping(MENU_NAME_URL)
    public ModelAndView menuDetails(@PathVariable String name) {
        return super.view(this.productService.findAllByMenuName(name), Map.ofEntries(entry(PAGE_TITLE_STR, MENU_PAGE_TITLE)));
    }
}
