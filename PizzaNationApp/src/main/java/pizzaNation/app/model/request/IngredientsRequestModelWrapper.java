package pizzaNation.app.model.request;

import java.util.List;

/**
 * Created by George-Lenovo on 14/04/2018.
 */
public class IngredientsRequestModelWrapper {

    private IngredientsRequestModel[] ingredients;

    public IngredientsRequestModelWrapper() {
    }

    public IngredientsRequestModelWrapper(List<IngredientsRequestModel> ingredients) {
        this.ingredients = ingredients.toArray(new IngredientsRequestModel[0]);
    }

    public IngredientsRequestModel[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(IngredientsRequestModel[] ingredients) {
        this.ingredients = ingredients;
    }
}
