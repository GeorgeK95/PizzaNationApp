package pizzaNation.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pizzaNation.app.model.entity.Log;
import pizzaNation.user.model.entity.User;

import java.util.Set;

@Repository
public interface LoggerRepository extends JpaRepository<Log, String> {
}
