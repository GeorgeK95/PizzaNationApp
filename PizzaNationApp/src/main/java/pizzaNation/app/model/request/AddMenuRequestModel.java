package pizzaNation.app.model.request;

import org.springframework.web.multipart.MultipartFile;
import pizzaNation.app.annotation.Image;
import pizzaNation.app.model.request.api.MenuRequestModel;

import javax.validation.constraints.*;

import static pizzaNation.app.util.WebConstants.*;

public class AddMenuRequestModel implements MenuRequestModel {

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

    private String[] productsIds;

    @NotNull
    @Image
    private MultipartFile image;
//    private String imagePath;

    public String[] getProductsIds() {
        return productsIds;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Integer getPriority() {
        return priority;
    }

    @Override
    public MultipartFile getImage() {
        return image;
    }

    public void setProductsIds(String[] productsIds) {
        this.productsIds = productsIds;
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

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
