package pizzaNation.app.service.contract;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.app.model.entity.Product;
import pizzaNation.app.model.request.AddProductRequestModel;
import pizzaNation.app.model.request.EditProductRequestModel;
import pizzaNation.app.model.response.ProductResponseModel;
import pizzaNation.app.model.view.ProductViewModel;

import java.util.List;
import java.util.Set;

/**
 * Created by George-Lenovo on 01/04/2018.
 */

public interface IProductService {

    List<ProductViewModel> findAll();

    List<ProductResponseModel> findAllByDate();

    Set<Product> getAllByIds(String[] productIds);

//    void saveAll(Set<Product> allByIds);

    Set<Product> findAllByMenuName(String name);

    boolean addProduct(AddProductRequestModel addProductRequestModel, RedirectAttributes attributes, BindingResult bindingResult);

    boolean persistProduct(AddProductRequestModel addProductRequestModel);

    EditProductRequestModel findByName(String name);

    boolean editProduct(EditProductRequestModel editProductRequestModel, RedirectAttributes attributes, BindingResult bindingResult, String name);

    boolean deleteProduct(String name);
}
