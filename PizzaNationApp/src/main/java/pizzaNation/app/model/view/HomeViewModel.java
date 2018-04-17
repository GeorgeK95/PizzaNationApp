package pizzaNation.app.model.view;

/**
 * Created by George-Lenovo on 17/04/2018.
 */
public class HomeViewModel {

    private ProductViewModel bestSeller;

    private ProductViewModel newest;

    public HomeViewModel(ProductViewModel bestSeller, ProductViewModel newest) {
        this.bestSeller = bestSeller;
        this.newest = newest;
    }

    public ProductViewModel getBestSeller() {
        return bestSeller;
    }

    public ProductViewModel getNewest() {
        return newest;
    }

    public void setBestSeller(ProductViewModel bestSeller) {
        this.bestSeller = bestSeller;
    }

    public void setNewest(ProductViewModel newest) {
        this.newest = newest;
    }
}
