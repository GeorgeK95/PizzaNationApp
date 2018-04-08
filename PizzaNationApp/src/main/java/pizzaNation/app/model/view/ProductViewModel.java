package pizzaNation.app.model.view;

import org.springframework.web.bind.annotation.ModelAttribute;
import pizzaNation.app.util.DTOConverter;

/**
 * Created by George-Lenovo on 01/04/2018.
 */
public class ProductViewModel {
    //TOOD:dali ne se preizpolzva drugade ?

    private String id;

    private String name;

//    private String[] productIds;

//    private Set<Ingredient> ingredients;

    private Integer totalSales;

    private Boolean isPromotional;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getTotalSales() {
        return totalSales;
    }

    public Boolean getIsPromotional() {
        return isPromotional;
    }
    //    public String[] getProductsIds() {
//        return productIds;
//    }

//    public void setProductsIds(String[] productIds) {
//        this.productIds = productIds;
//    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotalSales(Integer totalSales) {
        this.totalSales = totalSales;
    }

    public void setIsPromotional(Boolean promotional) {
        isPromotional = promotional;
    }
}
