package pizzaNation.app.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pizzaNation.app.util.DirectoryScanner;
import pizzaNation.app.util.StaticFilesContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static pizzaNation.app.util.WebConstants.CSS_PAGE_FILES;
import static pizzaNation.app.util.WebConstants.JS_PAGE_FILES;
import static pizzaNation.cloudinary.CloudConstants.*;
import static pizzaNation.email.util.Constants.*;

@Configuration
public class PizzaNationBeanConfiguration {

    @Bean
    public StaticFilesContainer getStaticFilesContainer() {
        return new StaticFilesContainer(
                DirectoryScanner.getDirectoryFilesNames(CSS_PAGE_FILES),
                DirectoryScanner.getDirectoryFilesNames(JS_PAGE_FILES)
        );
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(GMAIL_SMTP);
        mailSender.setPort(PORT);

        mailSender.setUsername(ADMIN_EMAIL);
        mailSender.setPassword(ADMIN_PASSWORD);

        Properties props = mailSender.getJavaMailProperties();
        props.put(MAIL_TRANSPORT_PROTOCOL, SMTP_STR);
        props.put(MAIL_SMTP_AUTH, STATUS_TRUE);
        props.put(MAIL_SMTP_STARTTLS_ENABLE, STATUS_TRUE);
        props.put(MAIL_DEBUG, STATUS_TRUE);

        return mailSender;
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SimpleMailMessage getSimpleMailMessage() {
        return new SimpleMailMessage();
    }

    @Bean
    public Cloudinary getCloudinary() {
        return new Cloudinary(this.configureCloudParams());
    }

    private Map<Object, Object> configureCloudParams() {
        Map<Object, Object> config = new HashMap<>();
        config.put(CLOUD_NAME, CLOUD_NAME_VALUE);
        config.put(API_KEY, API_KEY_VALUE);
        config.put(API_SECRET, SECRET_KEY_VALUE);
        return config;
    }
}
