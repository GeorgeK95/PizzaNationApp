package pizzaNation.admin.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pizzaNation.app.contoller.BaseController;

import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.*;

/**
 * Created by George-Lenovo on 25/03/2018.
 */
@Controller
@RequestMapping(ADMIN_URL)
public class AdminController extends BaseController {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(SLASH_URL)
    public ModelAndView admin() {
        return super.view(null, Map.ofEntries(entry(PAGE_TITLE_STR, ADMIN_PANEL_PAGE_TITLE)));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/stores/all")
    public ModelAndView stores() {
        return super.view(null, Map.ofEntries(entry(PAGE_TITLE_STR, ADMIN_PANEL_PAGE_TITLE)));
    }

    /*@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/users/all")
    public ModelAndView users() {
        return super.view(null, Map.ofEntries(entry(PAGE_TITLE_STR, ADMIN_PANEL_PAGE_TITLE)));
    }*/
}
