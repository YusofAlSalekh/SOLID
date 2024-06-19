package org.example.FileGeneration;

import org.example.DataClass;

import java.util.Arrays;
import java.util.List;

public class MethodBodyGenerator {

    public String generateMethodBody(List<DataClass> dataClasses) throws Exception {
        StringBuilder methodBodies = new StringBuilder();

        for (int i = 0; i < dataClasses.size(); i++) {
            for (int j = i + 1; j < dataClasses.size(); j++) {
                DataClass dataClass1 = dataClasses.get(i);
                DataClass dataClass2 = dataClasses.get(j);

                MethodBodyStrategy strategy = getStrategy(dataClass1.getReturnType());
                methodBodies.append(strategy.generateMethodBody(Arrays.asList(dataClass1, dataClass2))).append("\n");
            }
        }
        return methodBodies.toString();
    }

    private MethodBodyStrategy getStrategy(String returnType) {
        if (returnType.endsWith("[]")) {
            return new ArrayMethodBodyStrategy();
        } else {
            return new NonArrayMethodBodyStrategy();
        }
    }
}
