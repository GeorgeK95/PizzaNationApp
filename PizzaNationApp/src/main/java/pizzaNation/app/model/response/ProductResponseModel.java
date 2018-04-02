package pizzaNation.app.model.response;

import java.util.Date;

/**
 * Created by George-Lenovo on 02/04/2018.
 */
public class ProductResponseModel {

    private String name;

    private String details;

    private Date date;

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public Date getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
