package pizzaNation.admin.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.*;
import static pizzaNation.app.util.WebConstants.ADD_STORES_URL;

/**
 * Created by George-Lenovo on 02/04/2018.
 */
@Controller
@RequestMapping(ADMIN_URL)
public class AdminStoreController {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = ADD_STORES_URL, method = RequestMethod.POST)
    public @ResponseBody
    String addStoresProcess(@RequestParam Double lat, @RequestParam Double lng) throws Exception {
//        throw new Exception("Pderasdsad");
        return "Saved.";
    }
}
