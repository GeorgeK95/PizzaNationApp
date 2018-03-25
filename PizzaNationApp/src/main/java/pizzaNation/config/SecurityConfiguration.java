package pizzaNation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pizzaNation.model.PeshoModel;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll();
        /*http.authorizeRequests()
                .antMatchers("/").permitAll();
                .antMatchers("/register", "/login").access("isAnonymous()")
                .antMatchers("/viruses/show", "/logout").access("isAuthenticated()")
                .antMatchers("/viruses/**").access("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
                .and()
                .formLogin().loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/login?logout").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/unauthorized")
                .and()
                .csrf()
                .disable();*/
    }

//    @Bean
//    public StaticFilesContainer getStaticFilesContainer() {
//        StaticFilesContainer filesContainer = new StaticFilesContainer();
//        filesContainer.setCssFiles(DirectoryScanner.getDirectoryFilesNames(CSS_PAGE_FILES));
//        filesContainer.setJsFiles(DirectoryScanner.getDirectoryFilesNames(JS_PAGE_FILES));
//        return filesContainer;
//    }

    //ako stane,nov config file i tam
    @Bean
    public PeshoModel getPeshoModel() {
        PeshoModel peshoModel = new PeshoModel();
        peshoModel.setEmail("pecata_ludq@abv.bg");
        return peshoModel;
    }
}