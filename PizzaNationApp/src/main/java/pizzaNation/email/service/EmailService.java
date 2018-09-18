package pizzaNation.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pizzaNation.email.model.EmailVerification;
import pizzaNation.email.model.Mail;
import pizzaNation.email.service.api.IEmailService;

import static pizzaNation.app.util.WebConstants.ADMIN_EMAIL;
import static pizzaNation.app.util.WebConstants.LOCAL_HOST;
import static pizzaNation.app.util.WebConstants.VERIFY_EMAIL_TOKEN;
import static pizzaNation.email.util.Constants.*;

@Service
public class EmailService implements IEmailService {

    private final JavaMailSender emailSender;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendSimpleMessage(Mail mail) {
        this.sendSimpleMessage(mail.getEmail(), ADMIN_EMAIL, mail.getSubject(), this.constructMessage(mail));
    }

    /*@Override
    public void newProductMessage(EmailVerification mail) {
        this.sendSimpleMessage(ADMIN_EMAIL, mail.getEmail(), NEW_PRODUCT_SUBJECT, mail.getVerification());
    }*/

    @Override
    public void sendSimpleMessage(EmailVerification mail) {
        this.sendSimpleMessage(ADMIN_EMAIL, mail.getEmail(), CONFIRM_MESSAGE, mail.getVerification());
    }

    private void sendSimpleMessage(String from, String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject(subject);
        message.setText(LOCAL_HOST +VERIFY_EMAIL_TOKEN + text);
        message.setFrom(from);

        emailSender.send(message);
    }

    private String constructMessage(Mail mail) {
        return SENDER_NAME
                .concat(mail.getFirstName())
                .concat(System.lineSeparator())
                .concat(SENDER_EMAIL)
                .concat(mail.getEmail())
                .concat(System.lineSeparator())
                .concat(System.lineSeparator())
                .concat(mail.getMessage());
    }
}
