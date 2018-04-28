package pizzaNation.app.service.contract;

import pizzaNation.app.model.entity.Ingredient;
import pizzaNation.app.model.entity.Order;
import pizzaNation.app.model.view.OrderViewModel;

import java.util.List;
import java.util.Set;

/**
 * Created by George-Lenovo on 27/04/2018.
 */
public interface IOrderService {

    boolean persistOrder(Order order);

    List<OrderViewModel> getUserOrders();

    List<Order> findAll();

    boolean save(Order order);

    OrderViewModel getLastOrder();
}
