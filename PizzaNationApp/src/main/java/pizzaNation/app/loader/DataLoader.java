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
import java.util.stream.Collectors;

import static pizzaNation.app.util.WebConstants.*;

/**
 * Created by George-Lenovo on 21/03/2018.
 */
@Component
public class DataLoader implements ApplicationRunner {

    private static final String NAPOLITAN_IMG_NAME = "pizza_img_1.jpg";
    private static final String MEALTY_IMG_NAME = "pizza_img_1.jpg";
    private static final String YORK_IMG_NAME = "pizza_img_1.jpg";
    private static final String CHICAGO_IMG_NAME = "pizza_img_1.jpg";
    private static final String SICILIAN_IMG_NAME = "pizza_img_1.jpg";
    private static final String GREEK_IMG_NAME = "pizza_img_1.jpg";
    private static final String CALI_IMG_NAME = "pizza_img_1.jpg";
    private static final String TOMATO_IMG_NAME = "pizza_img_1.jpg";
    private static final String PEPPERONI_IMG_NAME = "pizza_img_1.jpg";
    private static final String ALLIGATOR_IMG_NAME = "pizza_img_1.jpg";
    private static final String NAPOLETAN_IMG_URL = "https://p-sf1.pcloud.com/DLZRjwk4JZam9ThJZekRUZZjdMVU7ZNVZZdy5ZXZ9qWZnkZZZAKxmESbM6U8h0clqv5tHpJSHlVL7/th-6197159206-600x300.jpg";
    private static final String NAPOLITAN_PRODUCT_NAME = "Neapolitan Pizza ";
    private static final String MEALTY_PRODUCT_NAME = "Mealty-one ";
    private static final String YORK_PRODUCT_NAME = "New York Style Pizza ";
    private static final String CHICAGO_PRODUCT_NAME = "Chicago Pizza ";
    private static final String SICILIAN_PRODUCT_NAME = "Sicilian Pizza ";
    private static final String GREEK_PRODUCT_NAME = "Greek Pizza ";
    private static final String CALI_PRODUCT_NAME = "California Pizza ";
    private static final String TOMATO_PRODUCT_NAME = "Tomato Pie ";
    private static final String PEPPERONI_PRODUCT_NAME = "Double Pepperoni ";
    private static final String NAPOLITAN_PRODUCT_DETAILS = "Neapolitan - the original pizza. This delicious pie has a history that dates all the way back to 18th century Naples, Italy. During this time, the poorer citizens of this seaside city frequently purchased food that was cheap and could be eaten quickly. Luckily for them, Neapolitan pizza – a flatbread with tomatoes, cheese, oil, and garlic – was affordable and readily available through numerous street vendors. ";
    private static final String MEALTY_PRODUCT_DETAILS = "Pizza with a log ot meat.";
    private static final String YORK_PRODUCT_DETAILS = "While New York-style pizza isn’t exactly the original, it’s become the most popular and widespread choice in the United States. Even though Neapolitan and New York pizzas share similarities, there are distinct differences. Some people will tell you that it’s the minerals in the Big Apple’s water used to make the dough that makes this pizza stand out. However, in order to make a proper New York-style pie, the crust still needs to be thin, like a Neapolitan, but thick enough to fold a slice in half lengthwise. This simplifies eating the pizza without utensils, which is a necessity in New York City's fast-paced setting.";
    private static final String CHICAGO_PRODUCT_DETAILS = "Chicago pizza, also commonly referred to as deep-dish pizza, gets its name from the city it was invented in. During the early 1900’s, Italian immigrants in the windy city were searching for something similar to the Neapolitan pizza that they knew and loved. Instead of imitating the notoriously thin pie, Ike Sewell had something else in mind. He created a pizza with a thick crust that had raised edges, similar to a pie, and ingredients in reverse, with slices of mozzarella lining the dough followed by meat, vegetables, and then topped with a can of crushed tomatoes. This original creation led Sewell to create the now famous chain restaurant, Pizzeria Uno.";
    private static final String SICILIAN_PRODUCT_DETAILS = "Sicilian pizza, also known as sfincione, may seem like a distant cousin of a Chicago-style pie, but the two have their differences. It's not even the same pizza that you'd get in Sicily. So what’s the deal with this complicated pizza? Well, no matter what country you get this square cut, thick crust pizza from, it should always have a spongier consistency than other pizzas. However, sfincione is typically topped with a tomato sauce, onions, herbs, anchovies, and then covered with bread crumbs. This version is typically served on holidays like Christmas and New Year’s Eve in Sicily. But in America, Sicilian pizza features a simple combination of tomato sauce and mozzarella cheese and is eaten all year round.";
    private static final String GREEK_PRODUCT_DETAILS = "Despite its name, Greek pizza has nothing to do with Greek toppings, nor was it invented in Greece. In fact, pizza isn’t even a common dish in the Mediterranean country, despite its close location to pizza’s birth place, Italy. Greek pizza was created by Greek immigrants who came to America and were introduced to Italian pizza. Instead of following the strict guidelines of New York or Chicago-style, Greek pizza has its own rules. While this style has a crust that is puffier and chewier than thin crust pizzas, it’s not quite as thick as a deep-dish or Sicilian crust. Greek pizza also typically uses a tangy tomato paste that has a strong oregano flavor and is topped with a blend of mozzarella and cheddar cheese.";
    private static final String CALI_PRODUCT_DETAILS = "California pizza, or gourmet pizza, is known for its unusual ingredients. This pizza got its start back in the late 1970’s when Chef Ed LaDou began experimenting with pizza recipes in the classic Italian restaurant, Prego. By chance, he served one of his newest creations, mustard, ricotta, pate, and red pepper, to Wolfgang Puck. Impressed with LaDou’s innovative pie, Puck invited him to be a head pizza chef at his restaurant. It was here that LaDou came up with over 250 unique pizza recipes that eventually formed the menu of chain restaurant, California Pizza Kitchen.";
    private static final String TOMATO_PRODUCT_DETAILS = "Derived from Sicilian pizza, Italian tomato pie is a thick crust, square cut pizza that features focaccia-like dough and plenty of sweet and tangy tomato sauce. If you travel to Philadelphia to try a square of this delicious treat, it’ll most likely feature “gravy” - another name for tomato sauce - poured over a crispy and doughy crust. However, in other areas, tomato pie can feature cheese and other toppings with the sauce poured over top. But what makes tomato pie really stand out amongst other pizza options is the fact that it is commonly served and eaten at room temperature.";
    private static final String PEPPERONI_PRODUCT_DETAILS = "Traditional italian double pepperoni pizza. Enjoy !";
    private static final int TOTAL_SALES = 0;
    private static final int PEPPERONI_PRODUCT_SALES = 1;
    private static final boolean PROMOTIONAL = true;
    private static final boolean NON_PROMOTIONAl = false;
    private static final String ALLIGATOR_PRODUCT_NAME = "Alligator";
    private static final String ALLIGATOR_PRODUCT_DETAILS = "When you eat a pizza like this, you become a real alligator ! ";
    private static final String MENU_PROMOTIONS_IMG_NAME = "prom_img_2.jpg";
    private static final String MENU_PIZZAS_IMG_NAME = "pizza_img_2.jpg";
    private static final String MENU_DRINKS_IMG_NAME = "drinks_img_2.jpg";
    private static final String MENU_DESSERTS_IMG_NAME = "dess_img_2.jpg";
    private static final String MENU_PROMOTIONS_NAME = "Promotions";
    private static final String MENU_PIZZAS_NAME = "Pizzas";
    private static final String MENU_DRINKS_NAME = "Drinks";
    private static final String MENU_DESSERTS_NAME = "Desserts";
    private static final String MENU_PROMOTIONS_DESCRIPTION = "Check out our newest promotional products.";
    private static final String MENU_PIZZAS_DESCRIPTION = "Who wants pizza? Taste our delicious pizza products.";
    private static final String MENU_DRINKS_DESCRIPTION = "Are you tasty? Pizza is not the same without the appropriate drink.";
    private static final String MENU_DESSERTS_DESCRIPTION = "We work with the best sweet companies. Browse our desserts gamma and order some.";
    private static final int PRIORITY_1 = 1;
    private static final int PRIORITY_2 = 2;
    private static final int PRIORITY_3 = 3;
    private static final int PRIORITY_4 = 4;
    private static final double SOFTUNI_LAT = 42.667246311064275;
    private static final double SOFTUNI_LNG = 23.352293102516114;
    private static final String KETCHUP = "Ketchup";
    private static final String MAYONNAISE = "Mayonnaise";
    private static final String MUSTARD = "Mustard";
    private static final boolean NON_SUBSCRIBED = false;
    private static final boolean SUBSCRIBED = true;
    private static final boolean ENABLED = true;
    private static final BigDecimal LOW_PRICE = new BigDecimal(10);
    private static final BigDecimal MEDIUM_PRICE = new BigDecimal(20);
    private static final BigDecimal HIGH_PRICE = new BigDecimal(30);

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
        this.addMenus();
        this.addProducts();
        this.addIngredients();
        this.addStore();
    }

    private void setPromotionalProductsToPromotionsMenu(Set<Product> productsSet) {
        Menu promotionMenu = this.menuRepository.findByName(MENU_PROMOTIONS_NAME);

        promotionMenu.setProducts(productsSet);

        this.menuRepository.saveAndFlush(promotionMenu);
    }

    private void addStore() {
        Store softUniLocation = new Store(SOFTUNI_LAT, SOFTUNI_LNG);
        this.storeRepository.saveAndFlush(softUniLocation);
    }

    private void addIngredients() {
        Ingredient i = new Ingredient(2.2, Unit.KILOGRAMS, KETCHUP);
        Ingredient i1 = new Ingredient(2.2, Unit.KILOGRAMS, MAYONNAISE);
        Ingredient i2 = new Ingredient(2.2, Unit.KILOGRAMS, MUSTARD);

        this.ingredientRepository.saveAll(Set.of(i, i1, i2/*, i3, i4, i5, i6, i7, i8, i9*/));
    }

    private void addMenus() {
        Image promotionsImg = new Image(MENU_PROMOTIONS_IMG_NAME, NAPOLETAN_IMG_URL);
        Image pizzasImg = new Image(MENU_PIZZAS_IMG_NAME, NAPOLETAN_IMG_URL);
        Image drinksImg = new Image(MENU_DRINKS_IMG_NAME, NAPOLETAN_IMG_URL);
        Image dessertsImg = new Image(MENU_DESSERTS_IMG_NAME, NAPOLETAN_IMG_URL);

        Menu promotions = new Menu(MENU_PROMOTIONS_NAME, MENU_PROMOTIONS_DESCRIPTION
                , PRIORITY_1, promotionsImg);
        Menu pizzas = new Menu(MENU_PIZZAS_NAME, MENU_PIZZAS_DESCRIPTION
                , PRIORITY_2, pizzasImg);
        Menu drinks = new Menu(MENU_DRINKS_NAME, MENU_DRINKS_DESCRIPTION
                , PRIORITY_3, drinksImg);
        Menu desserts = new Menu(MENU_DESSERTS_NAME, MENU_DESSERTS_DESCRIPTION
                , PRIORITY_4, dessertsImg);

        this.menuRepository.saveAll(Set.of(promotions, pizzas, drinks, desserts));
    }

    private void addProducts() {
        Image neapolitanImg = new Image(NAPOLITAN_IMG_NAME, NAPOLETAN_IMG_URL);
        Image mealtyImg = new Image(MEALTY_IMG_NAME, NAPOLETAN_IMG_URL);
        Image newYorkStyleImg = new Image(YORK_IMG_NAME, NAPOLETAN_IMG_URL);
        Image chicagoImg = new Image(CHICAGO_IMG_NAME, NAPOLETAN_IMG_URL);
        Image sicilianImg = new Image(SICILIAN_IMG_NAME, NAPOLETAN_IMG_URL);
        Image greekImg = new Image(GREEK_IMG_NAME, NAPOLETAN_IMG_URL);
        Image californiaImg = new Image(CALI_IMG_NAME, NAPOLETAN_IMG_URL);
        Image tomatoImg = new Image(TOMATO_IMG_NAME, NAPOLETAN_IMG_URL);
        Image pepperoniImg = new Image(PEPPERONI_IMG_NAME, NAPOLETAN_IMG_URL);
        Image alligatorImg = new Image(ALLIGATOR_IMG_NAME, NAPOLETAN_IMG_URL);

        Product neapolitan = new Product(NAPOLITAN_PRODUCT_NAME, NAPOLITAN_PRODUCT_DETAILS, TOTAL_SALES, PROMOTIONAL, neapolitanImg, LOW_PRICE);
        Product mealty = new Product(MEALTY_PRODUCT_NAME, MEALTY_PRODUCT_DETAILS, TOTAL_SALES, PROMOTIONAL, mealtyImg, LOW_PRICE);
        Product newYorkStyle = new Product(YORK_PRODUCT_NAME, YORK_PRODUCT_DETAILS, TOTAL_SALES, PROMOTIONAL, newYorkStyleImg, LOW_PRICE);
        Product chicago = new Product(CHICAGO_PRODUCT_NAME, CHICAGO_PRODUCT_DETAILS, TOTAL_SALES, NON_PROMOTIONAl, chicagoImg, LOW_PRICE);
        Product sicilian = new Product(SICILIAN_PRODUCT_NAME, SICILIAN_PRODUCT_DETAILS, TOTAL_SALES, NON_PROMOTIONAl, sicilianImg, LOW_PRICE);
        Product greek = new Product(GREEK_PRODUCT_NAME, GREEK_PRODUCT_DETAILS, TOTAL_SALES, NON_PROMOTIONAl, greekImg, MEDIUM_PRICE);
        Product california = new Product(CALI_PRODUCT_NAME, CALI_PRODUCT_DETAILS, TOTAL_SALES, NON_PROMOTIONAl, californiaImg, MEDIUM_PRICE);
        Product tomato = new Product(TOMATO_PRODUCT_NAME, TOMATO_PRODUCT_DETAILS, TOTAL_SALES, NON_PROMOTIONAl, tomatoImg, HIGH_PRICE);
        Product pepperoni = new Product(PEPPERONI_PRODUCT_NAME, PEPPERONI_PRODUCT_DETAILS, PEPPERONI_PRODUCT_SALES, PROMOTIONAL, pepperoniImg, HIGH_PRICE);

        Set<Product> productsSet = Set.of(mealty, neapolitan, chicago, newYorkStyle, sicilian, greek, california, tomato, pepperoni);

        Set<Product> promotionalProducts = productsSet.stream().filter(Product::getPromotional).collect(Collectors.toSet());
        Set<Product> nonPromotionalProducts = productsSet.stream().filter(p -> !p.getPromotional()).collect(Collectors.toSet());

        this.setPromotionalProductsToPromotionsMenu(promotionalProducts);

        Product alligator = new Product(ALLIGATOR_PRODUCT_NAME, ALLIGATOR_PRODUCT_DETAILS, TOTAL_SALES, PROMOTIONAL, alligatorImg, HIGH_PRICE);

        this.productRepository.saveAndFlush(alligator);
        this.productRepository.saveAll(nonPromotionalProducts);
    }

    private void addUsersWithRoles() {
        User admin = new User(ADMIN_EMAIL, ADMIN_PASSWORD, ADMIN_FIRST_NAME, ADMIN_LAST_NAME,
                ADMIN_ADDRESS, Gender.MALE, ADMIN_PHONE, ADMIN_CITY, NON_SUBSCRIBED);
        admin.setEnabled(ENABLED);
        User moderator = new User(MODERATOR_EMAIL, MODERATOR_PASSWORD, MODERATOR_FIRST_NAME,
                MODERATOR_LAST_NAME, MODERATOR_ADDRESS, Gender.FEMALE, MODERATOR_PHONE, MODERATOR_CITY, NON_SUBSCRIBED);
        moderator.setEnabled(ENABLED);
        User user = new User(USER_EMAIL, USER_PASSWORD, USER_FIRST_NAME,
                USER_LAST_NAME, USER_ADDRESS, Gender.FEMALE, USER_PHONE, USER_CITY, SUBSCRIBED);
        user.setEnabled(ENABLED);

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