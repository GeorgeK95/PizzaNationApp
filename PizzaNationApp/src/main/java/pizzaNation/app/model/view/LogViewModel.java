package pizzaNation.app.model.view;

import pizzaNation.app.enums.Action;
import pizzaNation.app.enums.TableEnum;
import pizzaNation.user.model.entity.User;

import java.util.Date;

/**
 * Created by George-Lenovo on 17/04/2018.
 */
public class LogViewModel {

    private Action action;

    private TableEnum affectedTable;

    private UserViewModel user;

    private Date date;

    public Action getAction() {
        return action;
    }

    public TableEnum getAffectedTable() {
        return affectedTable;
    }

    public UserViewModel getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void setAffectedTable(TableEnum affectedTable) {
        this.affectedTable = affectedTable;
    }

    public void setUser(UserViewModel user) {
        this.user = user;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
