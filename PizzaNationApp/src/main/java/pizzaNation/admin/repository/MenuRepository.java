package pizzaNation.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizzaNation.app.model.entity.Menu;

import java.util.List;

/**
 * Created by George-Lenovo on 01/04/2018.
 */
public interface MenuRepository extends JpaRepository<Menu, String> {
}
