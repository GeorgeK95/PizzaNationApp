package pizzaNation.app.model.view;

/**
 * Created by George-Lenovo on 23/04/2018.
 */
public class MenuProductsViewModel {

//    private String id;

    private String name;

    private String details;

    private Integer totalSales;

    private Boolean isPromotional;

    private ImageViewModel image;

    private IngredientViewModel ingredients;

//    public String getId() {
//        return id;
//    }

    public String getName() {
        return name;
    }

    public Integer getTotalSales() {
        return totalSales;
    }

    public Boolean getPromotional() {
        return isPromotional;
    }

    public ImageViewModel getImage() {
        return image;
    }

    public IngredientViewModel getIngredients() {
        return ingredients;
    }

    public String getDetails() {
        return details;
    }

//    public void setId(String id) {
//        this.id = id;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotalSales(Integer totalSales) {
        this.totalSales = totalSales;
    }

    public void setPromotional(Boolean promotional) {
        isPromotional = promotional;
    }

    public void setImage(ImageViewModel image) {
        this.image = image;
    }

    public void setIngredients(IngredientViewModel ingredients) {
        this.ingredients = ingredients;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
