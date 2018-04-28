package pizzaNation.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pizzaNation.app.model.entity.Menu;
import pizzaNation.app.model.entity.Product;
import pizzaNation.app.model.request.EditProductRequestModel;

import java.util.Date;
import java.util.Set;

/**
 * Created by George-Lenovo on 01/04/2018.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    //    @Query("select p from Product p inner join Menu m on m.id = p.menues where m.name like :name order by m.date")
    @Query("select m.products from Menu m where m.name like :name")
    Set<Product> getMenuProducts(@Param("name") String menuName);

    @Query("select p from Product p order by p.date desc")
    Set<Product> findAllOrderByDate();

    Set<Product> findAllByIdIn(String[] productIds);

    Set<Product> findAllByNameIn(Set<String> names);

    boolean existsByName(String name);

    Product findByName(String name);

    @Query("select p from Product p order by p.totalSales desc")
    Set<Product> getBestSeller();

    @Query("select p from Product p where p.isPromotional = TRUE")
    Set<Product> getPromotionalProducts();
}
