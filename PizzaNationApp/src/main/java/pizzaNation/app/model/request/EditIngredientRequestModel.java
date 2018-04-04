package pizzaNation.app.model.request;

import pizzaNation.app.model.enums.Unit;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.math.BigDecimal;

import static pizzaNation.app.util.WebConstants.*;
import static pizzaNation.app.util.WebConstants.INVALID_PRICE_MESSAGE;

/**
 * Created by George-Lenovo on 04/04/2018.
 */
public class EditIngredientRequestModel {
    @NotBlank
    @Size(max = 50, message = INVALID_INGREDIENT_NAME_MESSAGE)
    private String name;

    @NotBlank
    @Size(message = INVALID_DESCRIPTION_MESSAGE)
    private String description;

    @Min(1)
    @NotNull(message = INVALID_QUANTITY_MESSAGE)
    private Double quantity;

    @NotNull(message = INVALID_PRICE_MESSAGE)
    private Unit unit;

    @Min(1)
    @NotNull(message = INVALID_PRICE_MESSAGE)
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getQuantity() {
        return quantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}