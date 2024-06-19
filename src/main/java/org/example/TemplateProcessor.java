package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static org.example.FileGeneration.StringUtils.toLowerCaseFirstLetter;

public class TemplateProcessor {

    public static void main(String[] args) {
        String templatePath = "C:\\Users\\nogga\\IdeaProjects\\example\\src\\main\\java\\org\\example\\VerifyInputParamTemplate";
        System.out.println("Reading templateTwoMethods from: " + templatePath);

        try {
            if (!Files.exists(Paths.get(templatePath))) {
                //add exception
                System.err.println("File not found: " + templatePath);
                return;
            }

            String templateContent = new String(Files.readAllBytes(Paths.get(templatePath)));
            Map<String, String> replacements = new HashMap<>();
            Class<?>[] classes = {IncrementationA.class, IncrementationB.class, IncrementationC.class};
            StringBuilder instances = new StringBuilder();
            for (Class<?> cls : classes) {
                String simpleName = cls.getSimpleName();
                instances.append(simpleName + " " + toLowerCaseFirstLetter(simpleName) + " = new " + simpleName + "();\n    ");
                for (Method method : cls.getDeclaredMethods()) {
                    if (method.getReturnType().equals(Param.class) && method.getParameterTypes().length == 1 && method.getParameterTypes()[0].equals(Param.class)) {
                        replacements.put("{{method" + simpleName + "}}", method.getName());
                    }
                }
            }
            replacements.put("{{Instances}}", instances.toString().trim());
            String filledTemplate = fillTemplate(templateContent, replacements);
            Files.write(Paths.get("GeneratedVerifyInputParam.java"), filledTemplate.getBytes());
            System.out.println("Java class generated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String fillTemplate(String template, Map<String, String> placeholders) {
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            template = template.replace(entry.getKey(), entry.getValue());
        }
        return template;
    }
}
