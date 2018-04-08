package pizzaNation.app.model.response;

import java.util.Date;

/**
 * Created by George-Lenovo on 02/04/2018.
 */
public class ProductResponseModel {

    private String name;

    private String details;

    private Integer totalSales;

    private Boolean isPromotional;

    private Date date;

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public Integer getTotalSales() {
        return totalSales;
    }

    public Boolean getPromotional() {
        return isPromotional;
    }

    public Date getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setTotalSales(Integer totalSales) {
        this.totalSales = totalSales;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPromotional(Boolean promotional) {
        isPromotional = promotional;
    }
}
