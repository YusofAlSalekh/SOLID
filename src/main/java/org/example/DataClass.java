package org.example;

public class DataClass {
    private String className;
    private String methodName;
    private String returnType;

    public DataClass(String className, String methodName, String returnType) {
        this.className = className;
        this.methodName = methodName;
        this.returnType = returnType;
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

    @Override
    public String toString() {
        return "Class: " + className + ", Method: " + methodName + ", Return Type: " + returnType;
    }
}
