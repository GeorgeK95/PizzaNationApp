package pizzaNation.app.model.view;

import javax.validation.constraints.NotBlank;

import java.util.Date;

public class MenuViewModel {

    private String name;

    private String description;

    private Integer priority;

    private String[] productIds;

    private ImageViewModel image;

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

    public ImageViewModel getImage() {
        return image;
    }

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

    public void setImage(ImageViewModel image) {
        this.image = image;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
