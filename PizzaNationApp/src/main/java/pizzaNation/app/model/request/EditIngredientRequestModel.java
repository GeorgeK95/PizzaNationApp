package pizzaNation.app.model.request;

import pizzaNation.app.enums.Unit;

import javax.validation.constraints.*;

import java.math.BigDecimal;

import static pizzaNation.app.util.WebConstants.*;
import static pizzaNation.app.util.WebConstants.INVALID_PRICE_MESSAGE;

/**
 * Created by George-Lenovo on 04/04/2018.
 */
public class EditIngredientRequestModel {

    private String id;

    @NotBlank
    @Size(max = 50, message = INVALID_INGREDIENT_NAME_MESSAGE)
    private String name;

    @Min(value = 0, message = INVALID_QUANTITY_MESSAGE)
    @Max(value = 1_000_000, message = INVALID_QUANTITY_MESSAGE)
    @NotNull()
    private Double quantity;

    @NotNull
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

    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
    }
}
