package org.example.VerifyClassGeneration;

import org.example.StringUtils;

import java.util.List;

public class FieldsGenerator {

    public String generateFields(List<VerifyDataClass> verifyDataClasses, Class<?> interfaceClass) throws Exception {
        String template = "    private static final $Type$ var = new $ClassType$();\n";
        StringBuilder fields = new StringBuilder();
        for (int i = 0; i < verifyDataClasses.size(); i++) {
            VerifyDataClass verifyDataClass = verifyDataClasses.get(i);
            String className = verifyDataClass.getimplementationClass().getSimpleName();
            String type = interfaceClass.getSimpleName();
            String filledTemplate = template
                    .replace("$Type$", type)
                    .replace("var", StringUtils.toLowerCaseFirstLetter(className))
                    .replace("$ClassType$",className);
            fields.append(filledTemplate);
        }
        return fields.toString();
    }
}
