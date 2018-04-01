package pizzaNation.app.model.response;

import org.springframework.web.bind.annotation.ModelAttribute;
import pizzaNation.app.util.DTOConverter;

/**
 * Created by George-Lenovo on 01/04/2018.
 */
public class ProductViewModel {
    //TOOD:dali ne se preizpolzva drugade ?

    private String id;

    private String name;

    private String[] productIds;

//    private Set<Ingredient> ingredients;


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String[] getProductIds() {
        return productIds;
    }

    public void setProductIds(String[] productIds) {
        this.productIds = productIds;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
