package pizzaNation.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pizzaNation.app.model.entity.Product;

import java.util.Set;

/**
 * Created by George-Lenovo on 01/04/2018.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("select p from Product p order by p.id")
    Set<Product> findAllOrderById();

    Set<Product> findAllByIdIn(String[] productIds);
}
