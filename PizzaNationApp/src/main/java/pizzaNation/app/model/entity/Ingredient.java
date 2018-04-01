package pizzaNation.app.model.entity;

import org.hibernate.annotations.GenericGenerator;
import pizzaNation.app.model.enums.Unit;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
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
    private BigInteger price;

    @NotNull
    @Column(nullable = false)
    private String description;

    @ManyToMany
    @JoinTable(name = "ingredient_product",
            joinColumns = @JoinColumn(name = "ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> ingredients;

    public String getId() {
        return id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public BigInteger getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Set<Product> getIngredients() {
        return ingredients;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setQuantity(Double kilograms) {
        this.quantity = kilograms;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIngredients(Set<Product> ingredients) {
        this.ingredients = ingredients;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
