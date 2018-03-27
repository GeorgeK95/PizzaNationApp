package pizzaNation.util;

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
    public static final String BASE_LAYOUT_URL = "base-layout";
    public static final String CART_URL = "/cart";
    public static final String CONTACT_US_URL = "/contactUs";
    public static final String STORES_URL = "/stores";
    public static final String TERMS_URL = "/terms";
    public static final String REGISTER_URL = "/register";
    public static final String LOGIN_URL = "/login";

    //Page Titles
    public static final String ABOUT_US_PAGE_TITLE = "About Us";
    public static final String ADMIN_PANEL_PAGE_TITLE = "Admin Panel";
    public static final String SHOPPING_CART_PAGE_TITLE = "Shopping Cart";
    public static final String CONTACT_US_PAGE_TITLE = "Contact us";
    public static final String PAGE_TITLE_STR = "pageTitle";
    public static final String NOT_FOUND_PAGE_TITLE = "Page Not Found";
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

    //Messages
    public static final String PASSWORD_MISMATCH_MESSAGE = "Password mismatch.";
    public static final String MUST_NOT_BE_BLANK_MESSAGE = "must not be blank";
    public static final String MUST_NOT_BE_NULL_MESSAGE = "must not be null";
    public static final String COMPLETE_ALL_FIELDS_MESSAGE = "Please complete all required fields.";

    //Models
    public static final String USER_REGISTER_REQUEST_MODEL = "userRegisterRequestModel";

    private WebConstants() {
    }
}