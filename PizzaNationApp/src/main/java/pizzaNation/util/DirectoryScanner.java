package pizzaNation.util;

import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static pizzaNation.util.WebConstants.CSS_EXTENSION;
import static pizzaNation.util.WebConstants.EMPTY_STR;
import static pizzaNation.util.WebConstants.JS_EXTENSION;

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
                filesNames.add(fileEntry.getName()
                        .replace(CSS_EXTENSION, EMPTY_STR)
                        .replace(JS_EXTENSION, EMPTY_STR)
                );
            }
        }

        return filesNames;
    }

    private DirectoryScanner() {
    }
}