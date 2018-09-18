package pizzaNation.email.service.api;

import pizzaNation.email.model.EmailVerification;
import pizzaNation.email.model.Mail;

public interface IEmailService {
    void sendSimpleMessage(Mail mail);

    void sendSimpleMessage(EmailVerification mail);
}
