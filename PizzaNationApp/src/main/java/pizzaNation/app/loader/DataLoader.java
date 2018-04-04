package pizzaNation.app.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pizzaNation.admin.repository.MenuRepository;
import pizzaNation.app.model.entity.Ingredient;
import pizzaNation.app.model.entity.Menu;
import pizzaNation.app.model.entity.Product;
import pizzaNation.app.model.enums.Unit;
import pizzaNation.app.repository.IngredientRepository;
import pizzaNation.app.repository.ProductRepository;
import pizzaNation.app.service.IIngredientService;
import pizzaNation.user.enumeration.Gender;
import pizzaNation.user.model.entity.Role;
import pizzaNation.user.model.entity.User;
import pizzaNation.user.repository.UserRepository;

import java.math.BigDecimal;
import java.util.Set;

import static pizzaNation.app.util.WebConstants.*;

/**
 * Created by George-Lenovo on 21/03/2018.
 */
@Component
public class DataLoader implements ApplicationRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final MenuRepository menuRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, ProductRepository productRepository, MenuRepository menuRepository, IngredientRepository ingredientRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.menuRepository = menuRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public void run(ApplicationArguments args) {
        this.addUsersWithRoles();
        this.addProducts();
        this.addMenus();
        this.addIngredients();
    }

    private void addIngredients() {
        Ingredient i = new Ingredient(2.2, Unit.KILOGRAM, new BigDecimal(123123), "ketcuhip", "chervena technost");
        Ingredient i1 = new Ingredient(2.2, Unit.KILOGRAM, new BigDecimal(123123), "ketcuhip12", "chervena technost");
        Ingredient i2 = new Ingredient(2.2, Unit.KILOGRAM, new BigDecimal(123123), "ketcuhip2", "chervena technost");
        Ingredient i3 = new Ingredient(2.2, Unit.KILOGRAM, new BigDecimal(123123), "ketcuhip3", "chervena technost");
        Ingredient i4 = new Ingredient(2.2, Unit.KILOGRAM, new BigDecimal(123123), "ketcuhip4", "chervena technost");
        Ingredient i5 = new Ingredient(2.2, Unit.KILOGRAM, new BigDecimal(123123), "ketcuhip5", "chervena technost");
        Ingredient i6 = new Ingredient(2.2, Unit.KILOGRAM, new BigDecimal(123123), "ketcuhip6", "chervena technost");
        Ingredient i7 = new Ingredient(2.2, Unit.KILOGRAM, new BigDecimal(123123), "ketcuhip7", "chervena technost");
        Ingredient i8 = new Ingredient(2.2, Unit.KILOGRAM, new BigDecimal(123123), "ketcuhip9", "chervena technost");
        Ingredient i9 = new Ingredient(2.2, Unit.KILOGRAM, new BigDecimal(123123), "ketcuhip8", "chervena technost");

        this.ingredientRepository.saveAll(Set.of(i, i1, i2, i3, i4, i5, i6, i7, i8, i9));
    }

    private void addMenus() {
        Menu m1 = new Menu("name1", "description1"
                , 2, null);
        Menu m2 = new Menu("name2", "description1da"
                , 2, null);
        Menu m3 = new Menu("name3", "descriptiondas1"
                , 3, null);
        Menu m4 = new Menu("name4", "descriptiondsadsa1"
                , 4, null);
        Menu m5 = new Menu("name5", "description1dasadsadsadsdas"
                , 5, null);

        this.menuRepository.saveAll(Set.of(m1, m2, m3, m4, m5));
    }

    private void addProducts() {
        Product product1 = new Product("pizzanamee1 ", "details list details  details  details list details list ");
        Product product2 = new Product("pizzanamee2 ", "details list details  details  details list details list ");
        Product product3 = new Product("pizzanamee3 ", "details list details  details  details list details list ");
        Product product4 = new Product("pizzanamee4 ", "details list details  details  details list details list ");
        Product product5 = new Product("pizzanamee5 ", "details list details  details  details list details list ");
        Product product6 = new Product("pizzanamee6 ", "details list details  details  details list details list ");
        Product product7 = new Product("pizzanamee7 ", "details list details  details  details list details list ");
        Product product8 = new Product("pizzanamee8 ", "details list details  details  details list details list ");
        Product product9 = new Product("pizzanamee9 ", "details list details  details  details list details list ");
        Product product10 = new Product("pizzaname10 ", "details list details  details  details list details list");

        this.productRepository.saveAll(Set.of(
                product1, product2, product3, product4, product5, product6, product7, product8, product9, product10
        ));
    }

    private void addUsersWithRoles() {
        User admin = new User(ADMIN_EMAIL, ADMIN_PASSWORD, ADMIN_FIRST_NAME, ADMIN_LAST_NAME,
                ADMIN_ADDRESS, Gender.MALE, ADMIN_PHONE, ADMIN_CITY);
        User moderator = new User(MODERATOR_EMAIL, MODERATOR_PASSWORD, MODERATOR_FIRST_NAME,
                MODERATOR_LAST_NAME, MODERATOR_ADDRESS, Gender.FEMALE, MODERATOR_PHONE, MODERATOR_CITY);

        Role roleAdmin = new Role(ROLE_ADMIN);
        Role roleModerator = new Role(ROLE_MODERATOR);
        Role roleUser = new Role(ROLE_USER);

        admin.addRole(roleAdmin);
        admin.addRole(roleModerator);
        admin.addRole(roleUser);

        moderator.addRole(roleModerator);
        moderator.addRole(roleUser);

        this.userRepository.saveAll(Set.of(admin, moderator));
    }
}