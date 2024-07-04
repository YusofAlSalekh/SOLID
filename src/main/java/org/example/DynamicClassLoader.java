package org.example;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class DynamicClassLoader {

    /**
     * Loads a class from a specified directory.
     *
     * @param classPath the path to the directory containing the .class file
     * @param className the fully qualified name of the class to be loaded
     * @return the loaded Class object
     * @throws Exception if any error occurs during class loading
     */
    public static Class<?> loadClass(String classPath, String className) throws Exception {
        File file = new File(classPath);
        URL url = file.toURI().toURL();
        URL[] urls = new URL[]{url};
        try (URLClassLoader classLoader = new URLClassLoader(urls)) {
            return classLoader.loadClass(className);
        }
    }
}
