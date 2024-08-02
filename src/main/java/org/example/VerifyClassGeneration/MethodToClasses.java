package org.example.VerifyClassGeneration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodToClasses {
    private static final Map<String, List<Class<?>>> methodToClassesMap = new HashMap<>();

    public static void put(String methodName, List<Class<?>> classes) {
        methodToClassesMap.put(methodName, classes);
    }

    public static Map<String, List<Class<?>>> getMap() {
        return methodToClassesMap;
    }
}
