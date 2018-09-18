package pizzaNation.app.contoller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pizzaNation.app.model.view.ProductDetailsViewModel;
import pizzaNation.app.service.contract.IProductService;

import javax.servlet.http.HttpServletRequest;

import static pizzaNation.app.util.WebConstants.APPLICATION_JSON_MIME;
import static pizzaNation.app.util.WebConstants.PRODUCT_DETAILS_URL;

@Controller
public class ProductController extends BaseController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = PRODUCT_DETAILS_URL, method = RequestMethod.GET, produces = APPLICATION_JSON_MIME)
    public @ResponseBody
    String addStoresProcess(HttpServletRequest request) {
        String productName = super.escapeHTMLEncoding(request.getQueryString());
        ProductDetailsViewModel productDetails = this.productService.findByName(productName, ProductDetailsViewModel.class);
        return new Gson().toJson(productDetails);
    }
}
