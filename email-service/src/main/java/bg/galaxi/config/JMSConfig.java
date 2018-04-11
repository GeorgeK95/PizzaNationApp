package bg.galaxi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

import static bg.galaxi.util.Constants.*;

/**
 * Created by George-Lenovo on 31/03/2018.
 */
@Configuration
public class JMSConfig {

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
}
