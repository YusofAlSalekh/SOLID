package org.example.FileGeneration;

import org.example.DataClass;

import java.util.List;

public class NonArrayMethodBodyStrategy implements MethodBodyStrategy {
    @Override
    public String generateMethodBody(List<DataClass> dataClasses) throws Exception {
        String methodTemplate = "    public void verify$Num$($InputType$ input) {\n" +
                "        $ReturnType$ a = var$Num1$.$method$(input));\n" +
                "        $ReturnType$ b = var$Num2$.$method$(input));\n" +
                "        ass3rt(a.equals(b));\n" +
                "    }\n";

        StringBuilder methodBodies = new StringBuilder();


        for (int i = 0; i < dataClasses.size(); i++) {
            for (int j = i + 1; j < dataClasses.size(); j++) {
                DataClass dataClass1 = dataClasses.get(i);
                DataClass dataClass2 = dataClasses.get(j);

                String filledTemplate = methodTemplate
                        .replace("$Num$", String.valueOf(MethodCounter.getNext()))
                        .replace("$InputType$", dataClass1.getReturnType())
                        .replace("$ReturnType$", dataClass1.getReturnType())
                        .replace("var$Num1$", StringUtils.toLowerCaseFirstLetter(dataClass1.getClassName()))
                        .replace("var$Num2$", StringUtils.toLowerCaseFirstLetter(dataClass2.getClassName()))
                        .replace("$method$", dataClass1.getMethodName());

                methodBodies.append(filledTemplate);
            }
        }
        return methodBodies.toString();
    }
}
