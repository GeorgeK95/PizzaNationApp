package bg.galaxi;

import bg.galaxi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
public class EmailServiceApplication {

    @Autowired
    private EmailService emailService;

    public static void main(String[] args) {
        SpringApplication.run(EmailServiceApplication.class, args);
    }
}
