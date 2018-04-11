package pizzaNation.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pizzaNation.user.model.entity.User;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class PizzaNationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().permitAll();
        http.authorizeRequests()
                .antMatchers("/", "/menu", "/map", "/cart", "/about", "/terms", "/contactUs").permitAll()
                .antMatchers("/register", "/login").access("isAnonymous()")
                .antMatchers("/logout", "/account").access("isAuthenticated()")
                .antMatchers("/admin/**").access("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
                .antMatchers("/admin/users").access("hasAnyRole('ROLE_ADMIN')")
                .and()
                .formLogin().loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/login?logout").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/unauthorized")
                .and()
                .csrf()
                .disable();
    }

    public static String getCurrentlyLoggedInUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}