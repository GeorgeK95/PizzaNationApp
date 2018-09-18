package pizzaNation.app.model.view;

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
