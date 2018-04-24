package pizzaNation.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static pizzaNation.app.util.WebConstants.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class PizzaNationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String[] PERMITTED_FOR_ALL_ROUTES = new String[]{
            "/", "/menu", "/map", "/cart", "/about", "/terms", "/contactUs"
    };
    private static final String[] PERMITTED_FOR_ANONYMOUS_ROUTES = new String[]{
            "/register", "/login"
    };
    private static final String[] PERMITTED_FOR_LOGGED_ROUTES = new String[]{
            "/logout", "/account"
    };
    private static final String[] PERMITTED_FOR_ADMIN_AND_MODERATORS_ROUTES = new String[]{
            "/admin/**"
    };
    private static final String[] PERMITTED_FOR_ADMIN_ROUTES = new String[]{
            "/admin/users"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(PERMITTED_FOR_ALL_ROUTES).permitAll()
                .antMatchers(PERMITTED_FOR_ANONYMOUS_ROUTES).access("isAnonymous()")
                .antMatchers(PERMITTED_FOR_LOGGED_ROUTES).access("isAuthenticated()")
                .antMatchers(PERMITTED_FOR_ADMIN_AND_MODERATORS_ROUTES).access("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
                .antMatchers(PERMITTED_FOR_ADMIN_ROUTES).access("hasAnyRole('ROLE_ADMIN')")
                .and()
                .formLogin().loginPage(LOGIN_URL)
                .usernameParameter(EMAIL_STR)
                .passwordParameter(PASSWORD_STR)
                .and()
                .logout().logoutSuccessUrl(LOGIN_LOGOUT_URL).permitAll()
                .and()
                .exceptionHandling().accessDeniedPage(UNAUTHORIZED_URL)
                .and()
                .csrf()
                .disable();
    }

    public static String getCurrentlyLoggedInUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}