package pizzaNation.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pizzaNation.admin.service.ILogService;
import pizzaNation.app.contoller.BaseController;

import static pizzaNation.admin.controller.AdminController.ADMIN_PAGE_TITLE_MAP_ENTRY;
import static pizzaNation.app.util.WebConstants.ADMIN_LOGS_ALL_URL;

@Controller
public class AdminLogController extends BaseController {

    private final ILogService logService;

    @Autowired
    public AdminLogController(ILogService logService) {
        this.logService = logService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(ADMIN_LOGS_ALL_URL)
    public ModelAndView allLogs() {
        return super.view(this.logService.findAll(), ADMIN_PAGE_TITLE_MAP_ENTRY);
    }
}
