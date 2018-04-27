package bg.galaxi.listener;

import bg.galaxi.model.EmailVerification;
import bg.galaxi.service.EmailService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static bg.galaxi.util.Constants.PROMOTIONAL_PRODUCT_DESTINATION;
import static bg.galaxi.util.Constants.USER_ARRIVED_DESTINATION;

/**
 * Created by George-Lenovo on 26/04/2018.
 */
@Component
public class ProductArrivedListener {

    private final EmailService emailService;

    @Autowired
    public ProductArrivedListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @JmsListener(destination = PROMOTIONAL_PRODUCT_DESTINATION)
    public void newPromotionalProductListener(String emailVerificationJson) {
        EmailVerification emailVerification = new Gson().fromJson(emailVerificationJson, EmailVerification.class);
        this.emailService.newProductMessage(emailVerification);
    }
}
