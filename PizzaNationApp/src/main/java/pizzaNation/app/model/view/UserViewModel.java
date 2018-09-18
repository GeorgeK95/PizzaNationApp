package pizzaNation.app.model.view;

import pizzaNation.user.enumeration.Gender;
import pizzaNation.user.model.entity.Role;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Date;

public class UserViewModel {

    private String id;

    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private String address;

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

    public String getAddress() {
        return address;
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

    public void setAddress(String address) {
        this.address = address;
    }
}
