package pizzaNation.app.util;

import java.util.Set;

public class StaticFilesContainer {

    private Set<String> cssFiles;

    private Set<String> jsFiles;

    public StaticFilesContainer(Set<String> cssFiles, Set<String> jsFiles) {
        this.cssFiles = cssFiles;
        this.jsFiles = jsFiles;
    }

    public boolean containsCssFile(String fileName) {
        return this.cssFiles.contains(fileName);
    }

    public boolean containsJsFile(String fileName) {
        return this.jsFiles.contains(fileName);
    }
}
