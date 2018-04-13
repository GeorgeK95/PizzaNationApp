package pizzaNation.app.model.request;

import org.springframework.web.multipart.MultipartFile;
import pizzaNation.app.annotation.Image;

import javax.validation.constraints.*;

import static pizzaNation.app.util.WebConstants.*;

/**
 * Created by George-Lenovo on 01/04/2018.
 */
public class AddMenuRequestModel {

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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPriority() {
        return priority;
    }

//    public String getImagePath() {
//        return imagePath;
//    }


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

//    public void setImagePath(String imagePath) {
//        this.imagePath = imagePath;
//    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
