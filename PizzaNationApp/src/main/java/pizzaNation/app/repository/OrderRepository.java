package pizzaNation.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pizzaNation.app.model.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    @Query("select o from Order o order by o.date desc")
    Order findLastByDate();
}
