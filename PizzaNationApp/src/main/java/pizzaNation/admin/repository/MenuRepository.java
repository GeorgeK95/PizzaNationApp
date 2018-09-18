package pizzaNation.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pizzaNation.app.model.entity.Menu;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, String> {
    Menu findByName(String menuName);

    Menu getByPriority(Integer priority);

    Menu getByName(String name);

    @Query("select m from Menu m order by m.date desc")
    List<Menu> findAllByDateDesc();

    @Query("select m from Menu m order by m.priority asc")
    List<Menu> findAllOrderedByPriorityAsc();
}
