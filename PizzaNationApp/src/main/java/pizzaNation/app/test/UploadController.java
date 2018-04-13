/*
package pizzaNation.app.table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pizzaNation.app.contoller.BaseController;
import pizzaNation.app.service.contract.IImageService;

import java.util.Map;

import static java.util.Map.entry;
import static pizzaNation.app.util.WebConstants.PAGE_TITLE_STR;
import static pizzaNation.app.util.WebConstants.TERMS_AND_CONDITIONS_PAGE_TITLE;

*/
/**
 * Created by George-Lenovo on 10/04/2018.
 *//*

@Controller
public class UploadController extends BaseController {

    private final IImageService imageService;

    @Autowired
    public UploadController(IImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/upload")
    public ModelAndView upload(UploadImageRequestModel requestModel) {
        return super.view(requestModel, Map.ofEntries(entry(PAGE_TITLE_STR, TERMS_AND_CONDITIONS_PAGE_TITLE)));
    }

    @PostMapping("/upload")
    public ModelAndView uploadProcess(@ModelAttribute UploadImageRequestModel requestModel) {
        this.imageService.uploadImage(requestModel);
        return super.redirect("/");
    }

}
*/
