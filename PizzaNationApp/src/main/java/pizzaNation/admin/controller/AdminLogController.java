package pizzaNation.admin.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pizzaNation.app.contoller.BaseController;

import static pizzaNation.admin.controller.AdminController.ADMIN_PAGE_TITLE_MAP_ENTRY;
import static pizzaNation.app.util.WebConstants.ADMIN_LOGS_ALL_URL;

/**
 * Created by George-Lenovo on 17/04/2018.
 */
@Controller
public class AdminLogController extends BaseController {

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(ADMIN_LOGS_ALL_URL)
    public ModelAndView allLogs() {
        return super.view(null, ADMIN_PAGE_TITLE_MAP_ENTRY);
    }
}
