package pizzaNation.app.model.view;

import pizzaNation.user.enumeration.Gender;
import pizzaNation.user.model.entity.Role;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Date;

/**
 * Created by George-Lenovo on 04/04/2018.
 */
public class UserViewModel {

    private String id;

    private String email;

    private String firstName;

    private String lastName;

//    private String address;

//    private Gender gender;

    private String phone;

//    private String city;

    private Boolean emailNewsletters;

    private Date date;

    private String emailVerificationCode;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    /*public String getAddress() {
        return address;
    }

    public Gender getGender() {
        return gender;
    }

    public String getCity() {
        return city;
    }*/

    public String getPhone() {
        return phone;
    }

    public Boolean getEmailNewsletters() {
        return emailNewsletters;
    }

    public Date getDate() {
        return date;
    }

    public String getEmailVerificationCode() {
        return emailVerificationCode;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /*public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setCity(String city) {
        this.city = city;
    }*/

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmailNewsletters(Boolean emailNewsletters) {
        this.emailNewsletters = emailNewsletters;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setEmailVerificationCode(String emailVerificationCode) {
        this.emailVerificationCode = emailVerificationCode;
    }
}
