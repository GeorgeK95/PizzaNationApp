package pizzaNation.app.model.view;

import javax.swing.text.html.ImageView;

/**
 * Created by George-Lenovo on 17/04/2018.
 */
public class HomeProductViewModel {

    private String name;

    private String description;

    private ImageViewModel image;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ImageViewModel getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(ImageViewModel image) {
        this.image = image;
    }
}
