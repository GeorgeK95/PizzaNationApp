package pizzaNation.app.model.response;

import pizzaNation.app.enums.Unit;
import pizzaNation.app.model.view.ProductViewModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by George-Lenovo on 03/04/2018.
 */
public class IngredientResponseModel {

    private String id;

    private String name;

    private Date date;

    private Double quantity;

    private Unit unit;

    private ProductViewModel product;

    public Date getDate() {
        return date;
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

    public ProductViewModel getProduct() {
        return product;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public void setProduct(ProductViewModel product) {
        this.product = product;
    }
}
