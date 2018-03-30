
package pizzaNation.user.model.request;

import org.hibernate.validator.constraints.Length;
import pizzaNation.user.annotation.Email;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static pizzaNation.app.util.WebConstants.INVALID_PHONE_FORMAT_MESSAGE;
import static pizzaNation.app.util.WebConstants.PHONE_PATTERN;

/**
 * Created by George-Lenovo on 14/03/2018.
 */

public class ContactUsRequestModel {

    @NotBlank
    @Length(max = 50)
    private String firstName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Pattern(regexp = PHONE_PATTERN, message = INVALID_PHONE_FORMAT_MESSAGE)
    private String phone;

    @NotBlank
    @Length(min = 20)
    private String message;

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getMessage() {
        return message;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}