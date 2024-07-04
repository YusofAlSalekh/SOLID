package org.example;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

public class RuntimeCompiler {
    public static void main(String[] args) throws Exception {
        // Specify the path to the Java source file
        String sourceFilePath = "src/main/java/org/example/RunVerify.java";

        // Compile and run the source file
        compileAndRun(sourceFilePath);
    }

    public static void compileAndRun(String sourceFilePath) throws IOException, ClassNotFoundException, ReflectiveOperationException {
        // Create a file object for the source file
        File sourceFile = new File(sourceFilePath);

        // Compile the source file
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, null, null, sourceFile.getPath());
        if (result != 0) {
            throw new RuntimeException("Compilation failed.");
        }

        // Get the parent directory of the compiled classes
        File parentDir = sourceFile.getParentFile().getParentFile().getParentFile();

        // Load and instantiate the compiled class
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{parentDir.toURI().toURL()});
        Class<?> cls = Class.forName("org.example.RunVerify", true, classLoader);
        cls.getDeclaredMethod("main", String[].class).invoke(null, (Object) new String[]{});
    }
}
