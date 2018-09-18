package pizzaNation.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizzaNation.user.model.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByAuthority(String authority);
}
