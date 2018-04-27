package pizzaNation.app.service.contract;

import pizzaNation.app.model.entity.Order;

/**
 * Created by George-Lenovo on 27/04/2018.
 */
public interface IOrderService {

    boolean makeOrder(Order order);
}
