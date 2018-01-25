package com.company.tricks;

public class Anagram {
    private static final int NUMBER_OF_LETTERS = 26;

    /**
     * Given two strings, how many characters we need to remove from them to make them anagram?
     * EXAMPLE: glue -> legs. ANSWER (2, [u] from legs and [s] from legs)
     */
    public static int numbersToRemove(String s1, String s2) {
        int[] words1 = countChars(s1);
        int[] words2 = countChars(s2);

        return getDelta(words1, words2);
    }

    /*
    Counts how many of each characters are in the word
     */
    private static int[] countChars(String word) {
        int[] result = new int[NUMBER_OF_LETTERS];
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            int offset = (int) 'a';
            int index = letter - offset;
            result[index]++;
        }

        return result;
    }

    /*
    Calculate differences between each character amount in each word
     */
    private static int getDelta(int[] s1, int[] s2) {
        int counter = 0;
        if (s1.length != s2.length) {
            throw new RuntimeException();
        }

        for (int i = 0; i < s1.length; i++) {
            int diff = Math.abs(s1[i] - s2[i]);
            counter += diff;
        }

        return counter;
    }

    public static void main(String[] args) {
        String word1 = "legs";
        String word2 = "glue";
        int result = numbersToRemove(word1, word2);
        System.out.println(String.format("%s->%s. Chars to remove = %s", word1, word2, result));
    }
}
