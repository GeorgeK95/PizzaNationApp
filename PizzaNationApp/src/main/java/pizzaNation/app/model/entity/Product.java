package pizzaNation.app.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by George-Lenovo on 01/04/2018.
 */
@Entity
public class Product {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(unique = true, nullable = false, length = 50)
    private String name;

    private String details;

    @ManyToMany(mappedBy = "products")
    private Set<Menu> menus;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Ingredient> ingredients;

    @Column(nullable = false)
    private Date date;

    public Product() {
        this.menus = new HashSet<>();
        this.date = new Date();
    }

    public Product(String name, String details) {
        this();
        this.name = name;
        this.details = details;
    }

    public void addMenu(Menu menu) {
        this.menus.add(menu);
    }

    public String getId() {
        return id;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
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

    public void setDetails(String details) {
        this.details = details;
    }

    public void setMenus(Set<Menu> menu) {
        this.menus = menu;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
