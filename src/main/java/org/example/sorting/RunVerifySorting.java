package org.example.sorting;

import jbse.apps.run.Run;
import jbse.apps.run.RunParameters;

import static jbse.apps.run.RunParameters.DecisionProcedureType.Z3;
import static jbse.apps.run.RunParameters.StateFormatMode.TEXT;
import static jbse.apps.run.RunParameters.StepShowMode.LEAVES;

public class RunVerifySorting {
    public static void main(String[] args) {
        // Setup JBSE to run verifySorting
        RunParameters runParameters = new RunParameters();
        setupJBSE(runParameters, "org/example/sorting/VerifySorting", "([I)V", "verify");
        runJBSE(runParameters);

    }

    private static void setupJBSE(RunParameters params, String className, String descriptor, String methodName) {
        params.addUserClasspath("build/classes/java/main");
        params.setJBSELibPath("libs/jbse-0.11.0-SNAPSHOT.jar");
        params.setMethodSignature(className, descriptor, methodName);
        params.setDecisionProcedureType(Z3);
        params.setExternalDecisionProcedurePath("C:\\Users\\nogga\\Downloads\\z3-4.13.0-x64-win\\bin\\z3.exe");
        params.setOutputFilePath("./out/runIf_z3.txt");
        params.setStateFormatMode(TEXT);
        params.setStepShowMode(LEAVES);

    }

    private static void runJBSE(RunParameters params) {
        final Run run = new Run(params);
        run.run();
    }
}
