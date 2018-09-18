package pizzaNation.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pizzaNation.user.model.entity.User;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("select u from User u where u.emailNewsletters = TRUE")
    Set<User> findAllSubscribed();

    User findByEmailVerificationCode(String code);

    @Query("select u from User u order by u.date desc")
    List<User> findLastRegistered();

    User findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("select u from User u where u.email not like 'pizzanationapp@gmail.com'")
    List<User> findAllExceptAdmin();
}
