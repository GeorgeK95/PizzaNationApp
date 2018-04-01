package pizzaNation.app.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import pizzaNation.app.model.entity.Product;
import pizzaNation.app.model.response.ProductViewModel;
import pizzaNation.app.service.IProductService;
import pizzaNation.app.service.ProductService;
import pizzaNation.app.util.DTOConverter;

import java.util.List;

/**
 * Created by George-Lenovo on 01/04/2018.
 */
@Controller
public class ProductController extends BaseController {
}
