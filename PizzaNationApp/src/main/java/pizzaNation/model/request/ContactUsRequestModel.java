package pizzaNation.model.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created by George-Lenovo on 14/03/2018.
 */
public class ContactUsRequestModel {

    @NotNull
    @Length(min = 5, max = 50)
    private String email;

    @NotNull
    @Length(min = 5, max = 100)
    private String subject;

    @NotNull
    @Length(min = 20)
    private String content;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
