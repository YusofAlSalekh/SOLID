package org.example.RunClassGeneration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RunFileAssembler {

    private static final RunClassHeadGenerator runClassHeadGenerator = new RunClassHeadGenerator();
    private static final RunClassMainBody  runClassMainBody = new RunClassMainBody();
    private static final RunClassTailGenerator  runClassTailGenerator = new RunClassTailGenerator ();

    public static void generateRunClass(String outputDir, List<RunDataClass> dataClasses) throws IOException {
        String template = "{HEAD}{MAIN_BODY}{TAIL}";

        String head = runClassHeadGenerator.generateHead();
        String methodBody = runClassMainBody.generateMethodBody(dataClasses);
        String tail = runClassTailGenerator .generateTail();

        String finalContent = template
                .replace("{HEAD}", head)
                .replace("{MAIN_BODY}", methodBody)
                .replace("{TAIL}", tail);

        File outputFile = new File(outputDir, "RunVerify.java");
        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(finalContent);
        }
    }
}
