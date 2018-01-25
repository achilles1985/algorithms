package com.company.strings;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static java.util.stream.Collectors.joining;

public class RemoveDuplicates {

    public static void main(String[] args) {
        String input = "geeksforgeeks";
        String result = removeDuplicatesPure(input);
        System.out.println(String.format("Input %s. Output after removal: %s", input, result));

        System.out.println(removeDuplicatesHashSet(input));
    }

    /*
        Travers sorted array replacing each next index with next unique value.
     */
    private static String removeDuplicatesPure(String input) {
        char[] sorted = input.toCharArray();
        Arrays.sort(sorted);
        int current = 1;
        int index = 1;
        while (current != sorted.length) {
            if (sorted[current] != sorted[current-1]) {
                sorted[index] = sorted[current];
                index++;
            }
            current++;
        }

        return String.valueOf(Arrays.copyOf(sorted, index));
    }

    private static String removeDuplicatesHashSet(String input) {
        Set<Character> set = new LinkedHashSet<>();
        for (Character c: input.toCharArray()) {
            set.add(c);
        }

        return set.stream()
                .map(String::valueOf)
                .collect(joining());
    }

}

