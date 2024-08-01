package org.example.VerifyClassGeneration;

public class VerifyDataClass {
    private Class<?> implementationClass;
    private String methodName;
    private Class<?> returnType;

    public VerifyDataClass(Class<?> implementationClass, String methodName, Class<?>  returnType) {
        this.implementationClass = implementationClass;
        this.methodName = methodName;
        this.returnType = returnType;
    }

    public Class<?> getimplementationClass() {
        return implementationClass;
    }

    public String getMethodName() {
        return methodName;
    }

    public Class<?>  getReturnType() {
        return returnType;
    }

    @Override
    public String toString() {
        return "Class: " + implementationClass + ", Method: " + methodName + ", Return Type: " + returnType;
    }
}
