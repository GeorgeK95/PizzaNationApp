package pizzaNation.app.model.entity;

import org.hibernate.annotations.GenericGenerator;
import pizzaNation.app.enums.Unit;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by George-Lenovo on 01/04/2018.
 */
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Min(0)
    @Column(nullable = false)
    private Double quantity;

    @Enumerated(value = EnumType.STRING)
    private Unit unit;

    @Min(1)
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull
    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String description;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Product> products;

    @Column(nullable = false)
    private Date date;

    public Ingredient() {
        this.products = new HashSet<>();
        this.date = new Date();
    }

    public Ingredient(Double quantity, Unit unit, BigDecimal price, String name, String description) {
        this();
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Unit getUnit() {
        return unit;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setQuantity(Double kilograms) {
        this.quantity = kilograms;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProducts(Set<Product> ingredients) {
        this.products = ingredients;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }
}
