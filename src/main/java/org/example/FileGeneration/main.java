package org.example.FileGeneration;

import org.example.DataClass;
import org.example.Incrementation;
import org.example.JavaFileAssembler;
import org.reflections.Reflections;

import java.io.File;
import java.lang.reflect.Method;
import java.util.*;

public class main {

    public static void main(String[] args) {
        String packageName = "org.example";
        String outputDir = "./generated"; // Specify the output directory

        Reflections reflections = new Reflections(packageName);

        Class<?> interfaceClass = Incrementation.class;

        Set<Method> interfaceMethods = new HashSet<>();
        for (Method method : interfaceClass.getDeclaredMethods()) {
            interfaceMethods.add(method);
        }

        Set<?> implementations = reflections.getSubTypesOf(interfaceClass);

        List<DataClass> dataClasses = new ArrayList<>();

        for (Object implementation : implementations) {
            Class<?> implementationClass = (Class<?>) implementation;
            String className = implementationClass.getSimpleName();
            Method[] methods = implementationClass.getDeclaredMethods();

            for (Method method : methods) {
                if (isMethodDeclaredInInterface(method, interfaceMethods)) {
                    String methodName = method.getName();
                    String returnType = method.getReturnType().getSimpleName();
                    dataClasses.add(new DataClass(className, methodName, returnType));
                }
            }
        }

        try {
            // Ensure the output directory exists
            File outputDirectory = new File(outputDir);
            if (!outputDirectory.exists()) {
                outputDirectory.mkdirs();
            }

            JavaFileAssembler assembler = new JavaFileAssembler();
            assembler.generateJavaFile(outputDir, dataClasses, interfaceClass);
        } catch (Exception e) {
            e.printStackTrace();
        }

        dataClasses.forEach(System.out::println);
    }

    private static boolean isMethodDeclaredInInterface(Method method, Set<Method> interfaceMethods) {
        for (Method interfaceMethod : interfaceMethods) {
            if (method.getName().equals(interfaceMethod.getName()) &&
                    method.getReturnType().equals(interfaceMethod.getReturnType()) &&
                    parametersMatch(method, interfaceMethod)) {
                return true;
            }
        }
        return false;
    }

    private static boolean parametersMatch(Method method, Method interfaceMethod) {
        Class<?>[] methodParams = method.getParameterTypes();
        Class<?>[] interfaceMethodParams = interfaceMethod.getParameterTypes();
        if (methodParams.length != interfaceMethodParams.length) {
            return false;
        }
        for (int i = 0; i < methodParams.length; i++) {
            if (!methodParams[i].equals(interfaceMethodParams[i])) {
                return false;
            }
        }
        return true;
    }
}
