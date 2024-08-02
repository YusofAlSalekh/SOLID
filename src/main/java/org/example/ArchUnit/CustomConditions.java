package org.example.ArchUnit;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import org.example.CodeRunner;
import org.example.VerifyClassGeneration.MethodToClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomConditions {
    private static boolean codeRunnerExecuted = false;

    public static ArchCondition<JavaClass> notHaveUnsafeInTxtFiles(String packageName, String outputDir, Class<?> interfaceClass) {
        return new ArchCondition<JavaClass>("not have Unsafe > 0 in any txt file") {
            @Override
            public void check(JavaClass javaClass, ConditionEvents events) {
                String sourceDir = "src/main/java/" + javaClass.getPackageName().replace('.', '/');
                System.out.println(sourceDir);
                // Run CodeRunner
                if (!codeRunnerExecuted) {
                    CodeRunner codeRunner = new CodeRunner(packageName, sourceDir, outputDir, interfaceClass);
                    codeRunner.run();
                    codeRunnerExecuted = true;
                }

                Map<String, List<Class<?>>> methodToClassesMap = MethodToClasses.getMap();

                // Check the generated .txt files
                File dir = new File("./out");

                if (dir.isDirectory()) {
                    for (File file : dir.listFiles((d, name) -> name.endsWith(".txt"))) {
                        String fileName = file.getName();
                        String methodName = fileName.replace("_output.txt", "");

                        if (methodToClassesMap.containsKey(methodName)) {
                            List<Class<?>> involvedClasses = methodToClassesMap.get(methodName);
                            boolean violationFound = false;
                            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                                String line;
                                Pattern pattern = Pattern.compile("Unsafe:\\s*(\\d+)");
                                while ((line = reader.readLine()) != null) {
                                    Matcher matcher = pattern.matcher(line);
                                    if (matcher.find() && Integer.parseInt(matcher.group(1)) > 0) {
                                        violationFound = true;
                                        break;
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            if (violationFound) {
                                for (Class<?> clazz : involvedClasses) {
                                    if (clazz.getName().equals(javaClass.getName())) {
                                        String message = String.format("Class %s has Unsafe > 0 in txt file %s",
                                                clazz.getSimpleName(), fileName);
                                        events.add(SimpleConditionEvent.violated(javaClass, message));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        };
    }
}
