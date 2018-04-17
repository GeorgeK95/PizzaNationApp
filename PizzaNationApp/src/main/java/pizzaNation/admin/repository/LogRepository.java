package pizzaNation.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizzaNation.app.model.entity.Log;

/**
 * Created by George-Lenovo on 17/04/2018.
 */
public interface LogRepository extends JpaRepository<Log, String> {
}
