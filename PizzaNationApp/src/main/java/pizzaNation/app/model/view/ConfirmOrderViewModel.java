package pizzaNation.app.model.view;

import java.math.BigDecimal;

/**
 * Created by George-Lenovo on 27/04/2018.
 */
public class ConfirmOrderViewModel {

    private String address;

    private String phone;

    private BigDecimal totalPrice;

    public ConfirmOrderViewModel(String address, String phone, BigDecimal totalPrice) {
        this.address = address;
        this.phone = phone;
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
