package pizzaNation.app.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pizzaNation.app.annotation.LoggerAction;
import pizzaNation.app.service.contract.ILoggerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static pizzaNation.app.util.WebConstants.MODIFIED_TABLE_STR;
import static pizzaNation.app.util.WebConstants.OPERATION_STR;
import static pizzaNation.app.util.WebConstants.USER_STR;

/**
 * Created by George-Lenovo on 09/04/2018.
 */
@Component
public class LoggerInterceptor extends HandlerInterceptorAdapter {

    private final ILoggerService loggerService;

    @Autowired
    public LoggerInterceptor(ILoggerService loggerService) {
        this.loggerService = loggerService;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) {
        try {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if (!handlerMethod.getMethod().isAnnotationPresent(LoggerAction.class)) {
                return;
            }

            ModelMap modelMap = this.constructModelMapWithData(handlerMethod);

            this.loggerService.addLog(modelMap);
        } catch (ClassCastException cce) {
        }
    }

    private ModelMap constructModelMapWithData(HandlerMethod handlerMethod) {
        ModelMap modelMap = new ModelMap();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        modelMap.addAttribute(USER_STR, currentPrincipalName);
        modelMap.addAttribute(OPERATION_STR, handlerMethod.getMethod().getAnnotation(LoggerAction.class).action());
        modelMap.addAttribute(MODIFIED_TABLE_STR, handlerMethod.getMethod().getAnnotation(LoggerAction.class).table());

        return modelMap;
    }
}
