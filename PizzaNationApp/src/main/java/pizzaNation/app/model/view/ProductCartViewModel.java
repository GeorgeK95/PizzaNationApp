package pizzaNation.app.model.view;

import java.math.BigDecimal;

/**
 * Created by George-Lenovo on 26/04/2018.
 */
public class ProductCartViewModel {

    private String name;

    private BigDecimal price;

    private Integer totalSales;

    private Boolean isPromotional;

    public String getName() {
        return name;
    }

    public Integer getTotalSales() {
        return totalSales;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Boolean getPromotional() {
        return isPromotional;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setTotalSales(Integer totalSales) {
        this.totalSales = totalSales;
    }

    public void setPromotional(Boolean promotional) {
        isPromotional = promotional;
    }
}
