package bg.galaxi.listener;

import bg.galaxi.model.EmailVerification;
import bg.galaxi.model.Mail;
import bg.galaxi.service.EmailService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static bg.galaxi.util.Constants.SEND_EMAIL_DESTINATION;
import static bg.galaxi.util.Constants.USER_ARRIVED_DESTINATION;

/**
 * Created by George-Lenovo on 11/04/2018.
 */
@Component
public class UserArrivedListener {

    private final EmailService emailService;

    @Autowired
    public UserArrivedListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @JmsListener(destination = USER_ARRIVED_DESTINATION)
    public void clientFeedbackListener(String emailVerificationJson) {
        EmailVerification emailVerification = new Gson().fromJson(emailVerificationJson, EmailVerification.class);
        this.emailService.sendSimpleMessage(emailVerification);
    }
}
