package pizzaNation.app.model.request;

import org.springframework.web.multipart.MultipartFile;
import pizzaNation.app.annotation.Price;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.math.BigDecimal;
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

    @Price
    private BigDecimal price;

    private Set<String> ingredientsIds;

    private Boolean isPromotional;

    private Integer totalSales;

    private MultipartFile image;

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public Boolean getPromotional() {
        return isPromotional;
    }

    public Set<String> getIngredientsIds() {
        return ingredientsIds;
    }

    public Integer getTotalSales() {
        return totalSales;
    }

    public MultipartFile getImage() {
        return image;
    }

    public BigDecimal getPrice() {
        return price;
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

    public void setPromotional(Boolean promotional) {
        isPromotional = promotional;
    }

    public void setTotalSales(Integer totalSales) {
        this.totalSales = totalSales;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
