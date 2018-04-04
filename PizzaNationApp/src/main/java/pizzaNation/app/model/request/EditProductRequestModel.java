package pizzaNation.app.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.Set;

import static pizzaNation.app.util.WebConstants.INVALID_PRODUCT_MESSAGE;

/**
 * Created by George-Lenovo on 03/04/2018.
 */
public class EditProductRequestModel {

    @NotBlank
    @Size(max = 50, message = INVALID_PRODUCT_MESSAGE)
    private String name;

    @NotBlank
    @Size(message = INVALID_PRODUCT_MESSAGE)
    private String details;

    private Set<String> ingredientsIds;

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public Set<String> getIngredientsIds() {
        return ingredientsIds;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setIngredientsIds(Set<String> ingredientsIds) {
        this.ingredientsIds = ingredientsIds;
    }
}
