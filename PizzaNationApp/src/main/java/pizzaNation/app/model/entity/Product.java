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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_ingredient",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredients;

    private int totalSales;

    private Boolean isPromotional;

    @Column(nullable = true)//TODO:refactor
    private String imagePath;

    @Column(nullable = false)
    private Date date;

    public Product() {
        this.menus = new HashSet<>();
        this.date = new Date();
    }

    public Product(String name, String details, int totalSales, boolean isPromotional) {
        this();
        this.name = name;
        this.details = details;
        this.totalSales = totalSales;
        this.isPromotional = isPromotional;
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

    public int getTotalSales() {
        return totalSales;
    }

    public Boolean getPromotional() {
        return isPromotional;
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

    public void setDetails(String details) {
        this.details = details;
    }

    public void setMenus(Set<Menu> menu) {
        this.menus = menu;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    public void setPromotional(Boolean promotional) {
        isPromotional = promotional;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
