package pizzaNation.app.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pizzaNation.admin.service.IMenuService;
import pizzaNation.app.test.UploadImageRequestModel;

import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.PAGE_TITLE_STR;
import static pizzaNation.app.util.WebConstants.TERMS_AND_CONDITIONS_PAGE_TITLE;

/**
 * Created by George-Lenovo on 10/04/2018.
 */
@Controller
public class UploadController extends BaseController {

    private final IMenuService menuService;

    @Autowired
    public UploadController(IMenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/upload")
    public ModelAndView upload(UploadImageRequestModel requestModel) {
        return super.view(requestModel, Map.ofEntries(entry(PAGE_TITLE_STR, TERMS_AND_CONDITIONS_PAGE_TITLE)));
    }

    @PostMapping("/upload")
    public ModelAndView uploadProcess(@ModelAttribute UploadImageRequestModel requestModel) {
        this.menuService.uploadImage(requestModel);
        return super.redirect("/");
    }
}
