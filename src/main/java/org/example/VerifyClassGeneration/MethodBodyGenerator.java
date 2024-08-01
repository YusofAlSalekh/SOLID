package org.example.VerifyClassGeneration;

import java.util.Arrays;
import java.util.List;

public class MethodBodyGenerator {

    public String generateMethodBody(List<VerifyDataClass> verifyDataClasses) throws Exception {
        StringBuilder methodBodies = new StringBuilder();

        for (int i = 0; i < verifyDataClasses.size(); i++) {
            for (int j = i + 1; j < verifyDataClasses.size(); j++) {
                VerifyDataClass verifyDataClass1 = verifyDataClasses.get(i);
                VerifyDataClass verifyDataClass2 = verifyDataClasses.get(j);

                MethodBodyStrategy strategy = getStrategy(verifyDataClass1.getReturnType());
                methodBodies.append(strategy.generateMethodBody(Arrays.asList(verifyDataClass1, verifyDataClass2))).append("\n");
            }
        }
        return methodBodies.toString();
    }

    private MethodBodyStrategy getStrategy(Class<?> returnType) {
        if (returnType.isArray()) {
            return new ArrayMethodBodyStrategy();
        } else {
            return new NonArrayMethodBodyStrategy();
        }
    }
}
