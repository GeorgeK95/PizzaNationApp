package pizzaNation.app.model.view;

import java.util.Set;

public class ProductDetailsViewModel {

    private String name;

    private String details;

    private ImageViewModel image;

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public ImageViewModel getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setImage(ImageViewModel image) {
        this.image = image;
    }
}
