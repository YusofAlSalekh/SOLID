package org.example;

import jbse.apps.run.RunParameters;

import static jbse.apps.run.RunParameters.DecisionProcedureType.Z3;
import static jbse.apps.run.RunParameters.StateFormatMode.TEXT;
import static jbse.apps.run.RunParameters.StepShowMode.LEAVES;

public class Run {
    public static void main(String[] args) {
        final RunParameters p = new RunParameters();
        set(p);
        final jbse.apps.run.Run r = new jbse.apps.run.Run(p);
        r.run();
    }

    private static void set(RunParameters p) {
        p.addUserClasspath("build/classes/java/main");
        p.setJBSELibPath("libs/jbse-0.11.0-SNAPSHOT.jar");
        p.setMethodSignature("org/example/VerifySorting", "([I)V", "verify");
        p.setDecisionProcedureType(Z3);
        p.setExternalDecisionProcedurePath("C:\\Users\\nogga\\Downloads\\z3-4.13.0-x64-win\\bin\\z3.exe");
       // p.setOutputFilePath("./out/runIf_z3.txt");
        p.setStateFormatMode(TEXT);
        p.setStepShowMode(LEAVES);
    }
}
