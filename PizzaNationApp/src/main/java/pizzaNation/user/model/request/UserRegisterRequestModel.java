package pizzaNation.user.model.request;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pizzaNation.user.annotation.Email;
import pizzaNation.user.enumeration.Gender;

import javax.validation.constraints.*;

import java.net.URLEncoder;

import static pizzaNation.app.util.WebConstants.*;

public class UserRegisterRequestModel {

    //    sign in details
    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 50, message = INVALID_PASSWORD_MESSAGE)
    private String password;

    @NotBlank
    @Size(min = 8, max = 50, message = INVALID_PASSWORD_MESSAGE)
    private String confirmPassword;

    //    billing address
    @NotBlank
    @Size(max = 50, message = INVALID_FIRST_NAME_MESSAGE)
    private String firstName;

    @NotBlank
    @Size(max = 50, message = INVALID_LAST_NAME_MESSAGE)
    private String lastName;

    @NotBlank
    @Size(max = 50, message = INVALID_ADDRESS_MESSAGE)
    private String address;

    @NotBlank
    @Size(max = 50, message = INVALID_CITY_MESSAGE)
    private String city;

    @NotNull
    private Gender gender;

    @NotBlank
    @Pattern(regexp = PHONE_PATTERN, message = INVALID_PHONE_FORMAT_MESSAGE)
    private String phone;

    private Boolean emailNewsletters;

    @AssertTrue(message = ACCEPT_TERMS_MESSAGE)
    private Boolean terms;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getEmailNewsletters() {
        return emailNewsletters;
    }

    public void setEmailNewsletters(Boolean emailNewsletters) {
        this.emailNewsletters = emailNewsletters;
    }

    public Boolean getTerms() {
        return terms;
    }

    public void setTerms(Boolean terms) {
        this.terms = terms;
    }
}
