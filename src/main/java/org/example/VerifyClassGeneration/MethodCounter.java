package org.example.VerifyClassGeneration;

public class MethodCounter {
    private static int counter = 1;

    public static int getNext() {
        return counter++;
    }
}
