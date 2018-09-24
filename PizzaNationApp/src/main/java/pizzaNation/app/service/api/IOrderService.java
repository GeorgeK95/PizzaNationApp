package pizzaNation.app.service.api;

import pizzaNation.app.model.entity.Order;
import pizzaNation.app.model.view.OrderViewModel;

import java.util.List;

public interface IOrderService {

    boolean persistOrder(Order order);

    List<OrderViewModel> getUserOrders();

    List<Order> findAll();

    boolean save(Order order);

}
