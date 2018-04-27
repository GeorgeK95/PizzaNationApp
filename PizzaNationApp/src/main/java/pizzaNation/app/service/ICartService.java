package pizzaNation.app.service;

import pizzaNation.app.model.view.ConfirmOrderViewModel;
import pizzaNation.app.model.view.ProductCartViewModel;
import pizzaNation.app.model.view.ProductCartViewModelWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by George-Lenovo on 26/04/2018.
 */
public interface ICartService {
    boolean addProduct(String productName, HttpServletRequest request, HttpServletResponse response);

    boolean validateCartRequest(HttpServletRequest request);

    ProductCartViewModelWrapper getProducts(HttpServletRequest request);

    boolean removeProduct(String productName, HttpServletRequest request, HttpServletResponse response);

    int getCartSize(HttpServletRequest request);

    String getProductsAsJson(HttpServletRequest request);

    boolean confirmOrder(HttpServletRequest request);

    ConfirmOrderViewModel prepareOrder(HttpServletRequest request);
}
