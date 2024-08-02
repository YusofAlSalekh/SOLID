package org.example.VerifyClassGeneration;



import org.example.RunClassGeneration.RunDataClass;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class VerifyClassParser {
    public static List<RunDataClass> extractMethodData(Class<?> clazz) {
        List<RunDataClass> methodData = new ArrayList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getName().startsWith("verify")) {
                String methodName = method.getName();
                String returnType = method.getReturnType().getSimpleName();
                String descriptor = getMethodDescriptor(method);
                methodData.add(new RunDataClass(clazz.getSimpleName(), methodName, returnType, descriptor));
            }
        }
        return methodData;
    }

    private static String getMethodDescriptor(Method method) {
        StringBuilder descriptor = new StringBuilder();
        descriptor.append("(");
        for (Class<?> paramType : method.getParameterTypes()) {
            descriptor.append(getTypeDescriptor(paramType));
        }
        descriptor.append(")");
        descriptor.append(getTypeDescriptor(method.getReturnType()));
        return descriptor.toString();
    }

    private static String getTypeDescriptor(Class<?> clazz) {
        if (clazz.isPrimitive()) {
            if (clazz == void.class) {
                return "V";
            } else if (clazz == int.class) {
                return "I";
            } else if (clazz == boolean.class) {
                return "Z";
            } else if (clazz == byte.class) {
                return "B";
            } else if (clazz == char.class) {
                return "C";
            } else if (clazz == short.class) {
                return "S";
            } else if (clazz == long.class) {
                return "J";
            } else if (clazz == float.class) {
                return "F";
            } else if (clazz == double.class) {
                return "D";
            }
        } else if (clazz.isArray()) {
            return "[" + getTypeDescriptor(clazz.getComponentType());
        } else {
            return "L" + clazz.getName().replace('.', '/') + ";";
        }
        throw new IllegalArgumentException("Unknown type: " + clazz);
    }
}