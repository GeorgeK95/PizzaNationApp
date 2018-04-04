package pizzaNation.app.util;

/**
 * Created by George-Lenovo on 23/03/2018.
 */
public final class WebConstants {

    //Paths
    public static final String CSS_PAGE_FILES = System.getProperty("user.dir").concat("\\src\\main\\resources\\static\\css\\page");
    public static final String JS_PAGE_FILES = System.getProperty("user.dir").concat("\\src\\main\\resources\\static\\js\\page");

    //Static resources dir
    public static final String CSS_PAGE_DIR = "/css/page/";
    public static final String JS_PAGE_DIR = "/js/page/";

    //URL-s
    public static final String ERROR_URL = "/error";
    public static final String ABOUT_URL = "/about";
    public static final String ADMIN_URL = "/admin";
    public static final String TERMS_URL = "/terms";
    public static final String CART_URL = "/cart";
    public static final String REGISTER_URL = "/register";
    public static final String LOGIN_URL = "/login";
    public static final String CONTACT_US_URL = "/contactUs";
    public static final String STORES_URL = "/stores";
    public static final String BASE_LAYOUT_URL = "base-layout";
    public static final String ADD_STORES_URL = "/stores/add";
    public static final String ALL_MENUS_URL = "/menus/all";
    public static final String ALL_PRODUCTS_URL = "/products/all";
    public static final String ALL_INGREDIENTS_URL = "/ingredients/all";
    public static final String ADD_MENUS_URL = "/menus/add";
    public static final String ADD_PRODUCTS_URL = "/products/add";
    public static final String ADD_INGREDIENTS_URL = "/ingredients/add";
    public static final String EDIT_MENUS_URL = "/menus/edit/{name}";
    public static final String EDIT_PRODUCTS_URL = "/products/edit/{name}";
    public static final String EDIT_INGREDIENTS_URL = "/ingredients/edit/{name}";
    public static final String DELETE_MENUS_URL = "/menus/delete/{name}";
    public static final String DELETE_PRODUCTS_URL = "/products/delete/{name}";
    public static final String DELETE_INGREDIENTS_URL = "/ingredients/delete/{name}";
    public static final String ADMIN_ALL_MENUS_URL = "/admin/menus/all";
    public static final String ADMIN_ALL_PRODUCTS_URL = "/admin/products/all";
    public static final String ADMIN_ALL_INGREDIENTS_URL = "/admin/ingredients/all";
    public static final String ADMIN_ADD_MENUS_URL = "/admin/menus/add";
    public static final String ADMIN_ADD_PRODUCTS_URL = "/admin/products/add";
    public static final String ADMIN_ADD_INGREDIENTS_URL = "/admin/ingredients/add";
    public static final String ADMIN_EDIT_MENUS_URL = "/admin/menus/edit";
    public static final String ADMIN_EDIT_PRODUCTS_URL = "/admin/products/edit";
    public static final String ADMIN_EDIT_INGREDIENTS_URL = "/admin/ingredients/edit";
    public static final String ALL_USERS_URL = "/users/all";
    public static final String EMPTY_URL = "";
    public static final String MENU_NAME_URL = "/menu/{name}";

    //Page Titles
    public static final String ABOUT_US_PAGE_TITLE = "About Us";
    public static final String ADMIN_PANEL_PAGE_TITLE = "Admin Panel";
    public static final String SHOPPING_CART_PAGE_TITLE = "Shopping Cart";
    public static final String CONTACT_US_PAGE_TITLE = "Contact us";
    public static final String PAGE_TITLE_STR = "pageTitle";
    public static final String NOT_FOUND_PAGE_TITLE = "Page Not Found";
    public static final String MENU_NOT_FOUND_PAGE_TITLE = "Menu Not Found";
    public static final String OUR_STORES_PAGE_TITLE = "Our Stores";
    public static final String STORES_MAP_PAGE_TITLE = "stores/map";
    public static final String TERMS_AND_CONDITIONS_PAGE_TITLE = "Terms and Conditions";
    public static final String REGISTER_PAGE_TITLE = "Register";
    public static final String LOG_IN_PAGE_TITLE = "Log In";
    public static final String PIZZA_NATION_PAGE_TITLE = "Pizza Nation";
    public static final String MENU_URL = "menu";
    public static final String MENU_PAGE_TITLE = "Menu";

    //Strings
    public static final String MAP_VIEW_STR = "mapView";
    public static final String CONTROLLER_STR = "Controller";
    public static final String MODEL_STR = "model";
    public static final String SLASH_STR = "/";
    public static final String PAGE_STYLE_STR = "pageStyle";
    public static final String PAGE_SCRIPT_STR = "pageScript";
    public static final String VIEW_STR = "view";
    public static final String EMPTY_STR = "";
    public static final String REDIRECT_STR = "redirect:";

    //Extensions
    public static final String CSS_EXTENSION = ".css";
    public static final String JS_EXTENSION = ".js";

    //Errors
    public static final String USER_REGISTER_ERROR = "user_register_error";
    public static final String ADD_MENU_ERROR = "add_menu_error";
    public static final String ADD_INGREDIENT_ERROR = "add_ingredient_error";
    public static final String ADD_PRODUCT_ERROR = "add_product_error";
    public static final String EDIT_MENU_ERROR = "edit_menu_error";
    public static final String EDIT_INGREDIENT_ERROR = "edit_ingredient_error";
    public static final String EDIT_PRODUCT_ERROR = "edit_product_error";

