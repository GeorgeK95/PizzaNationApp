package pizzaNation.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pizzaNation.app.model.entity.Log;

@Repository
public interface LoggerRepository extends JpaRepository<Log, String> {
}
