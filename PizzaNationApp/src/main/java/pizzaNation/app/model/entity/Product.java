package pizzaNation.app.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(unique = true, nullable = false, length = 50)
    private String name;

    @Column(columnDefinition = "text")
    private String details;

    @ManyToMany(mappedBy = "products")
    private Set<Menu> menus;

    private int totalSales;

    private Boolean isPromotional;

    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal price;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", nullable = false, unique = true)
    private Image image;

    @Column(nullable = false)
    private Date date;

    public Product() {
        this.menus = new HashSet<>();
        this.date = new Date();
    }

    public Product(String name, String details, int totalSales, boolean isPromotional, Image img, BigDecimal price) {
        this();
        this.name = name;
        this.details = details;
        this.totalSales = totalSales;
        this.isPromotional = isPromotional;
        this.image = img;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public Boolean getPromotional() {
        return isPromotional;
    }

    public Image getImage() {
        return image;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setMenus(Set<Menu> menu) {
        this.menus = menu;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    public void setPromotional(Boolean promotional) {
        isPromotional = promotional;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPrice(BigDecimal price) {
        this.price = price.setScale(2, RoundingMode.CEILING);
    }
}
