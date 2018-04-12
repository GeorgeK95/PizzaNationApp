package pizzaNation.app.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pizzaNation.app.contoller.BaseController;

/**
 * Created by George-Lenovo on 12/04/2018.
 */
@Controller
public class TestController extends BaseController {

    @GetMapping("/table")
    public ModelAndView table() {
        return super.view();
    }
}
