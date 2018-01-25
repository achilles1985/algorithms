package com.company.searching;

public class BinarySearch {

    public static void main(String[] args) {
        int[] sorted = new int[] {1,2,3,4,5,6,7,8,9,10};
        int element = 100;
        System.out.println(String.format("Element %s. Found = %s ", element, binarySearch(sorted, element)));
    }

    private static boolean binarySearch(int[] array, int element) {
        return binarySearch(array, element, 0, array.length-1);
    }

    private static boolean binarySearch(int[] array, int element, int left, int right) {
        if (left > right) {
            return false;
        }

        int mid = (right + left)/2;
        if (element == array[mid]) {
            return true;
        } else if (element < array[mid]) {
            return binarySearch(array, element, left, mid-1);
        } else {
            return binarySearch(array, element, mid+1, right);
        }
    }

}
