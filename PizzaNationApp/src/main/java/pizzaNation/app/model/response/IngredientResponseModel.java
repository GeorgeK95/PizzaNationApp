package pizzaNation.app.model.response;

import pizzaNation.app.model.enums.Unit;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by George-Lenovo on 03/04/2018.
 */
public class IngredientResponseModel {

    private String id;

    private String name;

    private String description;

    private Date date;

    private BigDecimal price;

    private Double quantity;

    private Unit unit;

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Double getQuantity() {
        return quantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }
}
