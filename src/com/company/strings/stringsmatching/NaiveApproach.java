package com.company.strings.stringsmatching;

public class NaiveApproach {

    public static void main(String[] args) {
        String text = "abcaabdfdcbaab";
        String pattern = "aab";

        System.out.println(matches2(pattern, text));
    }

    private static boolean matches1(String pattern, String text) {
        int textLength = text.length();
        int patternLength = pattern.length();
        for (int i = 0; i < textLength - patternLength; i++) {
            String temp = text.substring(i, i+patternLength);
            if (temp.equals(pattern)) {
                return true;
            }
        }

        return false;
    }

    private static boolean matches2(String pattern, String text) {
        int textLength = text.length();
        int patternLength = pattern.length();

        char[] pArray = pattern.toCharArray();
        char[] tArray = text.toCharArray();
        for (int i = 0; i < textLength - patternLength; i++) {
            for (int j = 0; j < patternLength; j++) {
                if (pArray[j] != tArray[j+i]) {
                    break;
                }
                if (j == patternLength-1) {
                    return true;
                }
            }
        }

        return false;
    }
}
