package org.example;

import org.example.FileGeneration.FieldsGenerator;
import org.example.FileGeneration.HeadGenerator;
import org.example.FileGeneration.MethodBodyGenerator;
import org.example.FileGeneration.TailGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JavaFileAssembler {

    private final HeadGenerator headGenerator = new HeadGenerator();
    private final FieldsGenerator fieldsGenerator = new FieldsGenerator();
    private final MethodBodyGenerator methodBodyGenerator = new MethodBodyGenerator();
    private final TailGenerator tailGenerator = new TailGenerator();

    public void generateJavaFile(String outputDir, List<DataClass> dataClasses, Class<?> interfaceClass) throws Exception {
        String template = "{HEAD}\n{FIELDS}\n{METHOD_BODY}\n{TAIL}";

        String head = headGenerator.generateHead();
        String fields = fieldsGenerator.generateFields(dataClasses, interfaceClass);
        String methodBody = methodBodyGenerator.generateMethodBody(dataClasses);
        String tail = tailGenerator.generateTail();

        String finalContent = template
                .replace("{HEAD}", head)
                .replace("{FIELDS}", fields)
                .replace("{METHOD_BODY}", methodBody)
                .replace("{TAIL}", tail);

        File outputFile = new File(outputDir, "VerifySorting.java");
        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(finalContent);
        }
    }
}
