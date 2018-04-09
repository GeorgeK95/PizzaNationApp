package pizzaNation.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pizzaNation.app.model.entity.Log;

/**
 * Created by George-Lenovo on 09/04/2018.
 */
@Repository
public interface LoggerRepository extends JpaRepository<Log, String> {
}
