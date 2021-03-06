package pizzaNation.app.util;

import java.util.Set;

public final class WebConstants {

    //Paths
    public static final String CSS_PAGE_FILES = System.getProperty("user.dir").concat("\\src\\main\\resources\\static\\css\\page");
    public static final String JS_PAGE_FILES = System.getProperty("user.dir").concat("\\src\\main\\resources\\static\\js\\page");

    //Static resources dir
    public static final String CSS_PAGE_DIR = "/css/page/";
    public static final String JS_PAGE_DIR = "/js/page/";

    //URL-s
    public static final String ERROR_URL = "/error";
    public static final String UNAUTHORIZED_URL = "/unauthorized";
    public static final String ABOUT_URL = "/about";
    public static final String ACCOUNT_URL = "/account";
    public static final String ACCOUNT_SETTINGS_URL = "/account/settings";
    public static final String ACCOUNT_SETTINGS_EMAIL_URL = "/account/settings/signin";
    public static final String ACCOUNT_SETTINGS_DETAILS_URL = "/account/settings/details";
    public static final String ADMIN_URL = "/admin";
    public static final String TERMS_URL = "/terms";
    public static final String CART_URL = "/cart";
    public static final String CART_CHECKOUT_URL = "/cart/checkout";
    public static final String REGISTER_URL = "/register";
    public static final String LOGIN_URL = "/login";
    public static final String VERIFY_EMAIL = "/verify_email";
    public static final String ACCOUNT_ORDERS_URL = "/account/orders";
    public static final String LOGOUT_URL = "/logout";
    public static final String CONTACT_US_URL = "/contactUs";
    public static final String STORES_URL = "/stores";
    public static final String BASE_LAYOUT_URL = "base-layout";
    public static final String ALL_MENUS_URL = "/menus/all";
    public static final String MENUS_URL = "/menus";
    public static final String MENU_URL = "/menu";
    public static final String ALL_PRODUCTS_URL = "/products/all";
    public static final String PRODUCTS_URL = "/products";
    public static final String ADD_MENUS_URL = "/menus/add";
    public static final String ADD_PRODUCTS_URL = "/products/add";
    public static final String EDIT_MENUS_URL = "/menus/edit/{name}";
    public static final String EDIT_USERS_URL = "/users/edit/{id}";
    public static final String EDIT_PRODUCTS_URL = "/products/edit/{name}";
    public static final String DELETE_MENUS_URL = "/menus/delete/{name}";
    public static final String DELETE_PRODUCTS_URL = "/products/delete/{name}";
    public static final String DELETE_USERS_URL = "/users/delete/{id}";
    public static final String ADMIN_ALL_MENUS_URL = "/admin/menus/all";
    public static final String ADMIN_ALL_PRODUCTS_URL = "/admin/products/all";
    public static final String ADMIN_ALL_USERS_URL = "/admin/users/all";
    public static final String ADMIN_ADD_MENUS_URL = "/admin/menus/add";
    public static final String ADMIN_ADD_PRODUCTS_URL = "/admin/products/add";
    public static final String ADMIN_EDIT_MENUS_URL = "/admin/menus/edit";
    public static final String ADMIN_EDIT_PRODUCTS_URL = "/admin/products/edit";
    public static final String ADMIN_EDIT_USERS_URL = "/admin/users/edit/{id}";
    public static final String USERS_URL = "/users";
    public static final String ALL_USERS_URL = "/users/all";
    public static final String EMPTY_URL = "";
    public static final String MENU_NAME_URL = "/menu/{menuName}";
    public static final String ADMIN_LOGS_ALL_URL = "/admin/logs/all";
    public static final String LOGIN_LOGOUT_URL = "/login?logout";
    public static final String PRODUCT_DETAILS_URL = "/productDetails";

