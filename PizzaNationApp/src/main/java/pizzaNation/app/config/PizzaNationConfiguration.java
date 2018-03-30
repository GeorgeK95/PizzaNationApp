package pizzaNation.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pizzaNation.app.util.DirectoryScanner;
import pizzaNation.app.util.StaticFilesContainer;

import static pizzaNation.app.util.WebConstants.CSS_PAGE_FILES;
import static pizzaNation.app.util.WebConstants.JS_PAGE_FILES;

/**
 * Created by George-Lenovo on 25/03/2018.
 */
@Configuration
public class PizzaNationConfiguration {

    @Bean
    public StaticFilesContainer getStaticFilesContainer() {
        return new StaticFilesContainer(
                DirectoryScanner.getDirectoryFilesNames(CSS_PAGE_FILES),
                DirectoryScanner.getDirectoryFilesNames(JS_PAGE_FILES)
        );
    }
}
