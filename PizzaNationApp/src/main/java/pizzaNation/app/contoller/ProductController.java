package pizzaNation.app.contoller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pizzaNation.app.model.request.EditProductRequestModel;
import pizzaNation.app.model.view.ProductDetailsViewModel;
import pizzaNation.app.service.contract.IProductService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by George-Lenovo on 01/04/2018.
 */
@Controller
public class ProductController extends BaseController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/productDetails", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String addStoresProcess(HttpServletRequest request) {
        String productName = super.escapeHTMLEncoding(request.getQueryString());
        ProductDetailsViewModel productDetails = this.productService.findByName(productName, ProductDetailsViewModel.class);
        return new Gson().toJson(productDetails);
    }
}
