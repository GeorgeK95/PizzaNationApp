package pizzaNation.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pizzaNation.user.model.entity.User;

import java.util.List;

/**
 * Created by George-Lenovo on 27/03/2018.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("select u from User u where u.email not like 'pizzanationapp@gmail.com'")
    List<User> findAllExceptAdmin();
}
