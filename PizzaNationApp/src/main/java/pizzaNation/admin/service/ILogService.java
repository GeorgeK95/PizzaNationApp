package pizzaNation.admin.service;

import pizzaNation.app.model.view.LogViewModel;

import java.util.List;

public interface ILogService {

    List<LogViewModel> findAll();
}
