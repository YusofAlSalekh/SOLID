package org.example.ifExample;

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
        p.setDecisionProcedureType(Z3);
        p.setExternalDecisionProcedurePath("C:\\Users\\nogga\\Downloads\\z3-4.13.0-x64-win\\bin\\z3.exe");
        p.setOutputFilePath("./out/runIf_z3.txt");
        p.setStateFormatMode(TEXT);
        p.setStepShowMode(LEAVES);
    }
}