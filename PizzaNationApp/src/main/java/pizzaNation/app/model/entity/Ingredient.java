package pizzaNation.app.model.entity;

import org.hibernate.annotations.GenericGenerator;
import pizzaNation.app.enums.Unit;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static pizzaNation.app.util.WebConstants.INGREDIENT_QUANTITY_LIMIT;

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

    @NotNull
    @Column(nullable = false, length = 50)
    private String name;

    @Min(0)
    @Max(INGREDIENT_QUANTITY_LIMIT)
    @Column(nullable = false)
    private Double quantity;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Unit unit;

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private Date date;

    public Ingredient() {
        this.date = new Date();
    }

    public Ingredient(Double quantity, Unit unit, String name) {
        this();
        this.quantity = quantity;
        this.unit = unit;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public Double getQuantity() {
        return quantity;
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

    public Product getProduct() {
        return product;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setQuantity(Double kilograms) {
        this.quantity = kilograms;
    }

    public void setProduct(Product product) {
        this.product = product;
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
