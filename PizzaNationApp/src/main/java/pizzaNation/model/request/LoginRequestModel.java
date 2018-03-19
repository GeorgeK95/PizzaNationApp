package pizzaNation.model.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created by George-Lenovo on 15/03/2018.
 */
public class LoginRequestModel {

    @NotNull
    @Length(min = 5, max = 50)
    private String email;

    @NotNull
//    @Password
    private String password;

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
}
