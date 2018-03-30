package pizzaNation.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizzaNation.user.model.entity.Role;

/**
 * Created by George-Lenovo on 30/03/2018.
 */
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByAuthority(String authority);
}
