package pizzaNation.model.response;

/**
 * Created by George-Lenovo on 23/03/2018.
 */
public class StoreResponseModel {
    private Double x;
    private Double y;

    public StoreResponseModel(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
