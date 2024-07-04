package org.example;

import java.io.File;

public class FileManager {
    public static void createDirectoryIfNotExists(String dirPath) {
        File outputDirectory = new File(dirPath);
        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }
    }
}
