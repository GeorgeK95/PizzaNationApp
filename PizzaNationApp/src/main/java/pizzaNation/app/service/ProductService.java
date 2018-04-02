package pizzaNation.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaNation.app.model.entity.Product;
import pizzaNation.app.model.response.ProductResponseModel;
import pizzaNation.app.model.view.ProductViewModel;
import pizzaNation.app.repository.ProductRepository;
import pizzaNation.app.util.DTOConverter;

import java.util.List;
import java.util.Set;

/**
 * Created by George-Lenovo on 01/04/2018.
 */
@Service
@Transactional
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productService) {
        this.productRepository = productService;
    }

    @Override
    public List<ProductViewModel> findAll() {
        return DTOConverter.convert(this.productRepository.findAllOrderByDate(), ProductViewModel.class);
    }

    @Override
    public List<ProductResponseModel> findAllByDate() {
        return DTOConverter.convert(this.productRepository.findAllOrderByDate(), ProductResponseModel.class);
    }

    @Override
    public Set<Product> getAllByIds(String[] productIds) {
        return this.productRepository.findAllByIdIn(productIds);
    }

    @Override
    public void saveAll(Set<Product> allByIds) {
        this.productRepository.saveAll(allByIds);
    }

    @Override
    public Set<Product> findAllByMenuName(String name) {
        return this.productRepository.findAllByMenuNameOrderByDate(name);
    }
}
