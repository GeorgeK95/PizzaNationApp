package pizzaNation.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pizzaNation.app.model.entity.Image;

/**
 * Created by George-Lenovo on 13/04/2018.
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
}
