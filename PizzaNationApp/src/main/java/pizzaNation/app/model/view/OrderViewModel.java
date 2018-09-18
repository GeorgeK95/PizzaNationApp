package pizzaNation.app.model.view;

import pizzaNation.app.enums.OrderStatus;

import java.util.Comparator;
import java.util.Date;

public class OrderViewModel implements Comparator<OrderViewModel> {

    private Date date;

    private OrderStatus status;

    private UserViewModel user;

    public Date getDate() {
        return date;
    }

    public UserViewModel getUser() {
        return user;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setUser(UserViewModel user) {
        this.user = user;
    }

    @Override
    public int compare(OrderViewModel o1, OrderViewModel o2) {
        OrderStatus status1 = o1.getStatus();
        OrderStatus status2 = o2.getStatus();

        return Integer.compare(status1.toString().compareTo(status2.toString()), 0);
    }
}
