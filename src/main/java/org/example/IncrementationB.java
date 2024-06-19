package org.example;

public class IncrementationB implements Incrementation {

    @Override
    public Param method(Param input) {
        return input.add(1).subtract(1).add(1);
    }
}