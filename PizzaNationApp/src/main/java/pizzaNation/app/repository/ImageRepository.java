package pizzaNation.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pizzaNation.app.model.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
}
