package pizzaNation.app.service.contract;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.app.model.entity.Ingredient;
import pizzaNation.app.model.request.AddIngredientRequestModel;
import pizzaNation.app.model.request.EditIngredientRequestModel;
import pizzaNation.app.model.request.EditProductRequestModel;
import pizzaNation.app.model.response.IngredientResponseModel;
import pizzaNation.app.model.response.ProductResponseModel;

import java.util.List;
import java.util.Set;

/**
 * Created by George-Lenovo on 03/04/2018.
 */
public interface IIngredientService {
    List<IngredientResponseModel> findAllByDate();

    boolean addIngredient(AddIngredientRequestModel addIngredientRequestModel, RedirectAttributes attributes, BindingResult bindingResult);

    boolean persistIngredient(AddIngredientRequestModel addIngredientRequestModel);

    IngredientResponseModel findByName(String name);

    Set<Ingredient> findAllByIds(String[] ids);

    boolean editIngredient(EditIngredientRequestModel editIngredientRequestModel, RedirectAttributes attributes, BindingResult bindingResult, String name);

    boolean deleteIngredient(String name);
}
