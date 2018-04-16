package pizzaNation.app.model.view;

import org.springframework.web.bind.annotation.ModelAttribute;
import pizzaNation.app.util.DTOConverter;

/**
 * Created by George-Lenovo on 01/04/2018.
 */
public class ProductViewModel {

    private String id;

    private String name;

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

    public Boolean getPromotional() {
        return isPromotional;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotalSales(Integer totalSales) {
        this.totalSales = totalSales;
    }

    public void setPromotional(Boolean promotional) {
        isPromotional = promotional;
    }
}
