package pizzaNation.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaNation.app.config.PizzaNationSecurityConfiguration;
import pizzaNation.app.model.entity.Order;
import pizzaNation.app.model.view.OrderViewModel;
import pizzaNation.app.repository.OrderRepository;
import pizzaNation.app.service.contract.IOrderService;
import pizzaNation.app.util.DTOConverter;
import pizzaNation.user.model.entity.User;
import pizzaNation.user.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by George-Lenovo on 27/04/2018.
 */
@Service
@Transactional
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean persistOrder(Order order) {
        this.orderRepository.saveAndFlush(order);

        return true;
    }

    @Override
    public List<OrderViewModel> getUserOrders() {
        User user = this.userRepository.findByEmail(PizzaNationSecurityConfiguration.getCurrentlyLoggedInUserEmail());
        return this.convertUserOrders(user.getOrders());
    }

    @Override
    public List<Order> findAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public boolean save(Order order) {
        this.orderRepository.saveAndFlush(order);

        return true;
    }

    @Override
    public OrderViewModel getLastOrder() {
        return DTOConverter.convert(this.orderRepository.findLastByDate(), OrderViewModel.class);
    }

    private List<OrderViewModel> convertUserOrders(Set<Order> orders) {
        List<OrderViewModel> ordersView = new ArrayList<>();
        for (Order order : orders) {
            ordersView.add(DTOConverter.convert(order, OrderViewModel.class));
        }

//        Comparator<OrderViewModel> ordersComparator = Comparator.comparing(OrderViewModel::getStatus);
        Comparator<OrderViewModel> ordersComparator = (o1, o2) -> {
            int dateCmp = o1.getStatus().compareTo(o2.getStatus());
            if (dateCmp == 0)
                return o2.getDate().compareTo(o1.getDate());
            return dateCmp;
        };


        Collections.sort(ordersView, ordersComparator);

        return ordersView;
    }
}
