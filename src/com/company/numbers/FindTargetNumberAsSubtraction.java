package com.company.numbers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.binarySearch;

/*
Input : unsorted array of nonnegative integers and target integer value representing subtraction of the 2 elements.
Output : 2 elements, subtraction of which gives target value
 */
public class FindTargetNumberAsSubtraction {

    public static void main(String[] args) {
        int[] input = new int[]{1, 5, 3, 4, 2};
        int target = 3;
        //int[] result = findSubtractionSorted(input, target);

        //int[] result = findSubtractionNaive(input, target);
        //List<List<Integer>> result = getPairsWithDiffK(input, target);
        List<List<Integer>> result = getPairInLinearTime(input, target);
        System.out.println("Result: " + result);
    }

    private static int[] findSubtractionNaive(int[] arr, int target) {
        int[] result = new int[2];
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (Math.abs(arr[i] - arr[j]) == target) {
                    result[0] = arr[i];
                    result[1] = arr[j];
                    return result;
                }
            }
        }

        return result;
    }

    private static int[] findSubtractionSorted(int[] arr, int target) {
        int[] result = new int[2];
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int diff = Math.abs(arr[i] - arr[j]);
                if (diff == target) {
                    result[0] = arr[i];
                    result[1] = arr[j];
                    return result;
                }
                if (diff > target) {
                    break;
                }
            }
        }

        return result;
    }

    private static int[] findSubtractionSorted2(int[] arr, int target) {
        int[] result = new int[2];
        Arrays.sort(arr);
        int found = binarySearch(arr, 0, arr.length - 1, target);

        for (int i = 0; i < arr.length - 1; i++) {
            int diff = Math.abs(arr[i] - arr[i + 1]);
            if (diff == target) {
                result[0] = arr[i];
                result[1] = arr[i + 1];
                return result;
            }
            if (diff > target) {
                break;
            }
        }

        return result;
    }

    // O(n*logn)
    static List<List<Integer>> getPairsWithDiffK(int arr[], int k) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr);
        Integer[] arrNew = removeDuplicates(arr);
        int length = arr.length;

        for (int i = 0; i < length - 1; i++) {
            if (binarySearch(arrNew, i, length, arrNew[i] + k) >= 0) {
                result.add(Arrays.asList(arrNew[i], arrNew[i] + k));
            }
        }

        return result;
    }

    private static Integer[] removeDuplicates(int[] arr) {
        Set<Integer> temp = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            temp.add(arr[i]);
        }

        return temp.toArray(new Integer[0]);
    }

    // O(n*logn)
    private static List<List<Integer>> getPairInLinearTime(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr);
        int size = arr.length;

        int i = 0;
        int j = 1;
        while (i < size && j < size) {
            if ((arr[j] - arr[i]) == target)  {
                result.add(Arrays.asList(arr[i], arr[j]));
                i++;
                j++;
            } else if ((arr[j] - arr[i]) < target) {
                j++;
            } else {
                i++;
            }
        }

        return result;
    }
}
