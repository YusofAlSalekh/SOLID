package org.example;

public class Main {

    public static void main(String[] args) {
        try {
            String packageName = "org.example";
            String sourceDir = "src/main/java/org/example";
            String outputDir = "build/classes/java/main";
            Class<?> interfaceClass = Incrementation.class;

            CodeRunner codeRunner = new CodeRunner(packageName, sourceDir, outputDir, interfaceClass);
            codeRunner.run();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
