package com.company.datastructures.simple;

public class BinarySearch {

    public static void main(String[] args) {
        int arr[] = new int[] {1, 2, 5, 7, 8, 10, 12, 24, 16, 18, 20, 21, 32, 45, 60};
        int x  = 22;
        System.out.println(String.format("Is %s in array: %s", x, binarySearch(arr, x)));
    }

    public static boolean binarySearch(int[] array, int x) {
        return binarySearch(array, x, 0, array.length - 1);
    }

    private static boolean binarySearch(int[] array, int x, int left, int right) {
        if (left > right) {
            return false;
        }

        int mid = (right + left)/2;
        if (x == array[mid]) {
            return true;
        } else if (x < array[mid]) {
            return binarySearch(array, x, 0, mid - 1);
        } else {
            return binarySearch(array, x, mid + 1, right);
        }
    }
}
