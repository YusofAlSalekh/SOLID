package org.example.ifExample;

import static jbse.apps.run.RunParameters.DecisionProcedureType.CVC4;
import static jbse.apps.run.RunParameters.StateFormatMode.TEXT;
import static jbse.apps.run.RunParameters.StepShowMode.LEAVES;

import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;

import static jbse.apps.run.RunParameters.DecisionProcedureType.Z3;

public class RunIf {

    public static void main(String[] args) {
        final RunParameters p = new RunParameters();
        set(p);
        final Run r = new Run(p);
        r.run();
    }

    private static void set(RunParameters p) {
        p.addUserClasspath("build/classes/java/main");
        p.setJBSELibPath("libs/jbse-0.11.0-SNAPSHOT.jar");
        p.setMethodSignature("org/example/IfExample", "(I)V", "m");
        p.setDecisionProcedureType(CVC4);
        p.setExternalDecisionProcedurePath("C:\\Users\\nogga\\Downloads\\cvc4-1.6-win64-opt.exe");
        p.setOutputFilePath("./out/runIf_cvc4.txt");
        p.setStateFormatMode(TEXT);
        p.setStepShowMode(LEAVES);
    }
}