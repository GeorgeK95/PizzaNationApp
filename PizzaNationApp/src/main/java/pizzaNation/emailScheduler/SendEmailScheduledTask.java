package pizzaNation.emailScheduler;

import com.google.gson.Gson;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pizzaNation.app.model.transfer.ProductsOnPromotionEmailObject;
import pizzaNation.app.model.view.UserViewModel;
import pizzaNation.app.service.contract.IProductService;
import pizzaNation.user.service.IUserService;

import java.util.List;
import java.util.Set;

import static pizzaNation.app.util.WebConstants.NEW_PRODUCTS_DESTINATION;
import static pizzaNation.app.util.WebConstants.SCHEDULER_FIXED_RATE;
import static pizzaNation.app.util.WebConstants.SEND_EMAIL_DESTINATION;

/**
 * Created by George-Lenovo on 17/04/2018.
 */
@Component
public class SendEmailScheduledTask {

    private final IUserService userService;

    private final IProductService productService;

    private final JmsTemplate jmsTemplate;

    public SendEmailScheduledTask(IUserService userService, IProductService productService, JmsTemplate jmsTemplate) {
        this.userService = userService;
        this.productService = productService;
        this.jmsTemplate = jmsTemplate;
    }

    @Scheduled(fixedRate = SCHEDULER_FIXED_RATE)
    public void reportCurrentTime() {
        List<UserViewModel> users = this.userService.findAllSubscribed();
        Set<String> productsNames = this.productService.getNewProductsNames();

        ProductsOnPromotionEmailObject transferObject = new ProductsOnPromotionEmailObject(users, productsNames);

        jmsTemplate.convertAndSend(NEW_PRODUCTS_DESTINATION, new Gson().toJson(transferObject));
    }
}
