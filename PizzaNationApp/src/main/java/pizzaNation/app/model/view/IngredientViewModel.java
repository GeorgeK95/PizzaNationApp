package pizzaNation.app.model.view;

import pizzaNation.app.enums.Unit;

import java.util.Date;

/**
 * Created by George-Lenovo on 23/04/2018.
 */
public class IngredientViewModel {

    private String name;

    private Double quantity;

    private Unit unit;

    public String getName() {
        return name;
    }

    public Double getQuantity() {
        return quantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
