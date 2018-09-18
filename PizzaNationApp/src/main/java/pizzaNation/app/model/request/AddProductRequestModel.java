package pizzaNation.app.model.request;

import org.springframework.web.multipart.MultipartFile;
import pizzaNation.app.annotation.Image;
import pizzaNation.app.annotation.Price;

import javax.validation.constraints.*;

import java.math.BigDecimal;

import static pizzaNation.app.util.WebConstants.INVALID_DESCRIPTION_MESSAGE;
import static pizzaNation.app.util.WebConstants.INVALID_MENU_NAME_MESSAGE;
import static pizzaNation.app.util.WebConstants.INVALID_PRODUCT_MESSAGE;

public class AddProductRequestModel {

    @NotBlank
    @Size(max = 50, message = INVALID_PRODUCT_MESSAGE)
    private String name;

    @NotBlank
    @Size(message = INVALID_PRODUCT_MESSAGE)
    private String details;

    @Price
    private BigDecimal price;

    private Boolean isPromotional;

    @NotNull
    @Image
    private MultipartFile image;

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Boolean getPromotional() {
        return isPromotional;
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

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPromotional(Boolean isPromotional) {
        this.isPromotional = isPromotional;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