    //Page Titles
    public static final String ABOUT_US_PAGE_TITLE = "About Us";
    public static final String MY_ACCOUNT_PAGE_TITLE = "My Account";
    public static final String MY_PIZZA_NATION = "My Pizza Nation";
    public static final String ADMIN_PANEL_PAGE_TITLE = "Admin Panel";
    public static final String SHOPPING_CART_PAGE_TITLE = "Shopping Cart";
    public static final String CONTACT_US_PAGE_TITLE = "Contact us";
    public static final String PAGE_TITLE_STR = "pageTitle";
    public static final String NOT_FOUND_PAGE_TITLE = "Page Not Found";
    public static final String BAD_REQUEST_PAGE_TITLE = "Bad Request";
    public static final String UNAUTHORIZED_PAGE_TITLE = "Unauthorized";
    public static final String OUR_STORES_PAGE_TITLE = "Our Stores";
    public static final String STORES_MAP_PAGE_TITLE = "stores/map";
    public static final String TERMS_AND_CONDITIONS_PAGE_TITLE = "Terms and Conditions";
    public static final String REGISTER_PAGE_TITLE = "Register";
    public static final String LOG_IN_PAGE_TITLE = "Log In";
    public static final String PIZZA_NATION_PAGE_TITLE = "Pizza Nation";
    public static final String MENU_PAGE_TITLE = "Menu";

    //Strings
    public static final String JAVA_STR = "java";
    public static final String MAP_VIEW_STR = "mapView";
    public static final String SLASH = "/";
    public static final String CONTROLLER_STR = "Controller";
    public static final String MODEL_STR = "model";
    public static final String SLASH_STR = "/";
    public static final String PAGE_STYLE_STR = "pageStyle";
    public static final String PAGE_SCRIPT_STR = "pageScript";
    public static final String VIEW_STR = "view";
    public static final String EMPTY_STR = "";
    public static final String PRICE_SEPARATOR = "\\.";
    public static final String SPACE_STR = " ";
    public static final String REDIRECT_STR = "redirect:";
    public static final String USER_STR = "user";
    public static final String OPERATION_STR = "operation";
    public static final String MODIFIED_TABLE_STR = "modifiedTable";
    public static final String ACCOUNT_CONFIRMED_STR = "account_confirmed";
    public static final String STATUS_CODE_STR = "StatusCode";
    public static final String EMAIL_STR = "email";
    public static final String PASSWORD_STR = "password";
    public static final String EQUALS_STR = "=";
    public static final String QUESTION_MARK_STR = "\\?";
    public static final String TOKEN = "token";
    public static final String LOCAL_HOST = "http://localhost:8080/";
    public static final String VERIFY_EMAIL_TOKEN = "verify_email?token=";
    public static final String CART_ADD_PRODUCT_URL = "/cart/addProduct";
    public static final String CART_PRODUCTS_SIZE_URL = "/cart/cartSize";
    public static final String CART_REMOVE_PRODUCT_URL = "/cart/removeProduct";
    public static final String CART_ID_STR = "cart_id";

    //Extensions
    public static final String CSS_EXTENSION = ".css";
    public static final String JS_EXTENSION = ".js";

    //Errors
    public static final String USER_REGISTER_ERROR = "user_register_error";
    public static final String CONTACT_FORM_ERROR = "contact_form_error";
    public static final String USER_EDIT_ERROR = "user_edit_error";
    public static final String ADD_MENU_ERROR = "add_menu_error";
    public static final String ADD_PRODUCT_ERROR = "add_product_error";
    public static final String EDIT_MENU_ERROR = "edit_menu_error";
    public static final String EDIT_PRODUCT_ERROR = "edit_product_error";

    //Success
    public static final String CONTACT_FORM_SUCCESS = "contact_form_success";
    public static final String USER_EDIT_SUCCESS = "user_edit_success";

