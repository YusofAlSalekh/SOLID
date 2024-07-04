package org.example;

public class StringUtils {
    public static String toLowerCaseFirstLetter(String word) throws Exception {
        if (word == null || word.isEmpty()) {
            throw new Exception("Word is null or empty");
        } else return Character.toLowerCase(word.charAt(0)) + word.substring(1);
    }
}
