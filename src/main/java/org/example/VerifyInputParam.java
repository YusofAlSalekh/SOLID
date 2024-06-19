package org.example;

import static jbse.meta.Analysis.ass3rt;

public class VerifyInputParam {

    IncrementationA methodA = new IncrementationA();
    IncrementationB methodB = new IncrementationB();
    IncrementationC methodC = new IncrementationC();

    public void verifyMethodAB(Param input) {
        Param a = methodA.method(input);
        Param b = methodB.method(input);
        ass3rt(a.equals(b));
    }

    public void verifyMethodAC(Param input) {
        Param a = methodA.method(input);
        Param c = methodC.method(input);
        ass3rt(a.equals(c));
    }

    public void verifyMethodBC(Param input) {
        Param b = methodB.method(input);
        Param c = methodC.method(input);
        ass3rt(b.equals(c));
    }
}
