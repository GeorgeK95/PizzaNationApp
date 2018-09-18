package pizzaNation.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import pizzaNation.app.enums.Action;
import pizzaNation.app.enums.TableEnum;
import pizzaNation.app.model.entity.Log;
import pizzaNation.app.repository.LoggerRepository;
import pizzaNation.app.service.contract.ILoggerService;
import pizzaNation.user.repository.UserRepository;

import java.util.Date;

import static pizzaNation.app.util.WebConstants.MODIFIED_TABLE_STR;
import static pizzaNation.app.util.WebConstants.OPERATION_STR;
import static pizzaNation.app.util.WebConstants.USER_STR;

@Service
@Transactional
public class LoggerService implements ILoggerService {

    private final UserRepository userRepository;

    private final LoggerRepository loggerRepository;

    @Autowired
    public LoggerService(UserRepository userRepository, LoggerRepository loggerRepository) {
        this.userRepository = userRepository;
        this.loggerRepository = loggerRepository;
    }

    @Override
    public boolean addLog(ModelMap modelMap) {
        String email = (String) modelMap.get(USER_STR);
        Action action = (Action) modelMap.get(OPERATION_STR);
        TableEnum modifiedTable = (TableEnum) modelMap.get(MODIFIED_TABLE_STR);

        Log log = new Log(action, modifiedTable, this.userRepository.findByEmail(email));

        if (log.getUser() == null) log.setUser(this.userRepository.findLastRegistered().get(0));

        this.loggerRepository.saveAndFlush(log);

        return true;
    }
}
