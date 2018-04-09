package pizzaNation.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import pizzaNation.app.interceptor.LoggerInterceptor;

/**
 * Created by George-Lenovo on 09/04/2018.
 */
@Configuration
public class PizzaNationInterceptorConfiguration extends WebMvcConfigurerAdapter {

    private final LoggerInterceptor loggerInterceptor;

    @Autowired
    public PizzaNationInterceptorConfiguration(LoggerInterceptor loggerInterceptor) {
        this.loggerInterceptor = loggerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.loggerInterceptor);
    }
}
