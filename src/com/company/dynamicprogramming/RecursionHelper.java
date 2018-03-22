package com.company.dynamicprogramming;

public class RecursionHelper {

    public static void main(String[] args) {
        int input = 5;
        int[] arr = new int[] {5,3,5,7,10};
        int result = RecursionHelper.sum(input);
        int result2 = RecursionHelper.factorial(input);
        int result3 = RecursionHelper.sumArray(arr, arr.length-1);
        int res4 = RecursionHelper.pow(2, 5);
        String res5 = reverseString("abcd");
        boolean res6 = isPolindrom("racecar");
        printArray(new int[] {1, 2, 3, 4, 5});

        System.out.println(String.format("Sum of %s is %s", input, result));
        System.out.println(String.format("Factorial of %s is %s", input, result2));
        System.out.println(String.format("Sum array of %s is %s", arr.length, result3));
        System.out.println("Power of 2: " + res4);
        System.out.println("abcd reverse: " + res5);
        System.out.println("Is radar polindrom: " + res6);
    }

    public static int sum(int number) {
        if (number == 0) {
            return 0;
        }
        return number + sum(number - 1);
    }

    public static int factorial(int number) {
        if (number <= 1) {
            return 1;
        }
        return number*factorial(number - 1);
    }

    public static int sumArray(int[] input, int index) {
        if (index == 0) {
            return input[0];
        }
        return input[index] + sumArray(input, index-1);
    }

    public static int pow(int base, int exp) {
        if (exp == 1) {
            return base;
        }
        return base*pow(base, exp-1);
    }

    public static String  reverseString(String input) {
       return reverseString(input, input.length()-1);
    }

    private static String reverseString(String input, int i) {
        if (i == 0) {
            return "" + input.charAt(i);
        }
        return "" + input.charAt(i) + reverseString(input, i-1);
    }

    public static boolean isPolindrom(String input) {
        if (input == null) {
            return false;
        }
        if (input.isEmpty() || input.length() == 1) {
            return true;
        }
        int length = input.length()-1;
        if (input.charAt(0) == input.charAt(length)) {
            return isPolindrom(input.substring(1, length));
        }
        return false;
    }

    public static void printArray(int[] arr) {
        printArray(arr,  arr.length-1);
    }

    private static void printArray(int[] arr, int index) {
        if (index < 0) {
            return;
        }
        System.out.print(arr[index] + ", "); // reverse order
        printArray(arr, index-1);
        //System.out.print(arr[index] + ", "); // direct order
    }

}
