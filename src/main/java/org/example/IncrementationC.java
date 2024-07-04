package org.example;

public class IncrementationC implements Incrementation {

    @Override
    public Param method(Param input) {
        return input.add(2);
    }
}