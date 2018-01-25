package com.company.strings.stringsmatching;

public class RabinKarpApproach {

    public static void main(String[] args) {
        String text = "abcbca";
        String word = "ss";

        System.out.println(cWayMatches(word, text));
    }

    public static boolean javaWayMaches(String word, String text) {
        int wordHash = javaHash(word, 0, word.length());
        for (int i = 0; i <= text.length() - word.length(); i++) {
            int subTextHash = javaHash(text, i, word.length());
            if (wordHash == subTextHash) {
                return true;
            }
        }

        return false;
    }

    public static boolean cWayMatches(String word, String text) {
        char[] wordArray = word.toCharArray();
        char[] textArray = text.toCharArray();

        int wordHash = hash(wordArray, 0, word.length());
        for (int i = 0; i <= textArray.length - wordArray.length; i++) {
            int subTextHash = hash(textArray, i, word.length());
            if (wordHash == subTextHash) {
                for (int j = 0; j < wordArray.length; j++) {
                    if (wordArray[j] != textArray[i+j]) {
                        break;
                    }
                    return true;
                }
            }
        }

        return false;
    }

    private static int javaHash(String word, int start, int end) {
        return word.substring(start, start + end).hashCode();
    }

    private static int hash(char[] word, int start, int end) {
        int hash = 0;
        for (int i = start; i < start + end; i++) {
            hash = 31*hash + word[i];
        }

        return hash;
    }
}
