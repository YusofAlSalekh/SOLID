package org.example;

import java.util.List;

public class ArrayMethodBodyStrategy implements MethodBodyStrategy {
    @Override
    public String generateMethodBody(List<VerifyDataClass> verifyDataClasses) throws Exception {
        String methodTemplate = "    public void verify$Num$($InputType$ input) {\n" +
                "        assume(input.length <= 5);\n\n" +
                "        $ReturnType$ a = var$Num1$.$method$(Arrays.copyOf(input, input.length));\n" +
                "        $ReturnType$ b = var$Num2$.$method$(Arrays.copyOf(input, input.length));\n\n" +
                "        ass3rt(Arrays.equals(a, b));\n" +
                "    }\n";

        StringBuilder methodBodies = new StringBuilder();

        for (int i = 0; i < verifyDataClasses.size(); i++) {
            for (int j = i + 1; j < verifyDataClasses.size(); j++) {
                VerifyDataClass verifyDataClass1 = verifyDataClasses.get(i);
                VerifyDataClass verifyDataClass2 = verifyDataClasses.get(j);

                String filledTemplate = methodTemplate
                        .replace("$Num$", String.valueOf(MethodCounter.getNext()))
                        .replace("$InputType$", verifyDataClass1.getReturnType())
                        .replace("$ReturnType$", verifyDataClass1.getReturnType())
                        .replace("var$Num1$", StringUtils.toLowerCaseFirstLetter(verifyDataClass1.getClassName()))
                        .replace("var$Num2$", StringUtils.toLowerCaseFirstLetter(verifyDataClass2.getClassName()))
                        .replace("$method$", verifyDataClass1.getMethodName());

                methodBodies.append(filledTemplate);
            }
        }
        return methodBodies.toString();
    }
}