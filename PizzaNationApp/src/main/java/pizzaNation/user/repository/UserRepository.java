package pizzaNation.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pizzaNation.user.model.entity.User;

/**
 * Created by George-Lenovo on 27/03/2018.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}
