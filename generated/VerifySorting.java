package org.example;

import java.util.Arrays;

import static jbse.meta.Analysis.ass3rt;
import static jbse.meta.Analysis.assume;

    private static final IncrementationA incrementationA = new IncrementationA();
    private static final IncrementationC incrementationC = new IncrementationC();
    private static final IncrementationB incrementationB = new IncrementationB();

    public void verify1(Param input) {
        Param a = incrementationA.method(input));
        Param b = incrementationC.method(input));
        ass3rt(a.equals(b));
    }

    public void verify2(Param input) {
        Param a = incrementationA.method(input));
        Param b = incrementationB.method(input));
        ass3rt(a.equals(b));
    }

    public void verify3(Param input) {
        Param a = incrementationC.method(input));
        Param b = incrementationB.method(input));
        ass3rt(a.equals(b));
    }


}