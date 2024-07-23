package org.example;

import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;

import static jbse.apps.run.RunParameters.DecisionProcedureType.Z3;
import static jbse.apps.run.RunParameters.StateFormatMode.TEXT;
import static jbse.apps.run.RunParameters.StepShowMode.LEAVES;

public class RunVerify {
    public static void main(String[] args) {
        RunParameters verify1 = new RunParameters();
        setupJBSE(verify1, "org/example/Verify", "(Lorg/example/Param;)V", "verify1", "verify1_output.txt");
        runJBSE(verify1);

        RunParameters verify2 = new RunParameters();
        setupJBSE(verify2, "org/example/Verify", "(Lorg/example/Param;)V", "verify2", "verify2_output.txt");
        runJBSE(verify2);

        RunParameters verify3 = new RunParameters();
        setupJBSE(verify3, "org/example/Verify", "(Lorg/example/Param;)V", "verify3", "verify3_output.txt");
        runJBSE(verify3);

    }

    private static void setupJBSE(RunParameters params, String className, String descriptor, String methodName, String outputFileName) {
        params.addUserClasspath("build/classes/java/main");
        params.setJBSELibPath("libs/jbse-0.11.0-SNAPSHOT.jar");
        params.setMethodSignature(className, descriptor, methodName);
        params.setDecisionProcedureType(Z3);
        params.setExternalDecisionProcedurePath("C:\\Users\\nogga\\Downloads\\z3-4.13.0-x64-win\\bin\\z3.exe");
        params.setOutputFilePath("./out/" + outputFileName);
        params.setStateFormatMode(TEXT);
        params.setStepShowMode(LEAVES);
    }

    private static void runJBSE(RunParameters params) {
        final Run run = new Run(params);
        run.run();
    }
}