    //Messages
    public static final String ACCOUNT_CONFIRMED_SUCCESSFULLY_MESSAGE = "Account confirmed successfully.";
    public static final String PASSWORD_MISMATCH_MESSAGE = "Password mismatch.";
    public static final String EMAIL_ALREADY_TAKEN_MESSAGE = "There is a user with the given email, please choose another.";
    public static final String CHANGES_MADE_SUCCESSFULLY = "Changes were successfully made.";
    public static final String MUST_NOT_BE_BLANK_MESSAGE = "must not be blank";
    public static final String PRIORITY_VALIDATION_MESSAGE = "must be less than or equal to 100";
    public static final String MUST_NOT_BE_NULL_MESSAGE = "must not be null";
    public static final String COMPLETE_ALL_FIELDS_MESSAGE = "Please complete all required fields.";
    public static final String INVALID_PRIORITY_CONSTAINT_MESSAGE = "Invalid value for priority, must be greater than 0 and lower than 50.";
    public static final String INVALID_CURRENT_PASSWORD_MESSAGE = "Your current password is invalid.";
    public static final String INVALID_PASSWORD_MESSAGE = "Password cannot be empty and must be more than 8 and less than 50 symbols long.";
    public static final String INVALID_FIRST_NAME_MESSAGE = "First name cannot be empty and must be less than 50 symbols long.";
    public static final String INVALID_LAST_NAME_MESSAGE = "Last name cannot be empty and must be less than 50 symbols long.";
    public static final String INVALID_ADDRESS_MESSAGE = "Address cannot be empty and must less than 50 symbols long.";
    public static final String INVALID_CITY_MESSAGE = "City cannot be empty and must be less than 50 symbols long.";
    public static final String INVALID_PHONE_FORMAT_MESSAGE = "Invalid phone format.";
    public static final String INVALID_MESSAGE_LENGTH_MESSAGE = "Message length must be at least 20 symbols long.";
    public static final String ACCEPT_TERMS_MESSAGE = "Please, accept our terms.";
    public static final String WRONG_LOGIN_DATA_MESSAGE = "Invalid email or password.";
    public static final String INVALID_MENU_NAME_MESSAGE = "Menu name cannot be empty and must less than 20 symbols long.";
    public static final String INVALID_INGREDIENT_NAME_MESSAGE = "Ingredient name cannot be empty and must less than 50 symbols long.";
    public static final String INVALID_DESCRIPTION_MESSAGE = "Description name cannot be empty.";
    public static final String INVALID_PRODUCT_MESSAGE = "Product description cannot be empty and must be less than 50 symbols long.";
    public static final String INVALID_PRIORITY_MESSAGE = "Priority name cannot be empty and must be between 1 and 50.";
    public static final String INVALID_QUANTITY_MESSAGE = "Quantity value cannot be empty, must be greater or equal than 1 and must me less than 1 000 000.";
    public static final String INVALID_PRICE_MESSAGE = "Price value cannot be empty and must be min 1.";
    public static final String MENU_NAME_ALREADY_TAKEN_MESSAGE = "There is already a menu with the given name, please choose another.";
    public static final String MENU_PRIORITY_ALREADY_TAKEN_MESSAGE = "There is already a menu with the given priority, please choose another.";
    public static final String PRODUCT_NAME_ALREADY_TAKEN_MESSAGE = "There is already a product with the given name, please choose another.";
    public static final String USER_REGISTER_SUCCESS_MESSAGE = "user_register_success";
    public static final String REGISTERED_SUCCESSFULLY_MESSAGE = "Registered successfully, we send you email for confirmation.";
    public static final String SUCCESSFULLY_SENT_FEEDBACK_MESSAGE = "Your feedback was sent successfully.";
    public static final String LOGGER_MESSAGE = "Logger";
    public static final String PRODUCT_ADD_FAILED_MESSAGE = "Error ! Product was not added successfully.";
    public static final String PRODUCT_REMOVE_FAILED_MESSAGE = "Error ! Product was not removed successfully.";

    //MIME-s
    public static final String APPLICATION_JSON_MIME = "application/json";

    //Admin Data
    public static final String ADMIN_EMAIL = "pizzanationapp@gmail.com";
    public static final String ADMIN_PASSWORD = "pizzanationapppizzanationapp";
    public static final String ADMIN_LAST_NAME = "Adminov";
    public static final String ADMIN_FIRST_NAME = "Admin";
    public static final String ADMIN_CITY = "Plovdiv";
    public static final String ADMIN_PHONE = "1234567890";
    public static final String ADMIN_ADDRESS = "Boulevard Pobeda 65";

