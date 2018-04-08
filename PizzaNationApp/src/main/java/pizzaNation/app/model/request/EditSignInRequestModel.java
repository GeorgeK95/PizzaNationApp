package pizzaNation.app.model.request;

/**
 * Created by George-Lenovo on 08/04/2018.
 */
public class EditSignInRequestModel {

    private String email;

    private String password;

    private String confirm;

    private String currentEmail;

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

    public String getCurrentEmail() {
        return currentEmail;
    }

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

    public void setCurrentEmail(String currentEmail) {
        this.currentEmail = currentEmail;
    }
}
