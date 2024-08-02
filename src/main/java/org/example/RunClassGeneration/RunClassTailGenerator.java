package org.example.RunClassGeneration;

public class RunClassTailGenerator {

    public String generateTail() {
        return "    }\n\n" +
                "    private static void setupJBSE(RunParameters params, String className, String descriptor, String methodName, String outputFileName) {\n" +
                "        params.addUserClasspath(\"build/classes/java/main\");\n" +
                "        params.setJBSELibPath(\"libs/jbse-0.11.0-SNAPSHOT.jar\");\n" +
                "        params.setMethodSignature(className, descriptor, methodName);\n" +
                "        params.setDecisionProcedureType(Z3);\n" +
                "        params.setExternalDecisionProcedurePath(\"C:\\\\Users\\\\nogga\\\\Downloads\\\\z3-4.13.0-x64-win\\\\bin\\\\z3.exe\");\n" +
                "        params.setOutputFilePath(\"./out/\" + outputFileName);\n" +
                "        params.setStateFormatMode(TEXT);\n" +
                "        params.setStepShowMode(LEAVES);\n" +
                "    }\n\n" +
                "    private static void runJBSE(RunParameters params) {\n" +
                "        final Run run = new Run(params);\n" +
                "        run.run();\n" +
                "    }\n" +
                "}";
    }
}