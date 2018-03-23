package pizzaNation.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by George-Lenovo on 15/03/2018.
 */
@Controller
public abstract class BaseController {

//    private StaticFilesContainer staticFilesContainer;

    public BaseController() {
    }

//    @Autowired
//    public BaseController(StaticFilesContainer staticFilesContainer) {
//        this.staticFilesContainer = staticFilesContainer;
//    }

    private static final String CSS_PAGE_DIR = "/css/page/";
    private static final String JS_PAGE_DIR = "/js/page/";

    protected ModelAndView view(Object viewModel, Map<String, Object> attributes) {
        ModelAndView modelAndView = this.view(viewModel);
        attributes.forEach((key, value) -> modelAndView.getModelMap().addAttribute(key, value));
        return modelAndView;
    }

    protected ModelAndView view(Object viewModel) {
        StackTraceElement[] stackTraceElements = java.lang.Thread.currentThread().getStackTrace();
        StackTraceElement callee = stackTraceElements[3];
        String[] names = callee.getClassName().split("\\.");
        String folder = names[names.length - 1].replace("Controller", "").toLowerCase();
        String file = callee.getMethodName();

        return this.view(folder + "/" + file, viewModel);
    }

    private ModelAndView view(String viewName, Object viewModel) {
        ModelAndView modelAndView = new ModelAndView("base-layout");
        this.addAttributes(viewName, modelAndView);
        if (viewModel != null) modelAndView.getModelMap().addAttribute("model", viewModel);

        return modelAndView;
    }

    private void addAttributes(String viewName, ModelAndView modelAndView) {
        String viewSimpleName = viewName.split("/")[0];
//        if (this.staticFilesContainer.containsCssFile(viewSimpleName))
            modelAndView.getModelMap().addAttribute("pageStyle",
                    List.of(CSS_PAGE_DIR + viewSimpleName + ".css"));
//        if (this.staticFilesContainer.containsJsFile(viewSimpleName))
            modelAndView.getModelMap().addAttribute("pageScript",
                    List.of(JS_PAGE_DIR + viewSimpleName + ".js"));
        modelAndView.getModelMap().addAttribute("view", viewName);
    }
}
