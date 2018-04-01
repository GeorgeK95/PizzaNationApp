package pizzaNation.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaNation.app.model.entity.Product;
import pizzaNation.app.model.response.ProductViewModel;

import java.util.List;
import java.util.Set;

/**
 * Created by George-Lenovo on 01/04/2018.
 */

public interface IProductService {

    List<ProductViewModel> findAll();

    Set<Product> getAllByIds(String[] productIds);
}
