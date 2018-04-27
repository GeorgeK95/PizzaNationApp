package pizzaNation.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pizzaNation.app.model.entity.Order;

/**
 * Created by George-Lenovo on 27/04/2018.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
}
