package pizzaNation.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pizzaNation.app.model.entity.Menu;

import java.util.List;

/**
 * Created by George-Lenovo on 01/04/2018.
 */
public interface MenuRepository extends JpaRepository<Menu, String> {
    Menu findByName(String menuName);

    boolean existsByName(String name);

//    boolean existsByPriority(Integer priority);

    Menu getByPriority(Integer priority);

    Menu getByName(String name);

    @Query("select m from Menu m order by m.date desc")
    List<Menu> findAllByDateDesc();
}
