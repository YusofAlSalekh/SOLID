package org.example;

public class RunDataClass {
    private String className;
    private String methodName;
    private String returnType;
    private String descriptor;

    public RunDataClass(String className, String methodName, String returnType, String descriptor) {
        this.className = className;
        this.methodName = methodName;
        this.returnType = returnType;
        this.descriptor = descriptor;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getReturnType() {
        return returnType;
    }

    public String getDescriptor() {
        return descriptor;
    }

    @Override
    public String toString() {
        return "Class: " + className + ", Method: " + methodName + ", Return Type: " + returnType + ", Descriptor: " + descriptor;
    }
}
