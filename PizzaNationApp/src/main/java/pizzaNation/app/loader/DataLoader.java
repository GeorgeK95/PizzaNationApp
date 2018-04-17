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
//        this.addIngredients();
        this.addMenus();
        this.addStore();

        //this.setPromotionalProductsToPromotionsMenu();
    }

    private void addStore() {
        Store softUniLocation = new Store(42.667246311064275, 23.352293102516114);
        this.storeRepository.saveAndFlush(softUniLocation);
    }

    /*private void addIngredients() {
        Ingredient i = new Ingredient(2.2, Unit.KILOGRAM,  *//*new BigDecimal(0303),*//* "ketcuhip" *//*,   "chervena technost"*//*);
        Ingredient i1 = new Ingredient(2.2, Unit.KILOGRAM, *//*new BigDecimal(0303),*//*"ketcuhip0"*//*, "chervena technost" *//*);
        Ingredient i2 = new Ingredient(2.2, Unit.KILOGRAM, *//*new BigDecimal(0303),*//* "ketcuhip2"*//*,  "chervena technost"*//*);
        Ingredient i3 = new Ingredient(2.2, Unit.KILOGRAM, *//*new BigDecimal(0303),*//* "ketcuhip3"*//*,  "chervena technost"*//*);
        Ingredient i4 = new Ingredient(2.2, Unit.KILOGRAM, *//*new BigDecimal(0303),*//* "ketcuhip4"*//*,  "chervena technost"*//*);
        Ingredient i5 = new Ingredient(2.2, Unit.KILOGRAM, *//*new BigDecimal(0303),*//* "ketcuhip5"*//*,  "chervena technost"*//*);
        Ingredient i6 = new Ingredient(2.2, Unit.KILOGRAM, *//*new BigDecimal(0303),*//* "ketcuhip6"*//*,  "chervena technost"*//*);
        Ingredient i7 = new Ingredient(2.2, Unit.KILOGRAM, *//*new BigDecimal(0303),*//* "ketcuhip7"*//*,  "chervena technost"*//*);
        Ingredient i8 = new Ingredient(2.2, Unit.KILOGRAM, *//*new BigDecimal(0303),*//* "ketcuhip9"*//*,  "chervena technost"*//*);
        Ingredient i9 = new Ingredient(2.2, Unit.KILOGRAM, *//*new BigDecimal(0303),*//* "ketcuhip8"*//*,  "chervena technost"*//*);

        this.ingredientRepository.saveAll(Set.of(i, i1, i2, i3, i4, i5, i6, i7, i8, i9));
    }*/

    private void addMenus() {
        Image promotionsImg = new Image("prom_img_2.jpg", "https://p-sf1.pcloud.com/DLZWjwk4JZ34eThJZekRUZZuFYVU7ZNVZZdy5ZXZHyWZg7ZZZikiDQ9uvRm5mjXds0hGxDjSIMcgk/th-6197151521-600x300.jpg");
        Image pizzasImg = new Image("pizza_img_2.jpg", "https://p-sf1.pcloud.com/DLZRjwk4JZam9ThJZekRUZZuFYVU7ZNVZZdy5ZXZ9qWZnkZZZVIRxBrfrD2B2JqdN1vrXLyLdcJlk/th-6197159206-600x300.jpg");
        Image drinksImg = new Image("drinks_img_2.jpg", "https://p-sf1.pcloud.com/DLZQnwk4JZWGTThJZekRUZZsFYVU7ZNVZZdy5ZXZs1LZ5kZZZdjAsfvOrx9bKIzPezwwuUjTvDxE7/th-6197168879-600x300.jpg");
        Image dessertsImg = new Image("dess_img_2.jpg", "https://p-sf1.pcloud.com/DLZHTKk4JZ0TPThJZekRUZZypYVU7ZNVZZdy5ZXZ43mZT7ZZZAP2wDP7d0HRGHak1JbwpUSIBDmDV/th-6197175079-600x300.jpg");

        Menu promotions = new Menu("Promotions", "Check out our newest promotional products."
                , 1, promotionsImg);
        Menu pizzas = new Menu("Pizzas", "Who wants pizza? Taste our delicious pizza products."
                , 2, pizzasImg);
        Menu drinks = new Menu("Drinks", "Are you tasty? Pizza is not the same without the appropriate drink."
                , 3, drinksImg);
        Menu desserts = new Menu("Desserts", "We work with the best sweet companies. Browse our desserts gamma and order some."
                , 4, dessertsImg);

        this.menuRepository.saveAll(Set.of(promotions, pizzas, drinks, desserts));
    }

    private void addProducts() {
        Image productImg = new Image("cane.jpg", "https://p-sf1.pcloud.com/DLZ9KNvHJZGcC5YJZekRUZZBJ4VU7ZNVZZdy5ZXZgOXZcZZZ6GeXU3ceY0B5WsYR4quayQUvCw0k/th-6149060342-225x225.jpg");

        Product neapolitan = new Product("Neapolitan Pizza ", "Neapolitan - the original pizza. This delicious pie has a history that dates all the way back to 18th century Naples, Italy. During this time, the poorer citizens of this seaside city frequently purchased food that was cheap and could be eaten quickly. Luckily for them, Neapolitan pizza – a flatbread with tomatoes, cheese, oil, and garlic – was affordable and readily available through numerous street vendors. ", 0, true, productImg);
        Product mealty = new Product("Mealty-one ", "Pizza with a log ot meat.", 0, true, productImg);
        Product newYorkStyle = new Product("New York Style Pizza ", "While New York-style pizza isn’t exactly the original, it’s become the most popular and widespread choice in the United States. Even though Neapolitan and New York pizzas share similarities, there are distinct differences. Some people will tell you that it’s the minerals in the Big Apple’s water used to make the dough that makes this pizza stand out. However, in order to make a proper New York-style pie, the crust still needs to be thin, like a Neapolitan, but thick enough to fold a slice in half lengthwise. This simplifies eating the pizza without utensils, which is a necessity in New York City's fast-paced setting.", 0, true, productImg);
        Product chicago = new Product("Chicago Pizza ", "Chicago pizza, also commonly referred to as deep-dish pizza, gets its name from the city it was invented in. During the early 1900’s, Italian immigrants in the windy city were searching for something similar to the Neapolitan pizza that they knew and loved. Instead of imitating the notoriously thin pie, Ike Sewell had something else in mind. He created a pizza with a thick crust that had raised edges, similar to a pie, and ingredients in reverse, with slices of mozzarella lining the dough followed by meat, vegetables, and then topped with a can of crushed tomatoes. This original creation led Sewell to create the now famous chain restaurant, Pizzeria Uno.", 0, false, productImg);
        Product sicilian = new Product("Sicilian Pizza ", "Sicilian pizza, also known as sfincione, may seem like a distant cousin of a Chicago-style pie, but the two have their differences. It's not even the same pizza that you'd get in Sicily. So what’s the deal with this complicated pizza? Well, no matter what country you get this square cut, thick crust pizza from, it should always have a spongier consistency than other pizzas. However, sfincione is typically topped with a tomato sauce, onions, herbs, anchovies, and then covered with bread crumbs. This version is typically served on holidays like Christmas and New Year’s Eve in Sicily. But in America, Sicilian pizza features a simple combination of tomato sauce and mozzarella cheese and is eaten all year round.", 0, true, productImg);
        Product greek = new Product("Greek Pizza ", "Despite its name, Greek pizza has nothing to do with Greek toppings, nor was it invented in Greece. In fact, pizza isn’t even a common dish in the Mediterranean country, despite its close location to pizza’s birth place, Italy. Greek pizza was created by Greek immigrants who came to America and were introduced to Italian pizza. Instead of following the strict guidelines of New York or Chicago-style, Greek pizza has its own rules. While this style has a crust that is puffier and chewier than thin crust pizzas, it’s not quite as thick as a deep-dish or Sicilian crust. Greek pizza also typically uses a tangy tomato paste that has a strong oregano flavor and is topped with a blend of mozzarella and cheddar cheese.", 0, false, productImg);
        Product california = new Product("California Pizza ", "California pizza, or gourmet pizza, is known for its unusual ingredients. This pizza got its start back in the late 1970’s when Chef Ed LaDou began experimenting with pizza recipes in the classic Italian restaurant, Prego. By chance, he served one of his newest creations, mustard, ricotta, pate, and red pepper, to Wolfgang Puck. Impressed with LaDou’s innovative pie, Puck invited him to be a head pizza chef at his restaurant. It was here that LaDou came up with over 250 unique pizza recipes that eventually formed the menu of chain restaurant, California Pizza Kitchen.", 0, false, productImg);
        Product tomato = new Product("Tomato Pie ", "Derived from Sicilian pizza, Italian tomato pie is a thick crust, square cut pizza that features focaccia-like dough and plenty of sweet and tangy tomato sauce. If you travel to Philadelphia to try a square of this delicious treat, it’ll most likely feature “gravy” - another name for tomato sauce - poured over a crispy and doughy crust. However, in other areas, tomato pie can feature cheese and other toppings with the sauce poured over top. But what makes tomato pie really stand out amongst other pizza options is the fact that it is commonly served and eaten at room temperature.", 0, false, productImg);
        Product alligator = new Product("Alligator ", "When you eat a pizza like this, you become a real alligator ! ", 0, true, productImg);
        Product product10 = new Product("Double Pepperoni ", "Traditional italian double pepperoni pizza. Enjoy !", 0, true, productImg);

        this.productRepository.saveAll(Set.of(
                mealty, neapolitan, chicago, newYorkStyle, sicilian, greek, california, tomato, alligator, product10
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