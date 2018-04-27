package pizzaNation.app.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import pizzaNation.app.util.StaticFilesContainer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pizzaNation.app.util.WebConstants.*;


/**
 * Created by George-Lenovo on 15/03/2018.
 */
public abstract class BaseController {

    @Autowired
    private StaticFilesContainer staticFilesContainer;

    protected ModelAndView view(Object viewModel) {
        StackTraceElement[] stackTraceElements = java.lang.Thread.currentThread().getStackTrace();
        StackTraceElement callee = stackTraceElements[3];
        String[] names = callee.getClassName().split("\\.");
        String folder = names[names.length - 1].replace(CONTROLLER_STR, "")/*.toLowerCase()*/;
        String file = callee.getMethodName();

        //split po glavni bukvi
        String[] r = folder.split(UPPERCASE_SPLIT_PATTERN);
        StringBuilder sb = new StringBuilder();
        Arrays.stream(r).forEach(cc -> sb.append(cc).append(SLASH_STR));

        return this.view(sb.toString() /*+ SLASH_STR */ + file, viewModel);
    }

    protected ModelAndView view(Object viewModel, Map<String, Object> attributes) {
        ModelAndView modelAndView = this.view(viewModel);
        attributes.forEach((key, value) -> modelAndView.getModelMap().addAttribute(key, value));
        return modelAndView;
    }

    protected ModelAndView view(Object viewModel, Map<String, Object> attributes, int statusCode) {
        ModelAndView modelAndView = this.view(viewModel);
        attributes.forEach((key, value) -> modelAndView.getModelMap().addAttribute(key, value));
        modelAndView.addObject(STATUS_CODE_STR, statusCode);
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

    protected ModelAndView redirectAndLog(String redirectUrl) {
        ModelAndView modelAndViewWithLogAttribute = new ModelAndView(REDIRECT_STR.concat(redirectUrl));
        modelAndViewWithLogAttribute.addObject(LOGGER_MESSAGE, true);
        return modelAndViewWithLogAttribute;
    }

    private void addAttributes(String viewName, ModelAndView modelAndView) {
        String viewSimpleName = viewName.split(SLASH_STR)[0].toLowerCase();

        if (this.staticFilesContainer.containsCssFile(viewSimpleName))
            modelAndView.getModelMap().addAttribute(PAGE_STYLE_STR, List.of(CSS_PAGE_DIR + viewSimpleName + CSS_EXTENSION));
        if (this.staticFilesContainer.containsJsFile(viewSimpleName))
            modelAndView.getModelMap().addAttribute(PAGE_SCRIPT_STR, List.of(JS_PAGE_DIR + viewSimpleName + JS_EXTENSION));

        modelAndView.getModelMap().addAttribute(VIEW_STR, viewName);
    }

    protected String escapeHTMLEncoding(String queryString) {
        for (String current : HTML_SYMBOLS_TO_ESCAPE) {
            queryString = queryString.replace(current, SPACE_STR);
        }
        return queryString;
    }
}
