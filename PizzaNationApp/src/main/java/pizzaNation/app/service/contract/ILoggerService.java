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

   /* static ModelAndView constructModelAndView(String redirectTo,
                                              String username,
                                              String operation,
                                              String table) {
        ModelAndView modelAndView = new ModelAndView(redirectTo);

        modelAndView.addObject(USER_STR, username);
        modelAndView.addObject(OPERATION_STR, operation);
        modelAndView.addObject(MODIFIED_TABLE_STR, table);

        return modelAndView;
    }*/

    boolean addLog(ModelMap modelMap);
}
