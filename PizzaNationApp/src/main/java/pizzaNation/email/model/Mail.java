package pizzaNation.email.model;

public class Mail {

    private String from;

    private String to;

    private String subject;

    private String message;

    public Mail() {
    }

    public Mail(String from, String to, String message, String subject) {
        this.from = from;
        this.to = to;
        this.message = message;
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public String getSubject() {
        return subject;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
