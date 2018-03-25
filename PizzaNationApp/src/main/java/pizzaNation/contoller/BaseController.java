package pizzaNation.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import pizzaNation.model.PeshoModel;

import java.util.List;
import java.util.Map;

import static pizzaNation.util.WebConstants.*;


/**
 * Created by George-Lenovo on 15/03/2018.
 */
@Component
public abstract class BaseController {

//    private StaticFilesContainer staticFilesContainer;

    private PeshoModel peshoModel;

    public BaseController() {
    }

    @Autowired
    public BaseController(PeshoModel peshoModel) {
        this.peshoModel = peshoModel;
    }

//    @Autowired
//    public BaseController(StaticFilesContainer staticFilesContainer) {
//        this.staticFilesContainer = staticFilesContainer;
//    }

    private static final String CSS_PAGE_DIR = "/css/page/";
    private static final String JS_PAGE_DIR = "/js/page/";

    protected ModelAndView view() {
        return this.view(null);
    }

    protected ModelAndView view(Object viewModel) {
        StackTraceElement[] stackTraceElements = java.lang.Thread.currentThread().getStackTrace();
        StackTraceElement callee = stackTraceElements[3];
        String[] names = callee.getClassName().split("\\.");
        String folder = names[names.length - 1].replace(CONTROLLER_STR, "").toLowerCase();
        String file = callee.getMethodName();

        return this.view(folder + SLASH_STR + file, viewModel);
    }

    protected ModelAndView view(Object viewModel, Map<String, Object> attributes) {
        ModelAndView modelAndView = this.view(viewModel);
        attributes.forEach((key, value) -> modelAndView.getModelMap().addAttribute(key, value));
        return modelAndView;
    }

    private ModelAndView view(String viewName, Object viewModel) {
        ModelAndView modelAndView = new ModelAndView(BASE_LAYOUT_URL);
        this.addAttributes(viewName, modelAndView);
        if (viewModel != null) modelAndView.getModelMap().addAttribute(MODEL_STR, viewModel);

        return modelAndView;
    }

    private void addAttributes(String viewName, ModelAndView modelAndView) {
        String viewSimpleName = viewName.split(SLASH_STR)[0];
//        if (this.staticFilesContainer.containsCssFile(viewSimpleName))
        modelAndView.getModelMap().addAttribute(PAGE_STYLE_STR,
                List.of(CSS_PAGE_DIR + viewSimpleName + CSS_EXTENSION));
//        if (this.staticFilesContainer.containsJsFile(viewSimpleName))
        modelAndView.getModelMap().addAttribute(PAGE_SCRIPT_STR,
                List.of(JS_PAGE_DIR + viewSimpleName + JS_EXTENSION));
        modelAndView.getModelMap().addAttribute(VIEW_STR, viewName);
    }
}
