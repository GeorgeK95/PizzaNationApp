package pizzaNation.email.factory;


import pizzaNation.email.model.EmailVerification;
import pizzaNation.email.model.Mail;

public final class EmailFactory {

    public static EmailVerification generateEmailVerificationObject(String email, String code) {
        return new EmailVerification(email, code);
    }

    public static Mail generateMailObject(String from, String to, String subject, String content) {
        return new Mail(from, to, content,subject);
    }

    private EmailFactory() {
    }
}
