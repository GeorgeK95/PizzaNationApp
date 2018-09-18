package pizzaNation.email.model;

public class EmailVerification {

    private String email;

    private String verification;

    public EmailVerification(String email, String verification) {
        this.email = email;
        this.verification = verification;
    }

    public String getEmail() {
        return email;
    }

    public String getVerification() {
        return verification;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }
}
