package org.example;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

public class Runner {

    public static void run(String className, String classPath) throws IOException, ClassNotFoundException, ReflectiveOperationException {
        // Create a file object for the class path
        File parentDir = new File(classPath);

        // Check if the class file exists
        File classFile = new File(classPath + "/" + className.replace('.', '/') + ".class");
        if (!classFile.exists()) {
            throw new RuntimeException("Class file " + classFile.getPath() + " not found.");
        }

        // Load and instantiate the compiled class
        try (URLClassLoader classLoader = new URLClassLoader(new URL[]{parentDir.toURI().toURL()})) {
            Class<?> cls = Class.forName(className, true, classLoader);
            cls.getDeclaredMethod("main", String[].class).invoke(null, (Object) new String[]{});
        }
    }
}
