package pizzaNation.app.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import pizzaNation.app.service.contract.IProductService;

import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.MENU_NAME_URL;
import static pizzaNation.app.util.WebConstants.MENU_PAGE_TITLE;
import static pizzaNation.app.util.WebConstants.PAGE_TITLE_STR;

/**
 * Created by George-Lenovo on 01/04/2018.
 */
@Controller
public class ProductController extends BaseController {
}
