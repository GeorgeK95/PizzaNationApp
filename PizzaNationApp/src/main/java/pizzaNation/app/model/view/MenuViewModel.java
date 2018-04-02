package pizzaNation.app.model.view;

import javax.validation.constraints.NotBlank;

import java.util.Date;

import static pizzaNation.app.util.WebConstants.INVALID_DESCRIPTION_MESSAGE;
import static pizzaNation.app.util.WebConstants.INVALID_PRIORITY_MESSAGE;

/**
 * Created by George-Lenovo on 02/04/2018.
 */
public class MenuViewModel {

//    private String id;

    private String name;

    private String description;

    private Integer priority;

    private String[] productIds;

    private Date date;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPriority() {
        return priority;
    }

    public String[] getProductIds() {
        return productIds;
    }

//    public String getId() {
//        return id;
//    }

    public Date getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setProductIds(String[] productIds) {
        this.productIds = productIds;
    }

//    public void setId(String id) {
//        this.id = id;
//    }


    public void setDate(Date date) {
        this.date = date;
    }
}
