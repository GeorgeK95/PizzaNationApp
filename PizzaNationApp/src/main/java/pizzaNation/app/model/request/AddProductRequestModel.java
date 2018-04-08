package pizzaNation.app.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static pizzaNation.app.util.WebConstants.INVALID_DESCRIPTION_MESSAGE;
import static pizzaNation.app.util.WebConstants.INVALID_MENU_NAME_MESSAGE;
import static pizzaNation.app.util.WebConstants.INVALID_PRODUCT_MESSAGE;

/**
 * Created by George-Lenovo on 03/04/2018.
 */
public class AddProductRequestModel {

    @NotBlank
    @Size(max = 50, message = INVALID_PRODUCT_MESSAGE)
    private String name;

    @NotBlank
    @Size(message = INVALID_PRODUCT_MESSAGE)
    private String details;

    private String[] ingredientsIds;

    private Boolean isPromotional;

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public String[] getIngredientsIds() {
        return ingredientsIds;
    }

    public Boolean getPromotional() {
        return isPromotional;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setIngredientsIds(String[] ingredientsIds) {
        this.ingredientsIds = ingredientsIds;
    }

    public void setPromotional(Boolean isPromotional) {
        this.isPromotional = isPromotional;
    }
}
