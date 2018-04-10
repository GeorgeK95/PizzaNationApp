package pizzaNation.app.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pizzaNation.app.model.response.StoreResponseModel;
import pizzaNation.app.service.contract.IStoreService;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.*;

/**
 * Created by George-Lenovo on 15/03/2018.
 */
@Controller
public class StoresController extends BaseController {

    private final IStoreService storeService;

    @Autowired
    public StoresController(IStoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping(STORES_URL)
    public ModelAndView stores() {
        Set<StoreResponseModel> stores = this.storeService.findAll();
        return super.view(stores, Map.ofEntries(entry(PAGE_TITLE_STR, OUR_STORES_PAGE_TITLE), entry(MAP_VIEW_STR, STORES_MAP_PAGE_TITLE)));
    }
}
