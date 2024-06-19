package org.example;

import java.util.Objects;

public class Param {
    private int value;

    public Param(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public Param add(int addValue) {
        return new Param(this.value + addValue);
    }

    public Param subtract(int subValue) {
        return new Param(this.value - subValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Param that = (Param) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "InputParam{" +
                "value=" + value +
                '}';
    }
}
