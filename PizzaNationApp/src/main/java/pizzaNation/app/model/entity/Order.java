package pizzaNation.app.model.entity;

import org.hibernate.annotations.GenericGenerator;
import pizzaNation.app.enums.OrderStatus;
import pizzaNation.user.model.entity.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "order_tbl")
public class Order {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @ManyToMany
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private Set<Product> products;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private Date date;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    public Order() {
        this.date = new Date();
    }

    public Order(Set<Product> products, User user, OrderStatus status) {
        this();
        this.products = products;
        this.user = user;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Date getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
