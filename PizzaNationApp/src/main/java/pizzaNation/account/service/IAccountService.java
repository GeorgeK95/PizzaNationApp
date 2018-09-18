package pizzaNation.account.service;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface IAccountService {

    boolean tryConfirmAccount(String queryString,RedirectAttributes attributes);

}
