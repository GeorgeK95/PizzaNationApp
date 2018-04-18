package pizzaNation.app.model.view;

/**
 * Created by George-Lenovo on 17/04/2018.
 */
public class HomeViewModel {

    private ProductViewModel bestSeller;

    private ProductViewModel newest;

    private ProductViewModel promotional;

    public HomeViewModel(ProductViewModel bestSeller, ProductViewModel newest, ProductViewModel promotional) {
        this.bestSeller = bestSeller;
        this.newest = newest;
        this.promotional = promotional;
    }

    public ProductViewModel getBestSeller() {
        return bestSeller;
    }

    public ProductViewModel getNewest() {
        return newest;
    }

    public ProductViewModel getPromotional() {
        return promotional;
    }

    public void setBestSeller(ProductViewModel bestSeller) {
        this.bestSeller = bestSeller;
    }

    public void setNewest(ProductViewModel newest) {
        this.newest = newest;
    }

    public void setPromotional(ProductViewModel promotional) {
        this.promotional = promotional;
    }
}
