package pizzaNation.admin.service;

import pizzaNation.app.model.view.LogViewModel;

import java.util.List;

/**
 * Created by George-Lenovo on 17/04/2018.
 */
public interface ILogService {

    List<LogViewModel> findAll();
}
