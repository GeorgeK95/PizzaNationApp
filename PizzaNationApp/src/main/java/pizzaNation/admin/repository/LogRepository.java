package pizzaNation.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pizzaNation.app.model.entity.Log;

import java.util.Set;

public interface LogRepository extends JpaRepository<Log, String> {

    @Query("select l from Log l order by l.date desc")
    Set<Log> findAllByDateDesc();
}
