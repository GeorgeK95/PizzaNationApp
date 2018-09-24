package pizzaNation.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pizzaNation.email.core.EmailSender;
import pizzaNation.email.model.EmailVerification;
import pizzaNation.email.model.Mail;
import pizzaNation.email.service.api.IEmailService;

import static pizzaNation.app.util.WebConstants.*;
import static pizzaNation.email.util.Constants.CONFIRM_MESSAGE;

@Service
public class EmailService implements IEmailService {

    private final EmailSender emailSender;

    @Autowired
    public EmailService(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendSimpleMessage(Mail mail) {
        this.emailSender.sendSimpleMessage(mail.getFrom(), mail.getTo(), mail.getSubject(), mail.getMessage());
    }

    @Override
    public void sendSimpleMessage(EmailVerification mail) {
        this.emailSender.sendSimpleMessage(ADMIN_EMAIL, mail.getEmail(), CONFIRM_MESSAGE,
                LOCAL_HOST + VERIFY_EMAIL_TOKEN + mail.getVerification());
    }

}
