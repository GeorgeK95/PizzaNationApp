package pizzaNation.app.model.request;

import org.springframework.web.multipart.MultipartFile;
import pizzaNation.app.annotation.Image;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.math.BigDecimal;

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

    @NotBlank
    @Min(1)
    private BigDecimal price;

    @NotNull
    @Image
    private MultipartFile image;

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

    public BigDecimal getPrice() {
        return price;
    }

    public MultipartFile getImage() {
        return image;
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

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
