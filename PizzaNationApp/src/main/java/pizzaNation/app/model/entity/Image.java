package pizzaNation.app.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String url;

   /* @OneToOne(mappedBy = "image")
    private Menu menu;*/

    public Image() {
    }

    public Image(/*String id,*/ String name, String url) {
//        this.setId(id);
        this.setName(name);
        this.setUrl(url);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

//    public Menu getMenu() {
//        return menu;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

//    public void setMenu(Menu menu) {
//        this.menu = menu;
//    }
}
