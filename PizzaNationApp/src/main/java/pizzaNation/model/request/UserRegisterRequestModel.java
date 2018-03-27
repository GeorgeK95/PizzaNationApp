package pizzaNation.model.request;

import pizzaNation.annotation.Email;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by George-Lenovo on 27/03/2018.
 */
public class UserRegisterRequestModel {

    //    sign in details
    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 50, message = "Password length cannot be empty and must be more than 8 and less than 50 symbols long.")
    private String password;

    @NotBlank
    @Size(min = 8, max = 50, message = "Password length cannot be empty and must be more than 8 and less than 50 symbols long.")
    private String confirmPassword;

    //    billing address
    @NotBlank
    @Size(max = 50, message = "First name length cannot be empty and must be less than 50 symbols long.")
    private String firstName;

    @NotBlank
    @Size(max = 50, message = "Last name length cannot be empty and must be less than 50 symbols long.")
    private String lastName;

    @NotBlank
    @Size(max = 50, message = "Address length cannot be empty and must less than 50 symbols long.")
    private String address;

    @NotBlank
    @Size(max = 50, message = "City length cannot be empty and must be less than 50 symbols long.")
    private String city;

    @NotNull
    private String gender;

    /*//    contact details
    @Pattern(regexp = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}")
    private String phone;

    private Boolean emailNewsletters;

    private Boolean terms;*/

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
