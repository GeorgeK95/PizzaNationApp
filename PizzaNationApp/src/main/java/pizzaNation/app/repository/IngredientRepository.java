package pizzaNation.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pizzaNation.app.model.entity.Ingredient;
import pizzaNation.app.model.response.IngredientResponseModel;

import java.util.List;
import java.util.Set;

/**
 * Created by George-Lenovo on 03/04/2018.
 */
@Repository
@Transactional
public interface IngredientRepository extends JpaRepository<Ingredient, String> {
    @Query("select i from Ingredient i order by i.date")
    Set<Ingredient> findAllOrderByDate();

    boolean existsByName(String name);

    Ingredient findByName(String name);

    Set<Ingredient> findAllByIdIn(String[] ids);
}
