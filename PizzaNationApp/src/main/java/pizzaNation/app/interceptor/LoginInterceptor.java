package pizzaNation.app.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pizzaNation.app.config.PizzaNationSecurityConfiguration;
import pizzaNation.app.contoller.CartController;
import pizzaNation.app.model.view.ProductCartViewModelWrapper;
import pizzaNation.app.service.CartService;
import pizzaNation.user.model.entity.User;
import pizzaNation.user.service.IUserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import static pizzaNation.app.util.WebConstants.*;

/**
 * Created by George-Lenovo on 09/04/2018.
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {


    @Autowired
    public LoginInterceptor() {
    }

    /*@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!request.getRequestURI().equals(LOGIN_URL)) return true;

        response.sendRedirect(SET_CART_COOKIE_URL);

        return true;
    }*/

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws IOException {
        if (!request.getRequestURI().equals(LOGIN_URL)) return;

        response.sendRedirect(SET_CART_COOKIE_URL);
    }

}
