package org.example;

import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;

import static jbse.apps.run.RunParameters.DecisionProcedureType.Z3;
import static jbse.apps.run.RunParameters.StateFormatMode.TEXT;
import static jbse.apps.run.RunParameters.StepShowMode.LEAVES;

public class RunVerifyInputParam {/*
    public static void main(String[] args) {
        final RunParameters p = new RunParameters();
        set(p);
        final Run r = new Run(p);
        r.run();
    }

    private static void set(RunParameters p) {
        p.addUserClasspath("build/classes/java/main");
        p.setJBSELibPath("libs/jbse-0.11.0-SNAPSHOT.jar");
        p.setMethodSignature("org/example/VerifyInputParamTemplate", "(Lorg/example/InputParam;)V", "verifyMethodAB");
        p.setDecisionProcedureType(Z3);
        p.setExternalDecisionProcedurePath("C:\\Users\\nogga\\Downloads\\z3-4.13.0-x64-win\\bin\\z3.exe");
        p.setOutputFilePath("./out/runIf_z3.txt");
        p.setStateFormatMode(TEXT);
        p.setStepShowMode(LEAVES);
    }*/
    public static void main(String[] args) {
        // Setup JBSE to run verifyMethodAB
        RunParameters paramsAB = new RunParameters();
        setupJBSE(paramsAB, "org/example/VerifyInputParamTemplate", "(Lorg/example/Param;)V", "verifyMethodAB");
        runJBSE(paramsAB);

        // Setup JBSE to run verifyMethodAC
        RunParameters paramsAC = new RunParameters();
        setupJBSE(paramsAC, "org/example/VerifyInputParamTemplate", "(Lorg/example/Param;)V", "verifyMethodAC");
        runJBSE(paramsAC);

        // Setup JBSE to run verifyMethodBC
        RunParameters paramsBC = new RunParameters();
        setupJBSE(paramsBC, "org/example/VerifyInputParamTemplate", "(Lorg/example/Param;)V", "verifyMethodBC");
        runJBSE(paramsBC);

      /*  RunParameters paramsCachB = new RunParameters();
        setupJBSE(paramsCachB, "org/example/VerifyInputParamTemplate", "(Lorg/example/Param;)V", "verifyMethodBCash");
        runJBSE(paramsCachB);

        RunParameters paramsCachC = new RunParameters();
        setupJBSE(paramsCachC, "org/example/VerifyInputParamTemplate", "(Lorg/example/Param;)V", "verifyMethodCCash");
        runJBSE(paramsCachC);*/
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
