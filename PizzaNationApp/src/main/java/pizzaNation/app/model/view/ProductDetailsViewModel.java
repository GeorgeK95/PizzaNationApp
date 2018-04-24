package pizzaNation.app.model.view;

import pizzaNation.app.model.entity.Image;
import pizzaNation.app.model.entity.Ingredient;

import java.util.Set;

/**
 * Created by George-Lenovo on 24/04/2018.
 */
public class ProductDetailsViewModel {

    private String name;

    private String details;

    private Set<IngredientViewModel> ingredients;

    private Image image;

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public Set<IngredientViewModel> getIngredients() {
        return ingredients;
    }

    public Image getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setIngredients(Set<IngredientViewModel> ingredients) {
        this.ingredients = ingredients;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
