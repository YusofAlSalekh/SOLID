package org.example;

public class RunClassHeadGenerator  {
    public String generateHead() {
        return "package org.example;\n\n" +
                "import jbse.apps.run.RunParameters;\n" +
                "import jbse.apps.run.Run;\n\n" +
                "import static jbse.apps.run.RunParameters.DecisionProcedureType.Z3;\n" +
                "import static jbse.apps.run.RunParameters.StateFormatMode.TEXT;\n" +
                "import static jbse.apps.run.RunParameters.StepShowMode.LEAVES;\n\n" +
                "public class RunVerify {\n" +
                "    public static void main(String[] args) {\n";
    }
}