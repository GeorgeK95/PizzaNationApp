package pizzaNation.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.app.model.entity.Ingredient;
import pizzaNation.app.model.request.IngredientsRequestModel;
import pizzaNation.app.model.request.IngredientsRequestModelWrapper;
import pizzaNation.app.model.response.IngredientResponseModel;
import pizzaNation.app.repository.IngredientRepository;
import pizzaNation.app.repository.ProductRepository;
import pizzaNation.app.service.contract.IIngredientService;
import pizzaNation.app.util.DTOConverter;
import pizzaNation.user.service.BaseService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static pizzaNation.app.util.WebConstants.ADD_INGREDIENT_ERROR;

/**
 * Created by George-Lenovo on 03/04/2018.
 */
@Service
@Transactional
public class IngredientService extends BaseService implements IIngredientService {

    private final IngredientRepository ingredientRepository;
    private final ProductRepository productRepository;

    public IngredientService(IngredientRepository ingredientRepository, ProductRepository productRepository) {
        this.ingredientRepository = ingredientRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<IngredientResponseModel> findAllByDateDesc() {
        return DTOConverter.convert(this.ingredientRepository.findAllOrderByDateDesc(), IngredientResponseModel.class);
    }

    @Override
    public Set<Ingredient> findAllByIds(String[] ids) {
        return this.ingredientRepository.findAllByIdIn(ids);
    }

    @Override
    public IngredientsRequestModelWrapper getRequestModels() {
        return new IngredientsRequestModelWrapper(List.of(
                new IngredientsRequestModel(),
                new IngredientsRequestModel(),
                new IngredientsRequestModel(),
                new IngredientsRequestModel(),
                new IngredientsRequestModel(),
                new IngredientsRequestModel(),
                new IngredientsRequestModel(),
                new IngredientsRequestModel(),
                new IngredientsRequestModel(),
                new IngredientsRequestModel()
        ));
    }

    @Override
    public boolean addIngredientsAndSetThemToProduct(String productName, IngredientsRequestModelWrapper model,
                                                     BindingResult bindingResult, RedirectAttributes attributes) {
        if (super.containErrors(bindingResult, attributes, ADD_INGREDIENT_ERROR)) return false;

        Set<Ingredient> ingredients = new HashSet();
        Arrays.stream(model.getIngredients()).forEach((i) -> {
            if (!i.getName().isEmpty())
                ingredients.add(DTOConverter.convert(i, Ingredient.class));
        });

        ingredients.forEach(i -> i.setProduct(this.productRepository.findByName(productName)));

        this.ingredientRepository.saveAll(ingredients);

        return true;
    }

    /*@Override
    public boolean addIngredient(AddIngredientRequestModel addIngredientRequestModel, RedirectAttributes attributes, BindingResult bindingResult) {
        attributes.addFlashAttribute(ADD_INGREDIENT_REQUEST_MODEL, addIngredientRequestModel);

        if (super.containErrors(bindingResult, attributes, ADD_INGREDIENT_ERROR)) return false;

        if (this.checkForDuplicateName(addIngredientRequestModel.getName(), ADD_INGREDIENT_ERROR, attributes))
            return false;

        return this.persistIngredient(addIngredientRequestModel);
    }*/
/*

    private boolean checkForDuplicateName(String name, String error, RedirectAttributes attributes) {
        if (this.ingredientRepository.existsByName(name)) {
            attributes.addFlashAttribute(error, INGREDIENT_NAME_ALREADY_TAKEN_MESSAGE);
            return true;
        }

        return false;
    }
*/

   /* @Override
    public boolean persistIngredient(AddIngredientRequestModel addIngredientRequestModel) {
        Ingredient ingredient = DTOConverter.convert(addIngredientRequestModel, Ingredient.class);

        this.ingredientRepository.saveAndFlush(ingredient);

        return true;
    }
*/
   /* @Override
    public IngredientResponseModel findByName(String name) {
        Ingredient ingredient = this.ingredientRepository.findByName(name);

        if (ingredient == null) throw new IngredientNotFoundException();

        return DTOConverter.convert(ingredient, IngredientResponseModel.class);
    }*/



   /* @Override
    public boolean editIngredient(EditIngredientRequestModel editIngredientRequestModel, RedirectAttributes attributes, BindingResult bindingResult, String name) {
        attributes.addFlashAttribute(EDIT_INGREDIENT_REQUEST_MODEL, editIngredientRequestModel);

        Ingredient ingredient = this.ingredientRepository.findByName(name);

        if (this.hasErrors(ingredient, editIngredientRequestModel, bindingResult, attributes)) return false;

        ingredient.setDescription(editIngredientRequestModel.getDescription());
        ingredient.setName(editIngredientRequestModel.getName());
//        ingredient.setPrice(editIngredientRequestModel.getPrice());
        ingredient.setQuantity(editIngredientRequestModel.getQuantity());
        ingredient.setUnit(editIngredientRequestModel.getUnit());

        this.ingredientRepository.saveAndFlush(ingredient);

        return true;
    }

    @Override
    public void deleteIngredient(String name) {
        Ingredient ingredient = this.ingredientRepository.findByName(name);

        if (ingredient == null) throw new IngredientNotFoundException();

        ingredient.setProducts(null); //release products so they wont be deleted

        this.ingredientRepository.delete(ingredient);
    }*/

    /*private boolean hasErrors(Ingredient ingredient, EditIngredientRequestModel editIngredientRequestModel, BindingResult bindingResult, RedirectAttributes attributes) {
        if (ingredient == null) throw new IngredientNotFoundException();

        if (this.checkForDuplicateName(editIngredientRequestModel.getName(), EDIT_INGREDIENT_ERROR, attributes) &&
                !ingredient.getName().equals(editIngredientRequestModel.getName())) return true;

        return super.containErrors(bindingResult, attributes, EDIT_INGREDIENT_ERROR);
    }*/
}
