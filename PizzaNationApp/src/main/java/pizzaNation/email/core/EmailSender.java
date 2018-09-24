package pizzaNation.email.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
    private final JavaMailSender emailSender;
    private final SimpleMailMessage message;

    @Autowired
    public EmailSender(JavaMailSender emailSender, SimpleMailMessage message) {
        this.emailSender = emailSender;
        this.message = message;
    }

    public void sendSimpleMessage(String from, String to, String subject, String text) {
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom(from);

        emailSender.send(message);
    }
}
