package pizzaNation.app.model.view;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by George-Lenovo on 26/04/2018.
 */
public class ProductCartViewModelWrapper {

    private static final BigDecimal DELIVERY_PRICE = new BigDecimal(10);

    private BigDecimal subTotalPrice;

    private BigDecimal totalPriceWithDelivery;

    private List<ProductCartViewModel> products;

    public ProductCartViewModelWrapper() {
        products = new ArrayList<>();
    }

    public void setTotalPriceForCurrentProductsInCart() {
        this.subTotalPrice = this.calculateTotalPrice();
    }

    public void setTotalPriceForCurrentProductsInCartWithDelivery() {
        this.totalPriceWithDelivery = this.calculateTotalPriceWithDelivery();
    }

    public BigDecimal calculateTotalPriceWithDelivery() {
        return this.calculateTotalPrice().add(DELIVERY_PRICE);
    }

    public BigDecimal calculateTotalPrice() {
        return this.products.stream().map(ProductCartViewModel::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean addProduct(ProductCartViewModel product) {
        this.products.add(product);
        return true;
    }

    public boolean removeProduct(ProductCartViewModel product) {
        Iterator<ProductCartViewModel> productsIterator = this.products.iterator();
        boolean isDeletedFlag = false;

        while (productsIterator.hasNext()) {
            if (productsIterator.next().getName().equals(product.getName()) &&
                    !isDeletedFlag) {
                productsIterator.remove();
                isDeletedFlag = true;
            }
        }

//        this.products.removeIf(next -> next.getName().equals(product.getName()));

        return true;
    }

    public List<ProductCartViewModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductCartViewModel> products) {
        this.products = products;
    }

    public BigDecimal getTotalPriceWithDelivery() {
        if (this.totalPriceWithDelivery==null)
            this.setTotalPriceForCurrentProductsInCartWithDelivery();
        return totalPriceWithDelivery;
    }
}
