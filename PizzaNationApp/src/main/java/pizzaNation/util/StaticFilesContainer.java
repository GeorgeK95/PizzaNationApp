package pizzaNation.util;

import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by George-Lenovo on 23/03/2018.
 */
public class StaticFilesContainer {

    private Set<String> cssFiles;

    private Set<String> jsFiles;

    public boolean containsCssFile(String fileName) {
        return this.cssFiles.contains(fileName);
    }

    public boolean containsJsFile(String fileName) {
        return this.jsFiles.contains(fileName);
    }

    public void setCssFiles(Set<String> cssFiles) {
        this.cssFiles = cssFiles;
    }

    public void setJsFiles(Set<String> jsFiles) {
        this.jsFiles = jsFiles;
    }
}
