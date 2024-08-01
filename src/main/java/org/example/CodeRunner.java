package org.example;

import org.example.RunClassGeneration.RunDataClass;
import org.example.RunClassGeneration.RunFileAssembler;
import org.example.VerifyClassGeneration.VerifyClassParser;
import org.example.VerifyClassGeneration.VerifyDataClass;
import org.example.VerifyClassGeneration.VerifyFileAssembler;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CodeRunner {
    private final String packageName;
    private final String sourceDir;
    private final String outputDir;
    private final Class<?> interfaceClass;

    public CodeRunner(String packageName, String sourceDir, String outputDir, Class<?> interfaceClass) {
        this.packageName = packageName;
        this.sourceDir = sourceDir;
        this.outputDir = outputDir;
        this.interfaceClass = interfaceClass;
    }

    public void run() {
        try {
            Reflections reflections = new Reflections(packageName);

            Set<Method> interfaceMethods = getInterfaceMethods(interfaceClass);
            Set<?> implementations = reflections.getSubTypesOf(interfaceClass);

            List<VerifyDataClass> verifyDataClasses = gatherVerifyDataClasses(implementations, interfaceMethods);
            FileManager.createDirectoryIfNotExists(outputDir);

            // Generate the Verify class file
            VerifyFileAssembler.generateJavaFile(sourceDir, verifyDataClasses, interfaceClass);

            // Compile the Verify.java file using the existing Compiler class
            Compiler.compile(sourceDir + "/Verify.java", outputDir);

            // Load the Verify class dynamically
            Class<?> verifyClass = ClassLoader.loadClass(outputDir, "org.example.Verify");

            // Extract method data from the loaded Verify class
            List<RunDataClass> runDataClasses = VerifyClassParser.extractMethodData(verifyClass);

            FileManager.createDirectoryIfNotExists("./out");
            // Generate the RunVerify class based on the extracted data
            RunFileAssembler.generateRunClass(sourceDir, runDataClasses);

            // Compile the RunVerify.java file
            Compiler.compile(sourceDir + "/RunVerify.java", outputDir);

            // Run the RunVerify class
            Runner.run("org.example.RunVerify", outputDir);

            // debugging purposes
            verifyDataClasses.forEach(System.out::println);
            runDataClasses.forEach(System.out::println);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<VerifyDataClass> gatherVerifyDataClasses(Set<?> implementations, Set<Method> interfaceMethods) {
        List<VerifyDataClass> verifyDataClasses = new ArrayList<>();

        for (Object implementation : implementations) {
            Class<?> implementationClass = (Class<?>) implementation;
            Method[] methods = implementationClass.getDeclaredMethods();

            for (Method method : methods) {
                if (MethodUtils.isMethodDeclaredInInterface(method, interfaceMethods)) {
                    String methodName = method.getName();
                    Class<?> returnType = method.getReturnType();
                    verifyDataClasses.add(new VerifyDataClass(implementationClass, methodName, returnType));
                }
            }
        }
        return verifyDataClasses;
    }

    private Set<Method> getInterfaceMethods(Class<?> interfaceClass) {
        Set<Method> interfaceMethods = new HashSet<>();
        for (Method method : interfaceClass.getDeclaredMethods()) {
            interfaceMethods.add(method);
        }
        return interfaceMethods;
    }
}
