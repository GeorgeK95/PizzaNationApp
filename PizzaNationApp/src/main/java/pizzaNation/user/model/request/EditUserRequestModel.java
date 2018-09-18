package pizzaNation.user.model.request;

import pizzaNation.user.annotation.Email;
import pizzaNation.user.enumeration.Gender;

import javax.validation.constraints.*;

import static pizzaNation.app.util.WebConstants.*;

public class EditUserRequestModel {

    private String id;
    @Email
    @NotBlank
    private String email;

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

    private Boolean moderator;

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
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

    public Boolean getTerms() {
        return terms;
    }

    public Boolean getModerator() {
        return moderator;
    }

    public String getEmail() {
        return email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
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

    public void setTerms(Boolean terms) {
        this.terms = terms;
    }

    public void setModerator(Boolean moderator) {
        this.moderator = moderator;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
