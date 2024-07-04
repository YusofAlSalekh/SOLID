package org.example;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import javax.tools.StandardJavaFileManager;
import javax.tools.JavaFileObject;
import java.io.File;
import java.util.Arrays;

public class Compiler {

    public static void compile(String sourceFilePath, String outputDir) throws RuntimeException {
        // Create a file object for the source file
        File sourceFile = new File(sourceFilePath);

        // Compile the source file
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(sourceFile));
        Iterable<String> options = Arrays.asList("-d", outputDir);

        int result = compiler.getTask(null, fileManager, null, options, null, compilationUnits).call() ? 0 : 1;
        if (result != 0) {
            throw new RuntimeException("Compilation failed.");
        }
    }
}
