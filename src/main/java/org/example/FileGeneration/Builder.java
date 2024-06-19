package org.example.FileGeneration;

public class Builder {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("abc");
        stringBuilder.append("abc");
        System.out.println(stringBuilder.toString());

        String s = "123";
        int a = 4;
        System.out.println(s + a);
    }
}
