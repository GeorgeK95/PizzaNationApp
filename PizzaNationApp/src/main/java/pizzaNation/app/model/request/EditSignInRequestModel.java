package pizzaNation.app.model.request;

import pizzaNation.user.annotation.Email;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static pizzaNation.app.util.WebConstants.INVALID_PASSWORD_MESSAGE;

public class EditSignInRequestModel {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 50, message = INVALID_PASSWORD_MESSAGE)
    private String password;

    @NotBlank
    @Size(min = 8, max = 50, message = INVALID_PASSWORD_MESSAGE)
    private String confirm;

//    private String currentEmail;

    private String currentPassword;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirm() {
        return confirm;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

//    public String getCurrentEmail() {
//        return currentEmail;
//    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

//    public void setCurrentEmail(String currentEmail) {
//        this.currentEmail = currentEmail;
//    }
}
