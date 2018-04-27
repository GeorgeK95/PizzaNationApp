package pizzaNation.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaNation.app.model.entity.Order;
import pizzaNation.app.repository.OrderRepository;
import pizzaNation.app.service.contract.IOrderService;

/**
 * Created by George-Lenovo on 27/04/2018.
 */
@Service
@Transactional
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public boolean makeOrder(Order order) {
        this.orderRepository.saveAndFlush(order);

        return true;
    }
}
