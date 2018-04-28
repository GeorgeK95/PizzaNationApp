
package pizzaNation.emailScheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pizzaNation.app.model.entity.Ingredient;
import pizzaNation.app.service.contract.IIngredientService;
import pizzaNation.user.service.IUserService;

import java.util.Set;

import static pizzaNation.app.util.WebConstants.EMAIL_SCHEDULER_FIXED_RATE;

/**
 * Created by George-Lenovo on 17/04/2018.
 */

@Component
public class DeleteStandaloneIngredientsScheduledTask {

    private final IUserService userService;

    private final IIngredientService ingredientService;

    public DeleteStandaloneIngredientsScheduledTask(IUserService userService, IIngredientService ingredientService) {
        this.userService = userService;
        this.ingredientService = ingredientService;
    }

    @Scheduled(fixedRate = EMAIL_SCHEDULER_FIXED_RATE)
    public void reportCurrentTime() {
        Set<Ingredient> allStandalone = this.ingredientService.findAllStandalone();

        this.ingredientService.deleteAll(allStandalone);
    }
}

