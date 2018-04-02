package pizzaNation.app.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by George-Lenovo on 01/04/2018.
 */
@Entity
public class Menu {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @NotNull
    @Column(unique = true, nullable = false, length = 20)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "menu_product",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private Set<Product> products;

    private Integer priority;

    @Column(nullable = true)//TODO:refactor
    private String imagePath;

    @Column(nullable = false)
    private Date date;

    public Menu() {
        this.date = new Date();
        this.products = new HashSet<>();
    }

    public Menu(String name, String description, Integer priority, String imagePath) {
        this();
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.imagePath = imagePath;
    }

    public void addProducts(Set<Product> products) {
        this.products.addAll(products);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Integer getPriority() {
        return priority;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Date getDate() {
        return date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
