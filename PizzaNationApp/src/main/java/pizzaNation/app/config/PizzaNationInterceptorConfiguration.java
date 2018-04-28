package pizzaNation.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import pizzaNation.app.interceptor.LoggerInterceptor;
import pizzaNation.app.interceptor.LoginInterceptor;

/**
 * Created by George-Lenovo on 09/04/2018.
 */
@Configuration
public class PizzaNationInterceptorConfiguration extends WebMvcConfigurerAdapter {

    private final LoggerInterceptor loggerInterceptor;
    private final LoginInterceptor loginInterceptor;

    @Autowired
    public PizzaNationInterceptorConfiguration(LoggerInterceptor loggerInterceptor, LoginInterceptor loginInterceptor) {
        this.loggerInterceptor = loggerInterceptor;
        this.loginInterceptor = loginInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.loggerInterceptor);
        registry.addInterceptor(this.loginInterceptor);
    }
}
