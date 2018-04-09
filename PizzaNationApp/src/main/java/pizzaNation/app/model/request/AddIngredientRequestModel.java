package pizzaNation.app.model.request;

import pizzaNation.app.enums.Unit;

import javax.validation.constraints.*;
import java.math.BigDecimal;

import static pizzaNation.app.util.WebConstants.*;

/**
 * Created by George-Lenovo on 03/04/2018.
 */
public class AddIngredientRequestModel {

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
