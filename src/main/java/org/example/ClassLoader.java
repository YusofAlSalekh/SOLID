package org.example;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassLoader {
    public static Class<?> loadClass(String outputDir, String className) throws Exception {
        File classesDir = new File(outputDir);
        URLClassLoader classLoader = new URLClassLoader(new URL[]{classesDir.toURI().toURL()});
        return classLoader.loadClass(className);
    }
}
