package org.example.VerifyClassGeneration;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class VerifyFileAssembler {
    private static final HeadGenerator headGenerator = new HeadGenerator();
    private static final FieldsGenerator fieldsGenerator = new FieldsGenerator();
    private static final MethodBodyGenerator methodBodyGenerator = new MethodBodyGenerator();
    private static final VerifyTailGenerator VERIFY_TAIL_GENERATOR = new VerifyTailGenerator();

    public static void generateJavaFile(String outputDir, List<VerifyDataClass> verifyDataClasses, Class<?> interfaceClass) throws Exception {
        String template = "{HEAD}{FIELDS}\n{METHOD_BODY}{TAIL}";

        String head = headGenerator.generateHead(verifyDataClasses);
        String fields = fieldsGenerator.generateFields(verifyDataClasses, interfaceClass);
        String methodBody = methodBodyGenerator.generateMethodBody(verifyDataClasses);
        String tail = VERIFY_TAIL_GENERATOR.generateTail();

        String finalContent = template
                .replace("{HEAD}", head)
                .replace("{FIELDS}", fields)
                .replace("{METHOD_BODY}", methodBody)
                .replace("{TAIL}", tail);

        File outputFile = new File(outputDir, "Verify.java");
        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(finalContent);
        }
    }
}