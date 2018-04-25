package pizzaNation.account.service;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by George-Lenovo on 25/04/2018.
 */
public interface IAccountService {

    boolean tryConfirmAccount(String queryString,RedirectAttributes attributes);

}