    //Moderator Data
    public static final String MODERATOR_EMAIL = "pizzanationappmoderator@gmail.com";
    public static final String MODERATOR_ADDRESS = "Boulevard Tzarigradsko Shose 80";
    public static final String MODERATOR_PHONE = "(123)4567890";
    public static final String MODERATOR_CITY = "Sofia";
    public static final String MODERATOR_PASSWORD = "pizzanationappmoderator";
    public static final String MODERATOR_FIRST_NAME = "Moderator";
    public static final String MODERATOR_LAST_NAME = "Moderatorov";

    //User Data
    public static final String USER_EMAIL = /*"jeki_7@abv.bg"; */"user@abv.bg";
    public static final String USER_PASSWORD = "useruser";
    public static final String USER_FIRST_NAME = "User";
    public static final String USER_LAST_NAME = "Userov";
    public static final String USER_ADDRESS = "Boulevard San Stefano 12";
    public static final String USER_PHONE = "1234567890";
    public static final String USER_CITY = "Burgas";

    //Roles
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_MODERATOR = "ROLE_MODERATOR";
    public static final String ROLE_USER = "ROLE_USER";

    //Models
    public static final String USER_REGISTER_REQUEST_MODEL = "userRegisterRequestModel";
    public static final String ADD_MENU_REQUEST_MODEL = "addMenuRequestModel";
    public static final String ADD_PRODUCT_REQUEST_MODEL = "addProductRequestModel";
    public static final String EDIT_MENU_REQUEST_MODEL = "editMenuRequestModel";
    public static final String EDIT_PRODUCT_REQUEST_MODEL = "editProductRequestModel";

    //Patterns
    public static final String PHONE_PATTERN = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
    public static final String UPPERCASE_SPLIT_PATTERN = "(?<=.)(?=\\p{Lu})";

    //Exceptions
    public static final String MENU_EXCEPTION_MESSAGE = "Menu with given name was not found.";
    public static final String IMAGE_EXCEPTION_MESSAGE = "Image upload failed.";
    public static final String USER_EXCEPTION_MESSAGE = "User with given id was not found.";
    public static final String INGREDIENT_EXCEPTION_MESSAGE = "Ingredient with given name was not found.";
    public static final String ADMIN_EXCEPTION_MESSAGE = "Admin account cannot be modified.";
    public static final String PRODUCT_EXCEPTION_MESSAGE = "Product with given name was not found.";
    public static final String USER_WITH_GIVEN_CODE_EXCEPTION_MESSAGE = "Couldn't find user with the given verification code.";
    public static final String CART_NOT_FOUND_EXCEPTION_MESSAGE = "Empty cart, please add some products."; //Your session is either invalid or missing.
    public static final String INVALID_EMAIL_CONFIRM_REQUEST_MESSAGE = "No or invalid verification code found in the request.";

    //Names
    public static final String GENDERS_LIST = "gendersList";
    public static final String PRODUCTS_LIST = "productsList";
    public static final String MENUS_LIST = "menusList";
    public static final String UNITS_LIST = "unitsList";

    //jms
    public static final String SEND_EMAIL_DESTINATION = "client_feedback";

    //Constants
    public static final int CHECK_FOR_DELIVERED_ORDERS_SCHEDULER_FIXED_RATE = 60_000;
    public static final int STACK_TRACE_INDEX = 3;

    //HTML symbols set
    public static final Set<String> HTML_SYMBOLS_TO_ESCAPE = Set.of("%20");

    //Roles
    public static final String HAS_ANY_ROLE_ROLE_ADMIN = "hasAnyRole('ROLE_ADMIN')";
    public static final String HAS_ROLE_ROLE_ADMIN = "hasRole('ROLE_ADMIN')";
    public static final String HAS_ANY_ROLE_ROLE_ADMIN_ROLE_MODERATOR = "hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')";

    //Auth
    public static final String IS_ANONYMOUS = "isAnonymous()";
    public static final String IS_AUTHENTICATED = "isAuthenticated()";

    private WebConstants() {
    }
}
