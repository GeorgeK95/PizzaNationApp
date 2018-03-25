package pizzaNation.model;

import org.springframework.stereotype.Component;

/**
 * Created by George-Lenovo on 25/03/2018.
 */
@Component
public class PeshoModel {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
