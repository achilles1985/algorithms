package com.company.strings;

public class IsPolindrom {

    public static void main(String[] args) {
        String input = "abcdcba";
        System.out.println(String.format("Is %s polindrom: %s", input, isPolindrom(input)));
    }

    private static boolean isPolindrom(String input) {
        int i = 0;
        int j = input.length() - 1;
        while (i != j) {
            if (input.charAt(i) != input.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
