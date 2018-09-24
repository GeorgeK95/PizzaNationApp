package pizzaNation.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaNation.admin.repository.MenuRepository;
import pizzaNation.app.exception.ProductNotFoundException;
import pizzaNation.app.model.entity.Menu;
import pizzaNation.app.model.entity.Product;
import pizzaNation.app.repository.ProductRepository;
import pizzaNation.app.service.api.IProductDeleteService;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class ProductDeleteService implements IProductDeleteService {

    private final ProductRepository productRepository;

    private final MenuRepository menuRepository;

    @Autowired
    public ProductDeleteService(ProductRepository productRepository, MenuRepository menuRepository) {
        this.productRepository = productRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public boolean deleteProduct(String name) {
        Product product = this.productRepository.findByName(name);

        if (product == null) throw new ProductNotFoundException();

//        product.setIngredients(null); //release ingredients so they wont be deleted

        this.clearProductFromItsMenus(product);

        this.productRepository.delete(product);

        return true;
    }

    private void clearProductFromItsMenus(Product product) {
        Set<Product> newSet = new HashSet<>();

        for (Menu menu : product.getMenus()) {
            for (Product curr : menu.getProducts()) {
                if (!curr.getName().equals(product.getName()))
                    newSet.add(curr);
            }

            menu.setProducts(newSet);
//            this.menuService.persistMenuEntity(menu);
            this.menuRepository.save(menu);
            newSet = new HashSet<>();
        }
    }
}
