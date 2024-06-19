package org.example.FileGeneration;

import java.util.List;
import org.example.DataClass;

public class FieldsGenerator {

    public String generateFields(List<DataClass> dataClasses, Class<?> interfaceClass) throws Exception {
        String template = "    private static final $Type$ var = new $Type$();\n";
        StringBuilder fields = new StringBuilder();
        for (int i = 0; i < dataClasses.size(); i++) {
            DataClass dataClass = dataClasses.get(i);
            String className = dataClass.getClassName();
            String filledTemplate = template
                    .replace("$Type$", className)
                    .replace("var", StringUtils.toLowerCaseFirstLetter(className));
            fields.append(filledTemplate);
        }
        return fields.toString();
    }
}
