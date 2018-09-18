package pizzaNation.app.service.contract;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.app.model.entity.Product;
import pizzaNation.app.model.request.AddProductRequestModel;
import pizzaNation.app.model.request.EditProductRequestModel;
import pizzaNation.app.model.response.ProductResponseModel;
import pizzaNation.app.model.view.HomeViewModel;
import pizzaNation.app.model.view.MenuProductsViewModel;
import pizzaNation.app.model.view.ProductViewModel;

import java.util.List;
import java.util.Set;

public interface IProductService {

    HomeViewModel constructHomeModel();

    List<ProductViewModel> findAll();

    List<ProductResponseModel> findAllByDate();

    Set<Product> getAllByIds(String[] productIds);

    boolean addProduct(AddProductRequestModel addProductRequestModel, RedirectAttributes attributes, BindingResult bindingResult);

    boolean persistProduct(AddProductRequestModel addProductRequestModel);

    <T> T findByName(String name, Class<T> clazz);

    boolean editProduct(EditProductRequestModel editProductRequestModel, RedirectAttributes attributes, BindingResult bindingResult, String name);

    boolean deleteProduct(String name);

    List<MenuProductsViewModel> getMenuProducts(String menuName);
}
