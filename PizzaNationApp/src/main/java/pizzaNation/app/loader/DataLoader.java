package pizzaNation.app.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pizzaNation.user.enumeration.Gender;
import pizzaNation.user.model.entity.Role;
import pizzaNation.user.model.entity.User;
import pizzaNation.user.repository.UserRepository;

import java.util.Set;

import static pizzaNation.app.util.WebConstants.*;

/**
 * Created by George-Lenovo on 21/03/2018.
 */
@Component
public class DataLoader implements ApplicationRunner {

    private final UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        User admin = new User(ADMIN_EMAIL, ADMIN_PASSWORD, ADMIN_FIRST_NAME, ADMIN_LAST_NAME,
                ADMIN_ADDRESS, Gender.MALE, ADMIN_PHONE, ADMIN_CITY);
        User moderator = new User(MODERATOR_EMAIL, MODERATOR_PASSWORD, MODERATOR_FIRST_NAME,
                MODERATOR_LAST_NAME, MODERATOR_ADDRESS, Gender.FEMALE, MODERATOR_PHONE, MODERATOR_CITY);

        Role roleAdmin = new Role(ROLE_ADMIN);
        Role roleModerator = new Role(ROLE_MODERATOR);
        Role roleUser = new Role(ROLE_USER);

        admin.addRole(roleAdmin);
        admin.addRole(roleModerator);
        admin.addRole(roleUser);

        moderator.addRole(roleModerator);
        moderator.addRole(roleUser);

        this.userRepository.saveAll(Set.of(admin, moderator));
    }
}