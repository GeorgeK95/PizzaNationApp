package pizzaNation.app.model.transfer;

import pizzaNation.app.model.view.UserViewModel;

import java.util.List;
import java.util.Set;

/**
 * Created by George-Lenovo on 18/04/2018.
 */
public class ProductsOnPromotionEmailObject {

    private List<UserViewModel> users;

    private Set<String> productsNames;

    public ProductsOnPromotionEmailObject(List<UserViewModel> users, Set<String> productsNames) {
        this.users = users;
        this.productsNames = productsNames;
    }

    public List<UserViewModel> getUsers() {
        return users;
    }

    public Set<String> getProductsNames() {
        return productsNames;
    }

    public void setUsers(List<UserViewModel> users) {
        this.users = users;
    }

    public void setProductsNames(Set<String> productsNames) {
        this.productsNames = productsNames;
    }
}
