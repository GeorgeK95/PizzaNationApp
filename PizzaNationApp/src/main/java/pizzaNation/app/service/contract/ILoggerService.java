package pizzaNation.app.service.contract;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import static pizzaNation.app.util.WebConstants.MODIFIED_TABLE_STR;
import static pizzaNation.app.util.WebConstants.OPERATION_STR;
import static pizzaNation.app.util.WebConstants.USER_STR;

/**
 * Created by George-Lenovo on 09/04/2018.
 */
public interface ILoggerService {

    boolean addLog(ModelMap modelMap);
}
