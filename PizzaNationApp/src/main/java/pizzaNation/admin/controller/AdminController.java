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

@Controller
@RequestMapping(ADMIN_URL)
public class AdminController extends BaseController {
    static final Map<String, Object> ADMIN_PAGE_TITLE_MAP_ENTRY = Map.ofEntries(entry(PAGE_TITLE_STR, ADMIN_PANEL_PAGE_TITLE));

    @PreAuthorize(HAS_ANY_ROLE_ROLE_ADMIN_ROLE_MODERATOR)
    @GetMapping(EMPTY_URL)
    public ModelAndView admin() {
        return super.view(null, ADMIN_PAGE_TITLE_MAP_ENTRY);
    }


}
