package pizzaNation.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaNation.app.exception.AdminModifyException;
import pizzaNation.app.exception.UserNotFoundException;
import pizzaNation.user.model.entity.User;
import pizzaNation.user.repository.UserRepository;
import pizzaNation.user.service.api.IUserDeleteService;

import java.util.Optional;

import static pizzaNation.app.util.WebConstants.ADMIN_EMAIL;

@Service
@Transactional
public class UserDeleteService extends BaseService implements IUserDeleteService {

    private final UserRepository userRepository;

    @Autowired
    public UserDeleteService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean disableUser(String id) {
        Optional<User> byId = this.userRepository.findById(id);

        if (!byId.isPresent()) throw new UserNotFoundException();

        User user = byId.get();

        if (user.getEmail().equals(ADMIN_EMAIL)) throw new AdminModifyException();

        user.setEnabled(false);

        this.userRepository.saveAndFlush(user);

        return true;
    }
}
