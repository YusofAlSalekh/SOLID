package org.example;

import java.util.HashMap;
import java.util.Map;

public class CachingIncrementationA extends IncrementationA {
    private Map<Integer, Param> cache = new HashMap<>();

    @Override
    public Param method(Param input) {
        if (cache.containsKey(input.getValue())) {
            return cache.get(input.getValue());
        }

        Param result = super.method(input);

        cache.put(input.getValue(), result);

        return result;
    }
}
