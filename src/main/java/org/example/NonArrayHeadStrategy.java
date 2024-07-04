package org.example;

public class NonArrayHeadStrategy implements HeadStrategy{
    public String generateHead(){
        return "package org.example;\n\n" +
                "import static jbse.meta.Analysis.ass3rt;\n\n" +
                "public class Verify {";
    }
}
