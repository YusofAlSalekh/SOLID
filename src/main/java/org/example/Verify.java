package org.example;

import static jbse.meta.Analysis.ass3rt;

public class Verify {
    private static final Incrementation incrementationA = new IncrementationA();
    private static final Incrementation incrementationC = new IncrementationC();
    private static final Incrementation incrementationB = new IncrementationB();

    public void verify1(Param input) {
        Param a = incrementationA.method(input);
        Param b = incrementationC.method(input);
        ass3rt(a.equals(b));
    }

    public void verify2(Param input) {
        Param a = incrementationA.method(input);
        Param b = incrementationB.method(input);
        ass3rt(a.equals(b));
    }

    public void verify3(Param input) {
        Param a = incrementationC.method(input);
        Param b = incrementationB.method(input);
        ass3rt(a.equals(b));
    }

}