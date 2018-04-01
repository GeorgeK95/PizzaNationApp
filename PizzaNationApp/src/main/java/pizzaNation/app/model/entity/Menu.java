package pizzaNation.app.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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

    @OneToMany(mappedBy = "menu", fetch = FetchType.EAGER)
    private Set<Product> products;

    private Integer priority;

    @Column(nullable = true)//TODO:refactor
    private String imagePath;

    public Menu() {
    }

    public Menu(@NotNull String name, @NotNull String description, Set<Product> products, Integer priority, String imagePath) {
        this.name = name;
        this.description = description;
        this.products = products;
        this.priority = priority;
        this.imagePath = imagePath;
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
}
