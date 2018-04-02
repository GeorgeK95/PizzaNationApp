package pizzaNation.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pizzaNation.app.contoller.BaseController;
import pizzaNation.app.model.response.ProductResponseModel;
import pizzaNation.app.model.view.MenuViewModel;
import pizzaNation.app.model.view.ProductViewModel;
import pizzaNation.app.service.IProductService;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.*;

/**
 * Created by George-Lenovo on 02/04/2018.
 */
@Controller
@RequestMapping(ADMIN_URL)
public class AdminProductController extends BaseController {

    private final IProductService productService;

    @Autowired
    public AdminProductController(IProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @GetMapping(ALL_PRODUCTS_URL)
    public ModelAndView allProducts() {
        return super.view(this.productService.findAllByDate(), Map.ofEntries(entry(PAGE_TITLE_STR, ADMIN_PANEL_PAGE_TITLE)));
    }
}
