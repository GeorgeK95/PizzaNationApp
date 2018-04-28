package pizzaNation.app.model.view;

import java.math.BigDecimal;

/**
 * Created by George-Lenovo on 23/04/2018.
 */
public class MenuProductsViewModel {

//    private String id;

    private String name;

    private String details;

    private BigDecimal price;

    private Boolean isPromotional;

    private ImageViewModel image;

    private IngredientViewModel ingredients;

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Boolean getPromotional() {
        return isPromotional;
    }

    public ImageViewModel getImage() {
        return image;
    }

    public IngredientViewModel getIngredients() {
        return ingredients;
    }

    public String getDetails() {
        return details;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPromotional(Boolean promotional) {
        isPromotional = promotional;
    }

    public void setImage(ImageViewModel image) {
        this.image = image;
    }

    public void setIngredients(IngredientViewModel ingredients) {
        this.ingredients = ingredients;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
