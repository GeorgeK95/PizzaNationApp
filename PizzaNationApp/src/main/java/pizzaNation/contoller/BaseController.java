package pizzaNation.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import pizzaNation.util.StaticFilesContainer;

import java.util.List;
import java.util.Map;

import static pizzaNation.util.WebConstants.*;


/**
 * Created by George-Lenovo on 15/03/2018.
 */
public abstract class BaseController {

    @Autowired
    private StaticFilesContainer staticFilesContainer;

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

    protected ModelAndView redirect(String redirectUrl) {
        return new ModelAndView(REDIRECT_STR.concat(redirectUrl));
    }

    private void addAttributes(String viewName, ModelAndView modelAndView) {
        String viewSimpleName = viewName.split(SLASH_STR)[0];

        if (this.staticFilesContainer.containsCssFile(viewSimpleName))
            modelAndView.getModelMap().addAttribute(PAGE_STYLE_STR, List.of(CSS_PAGE_DIR + viewSimpleName + CSS_EXTENSION));
        if (this.staticFilesContainer.containsJsFile(viewSimpleName))
            modelAndView.getModelMap().addAttribute(PAGE_SCRIPT_STR, List.of(JS_PAGE_DIR + viewSimpleName + JS_EXTENSION));

        modelAndView.getModelMap().addAttribute(VIEW_STR, viewName);
    }
}
