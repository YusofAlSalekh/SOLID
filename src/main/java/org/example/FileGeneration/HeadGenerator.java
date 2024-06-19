package org.example.FileGeneration;

public class HeadGenerator {
    public String generateHead(){
        return "package org.example;\n\n" +
                "import java.util.Arrays;\n\n" +
                "import static jbse.meta.Analysis.ass3rt;\n" +
                "import static jbse.meta.Analysis.assume;\n";
    }
}