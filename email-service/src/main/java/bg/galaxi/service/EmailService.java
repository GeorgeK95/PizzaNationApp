package bg.galaxi.service;

import bg.galaxi.model.EmailVerification;
import bg.galaxi.model.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static bg.galaxi.util.Constants.ADMIN_EMAIL;
import static bg.galaxi.util.Constants.CONFIRM_MESSAGE;

@Service
public class EmailService {

    @Qualifier("getJavaMailSender")
    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(Mail mail) {
        this.sendSimpleMessage(mail.getEmail(), ADMIN_EMAIL, mail.getSubject(), this.constructMessage(mail));
    }

    public void sendSimpleMessage(EmailVerification mail) {
        this.sendSimpleMessage(ADMIN_EMAIL, mail.getEmail(), CONFIRM_MESSAGE, mail.getVerification());
    }

    private void sendSimpleMessage(String from, String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom(from);

        emailSender.send(message);
    }

    private String constructMessage(Mail mail) {
        return "Sender name: "
                .concat(mail.getFirstName())
                .concat(System.lineSeparator())
                .concat("Sender email: ")
                .concat(mail.getEmail())
                .concat(System.lineSeparator())
                .concat(System.lineSeparator())
                .concat(mail.getMessage());
    }
}