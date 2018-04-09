package pizzaNation.app.service.contract;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by George-Lenovo on 09/04/2018.
 */
public interface ILoggerService {

    static ModelAndView constructModelAndView(String redirectTo,
                                              String username,
                                              String operation,
                                              String table) {
        ModelAndView modelAndView = new ModelAndView(redirectTo);

        modelAndView.addObject("user", username);
        modelAndView.addObject("operation", operation);
        modelAndView.addObject("modifiedTable", table);

        return modelAndView;
    }

    void addLog(ModelMap modelMap);
}
