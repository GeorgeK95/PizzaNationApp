package pizzaNation.email.model;

public class Mail {

    private String email;

    private String firstName;

    private String message;

    private String subject;

    public Mail() {
    }

    public Mail(String email, String firstName, String message, String subject) {
        this.email = email;
        this.firstName = firstName;
        this.message = message;
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMessage() {
        return message;
    }

    public String getSubject() {
        return subject;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
