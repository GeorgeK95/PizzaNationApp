package pizzaNation.app.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Ingredient> ingredients;

    public Product() {
    }

    public Product(String name, String details, Menu menu) {
        this.name = name;
        this.details = details;
        this.menu = menu;
    }

    public String getId() {
        return id;
    }

    public Menu getMenu() {
        return menu;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
