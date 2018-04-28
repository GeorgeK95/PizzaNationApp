package pizzaNation.app.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaNation.app.config.PizzaNationSecurityConfiguration;
import pizzaNation.app.enums.OrderStatus;
import pizzaNation.app.exception.SessionNotFoundException;
import pizzaNation.app.model.entity.Order;
import pizzaNation.app.model.entity.Product;
import pizzaNation.app.model.view.ConfirmOrderViewModel;
import pizzaNation.app.model.view.ProductCartViewModel;
import pizzaNation.app.model.view.ProductCartViewModelWrapper;
import pizzaNation.app.repository.ProductRepository;
import pizzaNation.app.service.contract.IOrderService;
import pizzaNation.app.service.contract.IProductService;
import pizzaNation.user.model.entity.User;
import pizzaNation.user.service.IUserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

import static pizzaNation.app.util.WebConstants.*;

/**
 * Created by George-Lenovo on 26/04/2018.
 */
@Service
@Transactional
public class CartService implements ICartService {

    private final IProductService productService;
    private final ProductRepository productRepository;
    private final IUserService userService;
    private final IOrderService orderRepository;

    private Map<String, String> sessionCartData;
    private Map<String, ProductCartViewModelWrapper> cartProductsSessionData;
    private Map<String, ProductCartViewModelWrapper> oldCartProductsSessionData;

    @Autowired
    public CartService(IProductService productService, ProductRepository productRepository, IUserService userService, IOrderService orderRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.sessionCartData = new HashMap<>();
        this.cartProductsSessionData = new HashMap<>();
        this.oldCartProductsSessionData = new HashMap<>();
    }

    @Override
    public boolean addProduct(String productName, HttpServletRequest request, HttpServletResponse response) {
        String sessionId = request.getSession().getId();

        //za taq sesiq   imash taq cart
        String cartId = UUID.randomUUID().toString();
        if (this.sessionCartData.containsKey(sessionId)) {
            //imame value za takava sesiq, vzimame dr cart
            cartId = this.sessionCartData.get(sessionId);
        }

        Optional<Cookie> cookieOptional = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals(CART_ID_STR)).findFirst();
        if (cookieOptional.isPresent() && this.sessionCartData.containsKey(sessionId)) {
            //imam veche cart
            cartId = cookieOptional.get().getValue();
        } else {
            //nqma, biem muy nov
            Cookie cookie = new Cookie(CART_ID_STR, cartId);
            cookie.setPath(SLASH_STR);
            response.addCookie(cookie);
        }

        //ako nqmash takava sesiq, zapisvame q s cartid
        this.sessionCartData.putIfAbsent(sessionId, cartId);

        ProductCartViewModelWrapper productsWrapper = new ProductCartViewModelWrapper();
        if (this.cartProductsSessionData.containsKey(cartId)) {
            productsWrapper = this.cartProductsSessionData.get(cartId);
        }

        productsWrapper.addProduct(this.productService.findByName(productName, ProductCartViewModel.class));

        this.cartProductsSessionData.put(cartId, productsWrapper);

        return true;
    }

    @Override
    public boolean validateCartRequest(HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        if (!this.sessionCartData.containsKey(sessionId))
            return false;

        String cartId = this.sessionCartData.get(request.getSession().getId());
        return this.cartProductsSessionData.containsKey(cartId);
    }

    @Override
    public ProductCartViewModelWrapper getProducts(HttpServletRequest request) {
        String cartId = this.sessionCartData.get(request.getSession().getId());

        return this.cartProductsSessionData.get(cartId);
    }

    @Override
    public boolean removeProduct(String productName, HttpServletRequest request, HttpServletResponse response) {
        if (!this.validateCartRequest(request)) return false;

        ProductCartViewModelWrapper productsWrapper = this.getProducts(request);

        return productsWrapper.removeProduct(this.productService.findByName(productName, ProductCartViewModel.class));
    }

    @Override
    public int getCartSize(HttpServletRequest request) {
        if (!this.validateCartRequest(request)) return 0;

        return this.getProducts(request).getProducts().size();
    }

    @Override
    public String getProductsAsJson(HttpServletRequest request) {
        ProductCartViewModelWrapper products = this.getProducts(request);
        products.setTotalPriceForCurrentProductsInCart();
        products.setTotalPriceForCurrentProductsInCartWithDelivery();
        return new Gson().toJson(products);
    }

    @Override
    public boolean confirmOrder(HttpServletRequest request) {
        if (!this.validateCartRequest(request)) return false;

        Order order = this.buildOrderObject(request);

        this.orderRepository.persistOrder(order);

        return true;
    }

    @Override
    public ConfirmOrderViewModel prepareOrder(HttpServletRequest request) {
        if (!this.validateCartRequest(request)) throw new SessionNotFoundException();

        if (this.getCartSize(request) == 0) return null;

        User user = this.userService.findUserByEmail(PizzaNationSecurityConfiguration.getCurrentlyLoggedInUserEmail());

        return new ConfirmOrderViewModel(user.getAddress(), user.getPhone(), this.getProducts(request).getTotalPriceWithDelivery());
    }

    @Override
    public boolean reorder(HttpServletRequest request, HttpServletResponse response) {
        String cartId = this.sessionCartData.get(request.getSession().getId());
        ProductCartViewModelWrapper products = oldCartProductsSessionData.get(cartId);

        for (ProductCartViewModel current : products.getProducts()) {
            this.addProduct(current.getName(), request, response);
        }

        return false;
    }

    @Override
    public boolean setCartCookie(HttpServletRequest request) {
        Optional<Cookie> cookieOptional = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals(CART_ID_STR)).findFirst();

        if (!cookieOptional.isPresent()) return false;

        String cartCookie = cookieOptional.get().getValue();

        if (this.oldCartProductsSessionData.containsKey(cartCookie)) {
            this.sessionCartData.put(request.getSession().getId(), cartCookie);

            ProductCartViewModelWrapper products = this.oldCartProductsSessionData.get(cartCookie);

            this.cartProductsSessionData.put(cartCookie, products);
        }

        return true;
    }


    private Order buildOrderObject(HttpServletRequest request) {
        Set<String> productsNames = this.getProducts(request).getProducts().stream().map(ProductCartViewModel::getName).collect(Collectors.toSet());

        Set<Product> productsAsEntityObjects = this.productRepository.findAllByNameIn(productsNames);

        User user = this.userService.findUserByEmail(PizzaNationSecurityConfiguration.getCurrentlyLoggedInUserEmail());

        this.deleteCart(request, user.getEmail());

        return new Order(productsAsEntityObjects, user, OrderStatus.IN_PROGRESS);
    }

    private void deleteCart(HttpServletRequest request, String email) {
        String sessionId = request.getSession().getId();
        Optional<Cookie> cartIdCookie = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals(CART_ID_STR)).findFirst();

        if (!this.sessionCartData.containsKey(sessionId) ||
                !cartIdCookie.isPresent() ||
                !this.cartProductsSessionData.containsKey(cartIdCookie.get().getValue()))
            throw new SessionNotFoundException();

        oldCartProductsSessionData.put(email, this.cartProductsSessionData.get(cartIdCookie.get().getValue())); //save user last order

        this.sessionCartData.remove(sessionId);
        this.cartProductsSessionData.remove(cartIdCookie.get().getValue());
    }
}
