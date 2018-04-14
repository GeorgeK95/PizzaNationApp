package pizzaNation.app.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pizzaNation.admin.repository.MenuRepository;
import pizzaNation.app.model.entity.*;
import pizzaNation.app.enums.Unit;
import pizzaNation.app.repository.IngredientRepository;
import pizzaNation.app.repository.ProductRepository;
import pizzaNation.app.repository.StoreRepository;
import pizzaNation.user.enumeration.Gender;
import pizzaNation.user.model.entity.Role;
import pizzaNation.user.model.entity.User;
import pizzaNation.user.repository.UserRepository;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

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
    private final StoreRepository storeRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, ProductRepository productRepository, MenuRepository menuRepository, IngredientRepository ingredientRepository, StoreRepository storeRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.menuRepository = menuRepository;
        this.ingredientRepository = ingredientRepository;
        this.storeRepository = storeRepository;
    }

    public void run(ApplicationArguments args) {
        this.addUsersWithRoles();
        this.addProducts();
        this.addMenus();
//        this.addIngredients();
        this.addStore();
    }

    private void addStore() {
        Store softUniLocation = new Store(42.667246311064275, 23.352293102516114);
        this.storeRepository.saveAndFlush(softUniLocation);
    }

    private void addIngredients() {
        Ingredient i = new Ingredient(2.2, Unit.KILOGRAM,  /*new BigDecimal(123123),*/ "ketcuhip" /*,   "chervena technost"*/);
        Ingredient i1 = new Ingredient(2.2, Unit.KILOGRAM, /*new BigDecimal(123123),*/"ketcuhip12"/*, "chervena technost" */);
        Ingredient i2 = new Ingredient(2.2, Unit.KILOGRAM, /*new BigDecimal(123123),*/ "ketcuhip2"/*,  "chervena technost"*/);
        Ingredient i3 = new Ingredient(2.2, Unit.KILOGRAM, /*new BigDecimal(123123),*/ "ketcuhip3"/*,  "chervena technost"*/);
        Ingredient i4 = new Ingredient(2.2, Unit.KILOGRAM, /*new BigDecimal(123123),*/ "ketcuhip4"/*,  "chervena technost"*/);
        Ingredient i5 = new Ingredient(2.2, Unit.KILOGRAM, /*new BigDecimal(123123),*/ "ketcuhip5"/*,  "chervena technost"*/);
        Ingredient i6 = new Ingredient(2.2, Unit.KILOGRAM, /*new BigDecimal(123123),*/ "ketcuhip6"/*,  "chervena technost"*/);
        Ingredient i7 = new Ingredient(2.2, Unit.KILOGRAM, /*new BigDecimal(123123),*/ "ketcuhip7"/*,  "chervena technost"*/);
        Ingredient i8 = new Ingredient(2.2, Unit.KILOGRAM, /*new BigDecimal(123123),*/ "ketcuhip9"/*,  "chervena technost"*/);
        Ingredient i9 = new Ingredient(2.2, Unit.KILOGRAM, /*new BigDecimal(123123),*/ "ketcuhip8"/*,  "chervena technost"*/);

        this.ingredientRepository.saveAll(Set.of(i, i1, i2, i3, i4, i5, i6, i7, i8, i9));
    }

    private void addMenus() {
        Image img = new Image("cane.jpg", "https://p-sf1.pcloud.com/DLZ9KNvHJZGcC5YJZekRUZZfhxkU7ZNVZZdy5ZXZgOXZcZZZBuH4S9cj6QVFqFG8LerO4SYQ6mlX/th-6149060342-225x225.jpg");

        Menu m1 = new Menu("name1", "description1"
                , 1, img);
        Menu m2 = new Menu("name2", "description1da"
                , 2, img);
        Menu m3 = new Menu("name3", "descriptiondas1"
                , 3, img);
        Menu m4 = new Menu("name4", "descriptiondsadsa1"
                , 4, img);
        Menu m5 = new Menu("name5", "description1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdasdescription1dasadsadsadsdas"
                , 5, img);

        this.menuRepository.saveAll(Set.of(m1, m2, m3, m4, m5));
    }

    private void addProducts() {
        Image img = new Image("cane.jpg", "https://p-sf1.pcloud.com/DLZ9KNvHJZGcC5YJZekRUZZfhxkU7ZNVZZdy5ZXZgOXZcZZZBuH4S9cj6QVFqFG8LerO4SYQ6mlX/th-6149060342-225x225.jpg");

        Product product1 = new Product("pizzanamee1 ", "details list details  details  details list details list ", 12, true, img);
        Product product2 = new Product("pizzanamee2 ", "details list details  details  details list details list ", 12, true, img);
        Product product3 = new Product("pizzanamee3 ", "details list details  details  details list details list ", 12, true, img);
        Product product4 = new Product("pizzanamee4 ", "details list details  details  details list details list ", 12, false, img);
        Product product5 = new Product("pizzanamee5 ", "details list details  details  details list details list ", 12, false, img);
        Product product6 = new Product("pizzanamee6 ", "details list details  details  details list details list ", 12, false, img);
        Product product7 = new Product("pizzanamee7 ", "details list details  details  details list details list ", 12, false, img);
        Product product8 = new Product("pizzanamee8 ", "details list details  details  details list details list ", 12, false, img);
        Product product9 = new Product("pizzanamee9 ", "details list details  details  details list details list ", 12, true, img);
        Product product10 = new Product("pizzaname10 ", "details list details  details  details list details list", 12, true, img);

        this.productRepository.saveAll(Set.of(
                product1, product2, product3, product4, product5, product6, product7, product8, product9, product10
        ));
    }

    private void addUsersWithRoles() {
        User admin = new User(ADMIN_EMAIL, ADMIN_PASSWORD, ADMIN_FIRST_NAME, ADMIN_LAST_NAME,
                ADMIN_ADDRESS, Gender.MALE, ADMIN_PHONE, ADMIN_CITY, false);
        admin.setEnabled(true);
        User moderator = new User(MODERATOR_EMAIL, MODERATOR_PASSWORD, MODERATOR_FIRST_NAME,
                MODERATOR_LAST_NAME, MODERATOR_ADDRESS, Gender.FEMALE, MODERATOR_PHONE, MODERATOR_CITY, false);
        moderator.setEnabled(true);
        User user = new User(USER_EMAIL, USER_PASSWORD, USER_FIRST_NAME,
                USER_LAST_NAME, USER_ADDRESS, Gender.FEMALE, USER_PHONE, USER_CITY, true);
        user.setEnabled(true);

        Role roleAdmin = new Role(ROLE_ADMIN);
        Role roleModerator = new Role(ROLE_MODERATOR);
        Role roleUser = new Role(ROLE_USER);

        admin.addRole(roleAdmin);
        admin.addRole(roleModerator);
        admin.addRole(roleUser);

        moderator.addRole(roleModerator);
        moderator.addRole(roleUser);

        user.addRole(roleUser);

        this.userRepository.saveAll(Set.of(admin, moderator, user));
    }
}