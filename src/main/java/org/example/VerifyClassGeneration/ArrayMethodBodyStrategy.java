package org.example.VerifyClassGeneration;

import org.example.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrayMethodBodyStrategy implements MethodBodyStrategy {
    @Override
    public String generateMethodBody(List<VerifyDataClass> verifyDataClasses) throws Exception {
        String methodTemplate = "    public void verify$Num$($InputType$ input) {\n" +
                "        assume(input.length <= 4);\n\n" +
                "        $ReturnType$ a = var$Num1$.$method$(Arrays.copyOf(input, input.length));\n" +
                "        $ReturnType$ b = var$Num2$.$method$(Arrays.copyOf(input, input.length));\n\n" +
                "        ass3rt(Arrays.equals(a, b));\n" +
                "    }\n";

        StringBuilder methodBodies = new StringBuilder();

        for (int i = 0; i < verifyDataClasses.size(); i++) {
            for (int j = i + 1; j < verifyDataClasses.size(); j++) {
                VerifyDataClass verifyDataClass1 = verifyDataClasses.get(i);
                VerifyDataClass verifyDataClass2 = verifyDataClasses.get(j);

                String methodName = "verify" + MethodCounter.getNext();

                String filledTemplate = methodTemplate
                        .replace("verify$Num$", methodName)
                        .replace("$InputType$", verifyDataClass1.getReturnType().getSimpleName())
                        .replace("$ReturnType$", verifyDataClass1.getReturnType().getSimpleName())
                        .replace("var$Num1$", StringUtils.toLowerCaseFirstLetter(verifyDataClass1.getimplementationClass().getSimpleName()))
                        .replace("var$Num2$", StringUtils.toLowerCaseFirstLetter(verifyDataClass2.getimplementationClass().getSimpleName()))
                        .replace("$method$", verifyDataClass1.getMethodName());

                methodBodies.append(filledTemplate);

                MethodToClasses.put(methodName, Arrays.asList(verifyDataClass1.getimplementationClass(), verifyDataClass2.getimplementationClass()));

            }
        }
        return methodBodies.toString();
    }
}