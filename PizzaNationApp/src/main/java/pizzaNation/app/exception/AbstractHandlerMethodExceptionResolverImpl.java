package pizzaNation.app.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerMethodExceptionResolver;
import pizzaNation.app.contoller.error.ExceptionController;
import pizzaNation.app.interceptor.LoggerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class AbstractHandlerMethodExceptionResolverImpl extends AbstractHandlerMethodExceptionResolver {

    private final LoggerInterceptor interceptor;

    private final ExceptionController controller;

    @Autowired
    public AbstractHandlerMethodExceptionResolverImpl(ExceptionController controller, LoggerInterceptor interceptor) {
        this.controller = controller;
        this.interceptor = interceptor;
    }

    @Override
    @ExceptionHandler(value = {ProductNotFoundException.class, MenuNotFoundException.class, UserNotFoundException.class,
            ConfirmCodeNotFoundException.class, NoEmailVerificationCodeInGetRequestException.class, CartNotFoundException.class})
    protected ModelAndView doResolveHandlerMethodException(HttpServletRequest httpServletRequest,
                                                           HttpServletResponse httpServletResponse,
                                                           HandlerMethod handlerMethod,
                                                           Exception e) {
        ModelAndView modelAndView = this.processModelAndView(e);

        this.interceptor.postHandle(httpServletRequest, httpServletResponse, handlerMethod, modelAndView);

        return modelAndView;
    }

    private ModelAndView processModelAndView(Exception e) {
        ModelAndView modelAndView;

        if (e instanceof ProductNotFoundException) {
            modelAndView = this.controller.product();
        } else if (e instanceof MenuNotFoundException) {
            modelAndView = this.controller.menu();
        } else if (e instanceof UserNotFoundException) {
            modelAndView = this.controller.user();
        } else if (e instanceof ConfirmCodeNotFoundException) {
            modelAndView = this.controller.notConfirmed();
        } else if (e instanceof CartNotFoundException) {
            modelAndView = this.controller.badRequest();
        } else if (e instanceof NoEmailVerificationCodeInGetRequestException) {
            modelAndView = this.controller.invalidVerificationCodeRequest();
        } else {
            modelAndView = this.controller.notFound();
        }

        return modelAndView;
    }
}
