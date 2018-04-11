package bg.galaxi.listener;

import bg.galaxi.model.Mail;
import bg.galaxi.service.EmailService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static bg.galaxi.util.Constants.SEND_EMAIL_DESTINATION;

/**
 * Created by George-Lenovo on 11/04/2018.
 */
@Component
public class SendEmailListener {

    private final EmailService emailService;

    @Autowired
    public SendEmailListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @JmsListener(destination = SEND_EMAIL_DESTINATION)
    public void clientFeedbackListener(String contactUsFormDataJson) {
        Mail mail = new Gson().fromJson(contactUsFormDataJson, Mail.class);
        this.emailService.sendSimpleMessage(mail);
    }
}
