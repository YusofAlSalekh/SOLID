package org.example;

public class IncrementationA implements Incrementation {

    @Override
    public Param method(Param input) {
        return input.add(1);
    }
}