    //Messages
    public static final String PASSWORD_MISMATCH_MESSAGE = "Password mismatch.";
    public static final String MUST_NOT_BE_BLANK_MESSAGE = "must not be blank";
    public static final String PRIORITY_VALIDATION_MESSAGE = "must be less than or equal to 100";
    public static final String MUST_NOT_BE_NULL_MESSAGE = "must not be null";
    public static final String COMPLETE_ALL_FIELDS_MESSAGE = "Please complete all required fields.";
    public static final String INVALID_PRIORITY_CONSTAINT_MESSAGE = "Invalid value for priority.";
    public static final String INVALID_PASSWORD_MESSAGE = "Password cannot be empty and must be more than 8 and less than 50 symbols long.";
    public static final String INVALID_FIRST_NAME_MESSAGE = "First name cannot be empty and must be less than 50 symbols long.";
    public static final String INVALID_LAST_NAME_MESSAGE = "Last name cannot be empty and must be less than 50 symbols long.";
    public static final String INVALID_ADDRESS_MESSAGE = "Address cannot be empty and must less than 50 symbols long.";
    public static final String INVALID_CITY_MESSAGE = "City cannot be empty and must be less than 50 symbols long.";
    public static final String INVALID_PHONE_FORMAT_MESSAGE = "Invalid phone format.";
    public static final String ACCEPT_TERMS_MESSAGE = "Please, accept our terms.";
    public static final String WRONG_LOGIN_DATA_MESSAGE = "Invalid email or password.";
    public static final String INVALID_MENU_NAME_MESSAGE = "Menu name cannot be empty and must less than 20 symbols long.";
    public static final String INVALID_INGREDIENT_NAME_MESSAGE = "Ingredient name cannot be empty and must less than 50 symbols long.";
    public static final String INVALID_PRODUCT_NAME_MESSAGE = "Product name cannot be empty and must less than 50 symbols long.";
    public static final String INVALID_DESCRIPTION_MESSAGE = "Description name cannot be empty.";
    public static final String INVALID_PRODUCT_MESSAGE = "Product description cannot be empty and must be less than 50 symbols long.";
    public static final String INVALID_PRIORITY_MESSAGE = "Priority name cannot be empty and must be between 1 and 100.";
    public static final String INVALID_QUANTITY_MESSAGE = "Quantity value cannot be empty and must be min 1.";
    public static final String INVALID_PRICE_MESSAGE = "Price value cannot be empty and must be min 1.";
    public static final String INVALID_NULL_MESSAGE = "Unit field cannot be empty.";
    public static final String MENU_NAME_ALREADY_TAKEN_MESSAGE = "There is already a menu with the given name, please choose another.";
    public static final String INGREDIENT_NAME_ALREADY_TAKEN_MESSAGE = "There is already a ingredient with the given name, please choose another.";
    public static final String PRODUCT_NAME_ALREADY_TAKEN_MESSAGE = "There is already a product with the given name, please choose another.";

    //Admin Data
    public static final String ADMIN_EMAIL = "admin@admin.com";
    public static final String ADMIN_PASSWORD = "adminadmin";
    public static final String ADMIN_LAST_NAME = "Admin";
    public static final String ADMIN_FIRST_NAME = "Admin";
    public static final String ADMIN_CITY = "Plovdiv";
    public static final String ADMIN_PHONE = "1234567890";
    public static final String ADMIN_ADDRESS = "bulevard Pobeda 65";

    //Moderator Data
    public static final String MODERATOR_EMAIL = "moderator@moderator.com";
    public static final String MODERATOR_ADDRESS = "bulevard Tzarigradsko Shose 80";
    public static final String MODERATOR_PHONE = "(123)4567890";
    public static final String MODERATOR_CITY = "Sofia";
    public static final String MODERATOR_PASSWORD = "moderator";
    public static final String MODERATOR_FIRST_NAME = "Moderator";
    public static final String MODERATOR_LAST_NAME = "Moderator";

    //Roles
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_MODERATOR = "ROLE_MODERATOR";
    public static final String ROLE_USER = "ROLE_USER";

    //Models
    public static final String USER_REGISTER_REQUEST_MODEL = "userRegisterRequestModel";
    public static final String ADD_MENU_REQUEST_MODEL = "addMenuRequestModel";
    public static final String ADD_PRODUCT_REQUEST_MODEL = "addProductRequestModel";
    public static final String ADD_INGREDIENT_REQUEST_MODEL = "addIngredientRequestModel";
    public static final String EDIT_MENU_REQUEST_MODEL = "editMenuRequestModel";
    public static final String EDIT_PRODUCT_REQUEST_MODEL = "editProductRequestModel";
    public static final String EDIT_INGREDIENT_REQUEST_MODEL = "editIngredientRequestModel";

    //Patterns
    public static final String PHONE_PATTERN = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
    public static final String UPPERCASE_SPLIT_PATTERN = "(?<=.)(?=\\p{Lu})";

    //Exceptions
    public static final String MENU_EXCEPTION_MESSAGE = "Menu with given name was not found.";
    public static final String INGREDIENT_EXCEPTION_MESSAGE = "Ingredient with given name was not found.";
    public static final String PRODUCT_EXCEPTION_MESSAGE = "Product with given name was not found.";

    private WebConstants() {
    }
}
