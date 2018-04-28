package pizzaNation.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaNation.admin.repository.LogRepository;
import pizzaNation.app.model.entity.Log;
import pizzaNation.app.model.view.LogViewModel;
import pizzaNation.app.util.DTOConverter;

import java.util.List;

/**
 * Created by George-Lenovo on 17/04/2018.
 */
@Service
@Transactional
public class LogService implements ILogService {

    private final LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public List<LogViewModel> findAll() {
        return DTOConverter.convert(this.logRepository.findAllByDateDesc(), LogViewModel.class);
    }
}
