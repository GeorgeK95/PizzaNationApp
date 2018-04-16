package pizzaNation.app.service.contract;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.app.model.entity.Ingredient;
import pizzaNation.app.model.request.*;
import pizzaNation.app.model.response.IngredientResponseModel;

import java.util.List;
import java.util.Set;

/**
 * Created by George-Lenovo on 03/04/2018.
 */
public interface IIngredientService {
    List<IngredientResponseModel> findAllByDateDesc();

//    boolean addIngredient(AddIngredientRequestModel addIngredientRequestModel, RedirectAttributes attributes, BindingResult bindingResult);

//    boolean persistIngredient(AddIngredientRequestModel addIngredientRequestModel);

//    IngredientResponseModel findByName(String name);

    Set<Ingredient> findAllByIds(String[] ids);

//    boolean editIngredient(EditIngredientRequestModel editIngredientRequestModel, RedirectAttributes attributes, BindingResult bindingResult, String name);

//    void deleteIngredient(String name);

    IngredientsRequestModelWrapper getRequestModels();

    boolean addIngredientsAndSetThemToProduct(String productName, IngredientsRequestModelWrapper model, BindingResult bindingResult, RedirectAttributes attributes);

    IngredientResponseModel findOne(String id);

    boolean editIngredient(EditIngredientRequestModel editIngredientRequestModel, RedirectAttributes attributes, BindingResult bindingResult, String name);

    IngredientResponseModel findById(String id);

    boolean deleteIngredient(String id,RedirectAttributes attributes);
}
