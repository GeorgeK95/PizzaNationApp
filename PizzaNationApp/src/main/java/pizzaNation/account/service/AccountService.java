package pizzaNation.account.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.app.exception.NoEmailVerificationCodeInGetRequestException;
import pizzaNation.user.service.IUserService;

import static pizzaNation.app.util.WebConstants.*;

@Service
@Transactional
public class AccountService implements IAccountService {

    private final IUserService userService;

    public AccountService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean tryConfirmAccount(String queryString, RedirectAttributes attributes) {
        this.verifyQueryString(queryString);

        String token = queryString.split(EQUALS_STR)[1];

        this.userService.confirmAccount(token, attributes);

        return true;
    }

    private void verifyQueryString(String queryString) {
        if (queryString == null || queryString.isEmpty()) {
            throw new NoEmailVerificationCodeInGetRequestException();
        }

        if (!queryString.split(QUESTION_MARK_STR)[0].split(EQUALS_STR)[0].equals(TOKEN))
            //if so tries request as lh:80/confirm?sdasdaasd=123123
            //app accepts requests like lh:80/confirm?verificationCode=123123
            throw new NoEmailVerificationCodeInGetRequestException();
    }
}
