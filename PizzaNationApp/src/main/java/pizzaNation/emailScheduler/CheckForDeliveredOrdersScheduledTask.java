
package pizzaNation.emailScheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pizzaNation.app.enums.OrderStatus;
import pizzaNation.app.model.entity.Ingredient;
import pizzaNation.app.model.entity.Order;
import pizzaNation.app.service.contract.IOrderService;
import pizzaNation.user.service.IUserService;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static pizzaNation.app.util.WebConstants.CHECK_FOR_DELIVERED_ORDERS_SCHEDULER_FIXED_RATE;
import static pizzaNation.app.util.WebConstants.EMAIL_SCHEDULER_FIXED_RATE;

/**
 * Created by George-Lenovo on 17/04/2018.
 */

@Component
public class CheckForDeliveredOrdersScheduledTask {

    private final IOrderService orderService;

    public CheckForDeliveredOrdersScheduledTask(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(fixedRate = CHECK_FOR_DELIVERED_ORDERS_SCHEDULER_FIXED_RATE)
    public void reportCurrentTime() {
        List<Order> orders = this.orderService.findAll();
        Date now = new Date();

        for (Order order : orders) {
            long diffInMillies = Math.abs(now.getTime() - order.getDate().getTime());
            long diff = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);

            if (diff >= 1) {
                order.setStatus(OrderStatus.DELIVERED);
                this.orderService.save(order);
            }
        }
    }
}

