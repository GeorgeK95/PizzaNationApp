package pizzaNation.app.model.request;

import pizzaNation.app.model.response.ProductViewModel;

import javax.validation.constraints.*;

import static pizzaNation.app.util.WebConstants.INVALID_DESCRIPTION_MESSAGE;
import static pizzaNation.app.util.WebConstants.INVALID_MENU_NAME_MESSAGE;
import static pizzaNation.app.util.WebConstants.INVALID_PRIORITY_MESSAGE;

/**
 * Created by George-Lenovo on 02/04/2018.
 */
public class EditMenuRequestModel {

    @NotBlank
    @Size(max = 20, message = INVALID_MENU_NAME_MESSAGE)
    private String name;

    @NotBlank
    @Size(message = INVALID_DESCRIPTION_MESSAGE)
    private String description;

    @NotNull(message = INVALID_PRIORITY_MESSAGE)
    @Min(1)
    @Max(100)
    private Integer priority;

    private ProductViewModel[] productIds;

    private String imagePath;

    public ProductViewModel[] getProductIds() {
        return productIds;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPriority() {
        return priority;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setProductIds(ProductViewModel[] productIds) {
        this.productIds = productIds;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}
