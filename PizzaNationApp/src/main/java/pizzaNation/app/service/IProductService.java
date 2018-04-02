package pizzaNation.app.service;

import pizzaNation.app.model.entity.Product;
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

    void saveAll(Set<Product> allByIds);

    Set<Product> findAllByMenuName(String name);

}
