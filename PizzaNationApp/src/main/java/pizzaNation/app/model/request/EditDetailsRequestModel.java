package pizzaNation.app.model.request;

import pizzaNation.user.enumeration.Gender;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

import static pizzaNation.app.util.WebConstants.*;

/**
 * Created by George-Lenovo on 08/04/2018.
 */
public class EditDetailsRequestModel {

    @NotBlank
    @Size(max = 50, message = INVALID_FIRST_NAME_MESSAGE)
    private String firstName;

    @NotBlank
    @Size(max = 50, message = INVALID_LAST_NAME_MESSAGE)
    private String lastName;

    @NotNull
    private Gender gender;

    @NotBlank
    @Pattern(regexp = PHONE_PATTERN, message = INVALID_PHONE_FORMAT_MESSAGE)
    private String phone;

    private Boolean emailNewsletters;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public Boolean getEmailNewsletters() {
        return emailNewsletters;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmailNewsletters(Boolean emailNewsletters) {
        this.emailNewsletters = emailNewsletters;
    }
}
