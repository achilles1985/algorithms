package com.company.sorting;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {
        int[] unsorted = new int[] {8, 2, 5, 1, 4, 7, 3, 6, 10};
        sort(unsorted);
        System.out.println(Arrays.toString(unsorted));
    }

    public static void sort(int[] input) {
        for (int i = 1; i < input.length; i++) {
            for (int j = i-1; j >= 0; j--) {
                if (input[j+1] < input[j]) {
                    swap(j+1, j, input);
                }
            }
        }
    }

    private static void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
