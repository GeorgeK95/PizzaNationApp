package pizzaNation.util;

import java.io.File;
import java.util.*;

import static pizzaNation.util.WebConstants.CSS_PAGE_FILES;
import static pizzaNation.util.WebConstants.JS_PAGE_FILES;

/**
 * Created by George-Lenovo on 23/03/2018.
 */
public final class DirectoryScanner {

    public static Set<String> getDirectoryFilesNames(String path) {
        return DirectoryScanner.listFilesForFolder(new File(path), new HashSet<>());
    }

    private static Set<String> listFilesForFolder(File folder, Set<String> filesNames) {
        for (File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry, filesNames);
            } else {
                filesNames.add(fileEntry.getName());
            }
        }

        return filesNames;
    }

    private DirectoryScanner() {
    }
}
