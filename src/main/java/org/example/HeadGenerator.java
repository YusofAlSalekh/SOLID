package org.example;

import java.util.List;

public class HeadGenerator {
    public String generateHead(List<VerifyDataClass> verifyDataClasses) {
        StringBuilder head = new StringBuilder();

        HeadStrategy strategy = getStrategy(verifyDataClasses.get(0).getReturnType());
        head.append(strategy.generateHead()).append("\n");

        return head.toString();
    }

    private HeadStrategy getStrategy(String returnType) {
        if (returnType.endsWith("[]")) {
            return new ArrayHeadStrategy();
        } else {
            return new NonArrayHeadStrategy();
        }
    }
}
