package pizzaNation.admin.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pizzaNation.app.annotation.LoggerAction;
import pizzaNation.app.enums.Action;
import pizzaNation.app.enums.TableEnum;
import pizzaNation.app.model.response.StoreResponseModel;
import pizzaNation.app.service.contract.IStoreService;

import java.util.Set;

import static pizzaNation.app.util.WebConstants.*;
import static pizzaNation.app.util.WebConstants.ADD_STORES_URL;

/**
 * Created by George-Lenovo on 02/04/2018.
 */
@Controller
@RequestMapping(ADMIN_URL)
public class AdminStoreController {

    private final IStoreService storeService;

    @Autowired
    public AdminStoreController(IStoreService storeService) {
        this.storeService = storeService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = ADD_STORES_URL, method = RequestMethod.POST)
    @LoggerAction(table = TableEnum.STORE, action = Action.ADD)
    public @ResponseBody
    String addStoresProcess(@RequestParam Double lat, @RequestParam Double lng) throws Exception {
        this.storeService.persist(lat, lng);
        return "Saved.";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = DELETE_STORES_URL, method = RequestMethod.POST)
    @LoggerAction(table = TableEnum.STORE, action = Action.DELETE)
    public @ResponseBody
    String deleteStoresProcess(@RequestParam Double lat, @RequestParam Double lng) throws Exception {
        this.storeService.delete(lat, lng);
        return "Deleted.";
    }

}
