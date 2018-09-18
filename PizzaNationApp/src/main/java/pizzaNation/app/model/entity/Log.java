package pizzaNation.app.model.entity;

import org.hibernate.annotations.GenericGenerator;
import pizzaNation.app.enums.Action;
import pizzaNation.app.enums.TableEnum;
import pizzaNation.user.model.entity.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Log {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Action action;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TableEnum affectedTable;

    @ManyToOne
    @JoinColumn(nullable = false, name = "action_made_by")
    private User user;

    private Date date;

    public Log() {
        this.date = new Date();
    }

    public Log(Action action, TableEnum affectedTable, User user) {
        this();
        this.action = action;
        this.affectedTable = affectedTable;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public Action getAction() {
        return action;
    }

    public TableEnum getAffectedTable() {
        return affectedTable;
    }

    public User getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void setAffectedTable(TableEnum affectedTable) {
        this.affectedTable = affectedTable;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
