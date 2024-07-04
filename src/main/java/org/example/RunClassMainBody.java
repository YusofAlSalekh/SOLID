package org.example;

import java.util.List;

public class RunClassMainBody {
    public String generateMethodBody(List<RunDataClass> dataClasses) {
        StringBuilder methodBodies = new StringBuilder();

        for (RunDataClass dataClass : dataClasses) {
            String methodName = dataClass.getMethodName();
            String descriptor = dataClass.getDescriptor();
            String filledTemplate = "        RunParameters " + methodName + " = new RunParameters();\n" +
                    "        setupJBSE(" + methodName + ", \"org/example/Verify\", \"" + descriptor + "\", \"" + methodName + "\");\n" +
                    "        runJBSE(" + methodName + ");\n\n";
            methodBodies.append(filledTemplate);
        }
        return methodBodies.toString();
    }
}
