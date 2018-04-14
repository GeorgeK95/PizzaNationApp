package pizzaNation.app.model.request;

import pizzaNation.app.enums.Unit;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static pizzaNation.app.util.WebConstants.INVALID_INGREDIENT_NAME_MESSAGE;

/**
 * Created by George-Lenovo on 14/04/2018.
 */
public class IngredientsRequestModel {

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
