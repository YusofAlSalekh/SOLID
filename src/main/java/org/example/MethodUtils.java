package org.example;

import java.lang.reflect.Method;
import java.util.Set;

public class MethodUtils {
    public static boolean isMethodDeclaredInInterface(Method method, Set<Method> interfaceMethods) {
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
