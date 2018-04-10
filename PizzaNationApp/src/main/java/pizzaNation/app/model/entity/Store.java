package pizzaNation.app.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by George-Lenovo on 10/04/2018.
 */
@Entity
public class Store {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(nullable = false)
    private Double lat;

    @Column(nullable = false)
    private Double lng;

    public Store() {
    }

    public Store(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public String getId() {
        return id;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
