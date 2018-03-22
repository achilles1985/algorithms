package com.company.dynamicprogramming;

import java.util.Arrays;

public class Fibonacci {

    public static void main(String[] args) {
        int[] cache = new int[100];
        Arrays.fill(cache, -1);

        int number = 4;
        long start = System.currentTimeMillis();
        int result = fibonacciDynamic(number, cache);
        //int result = fibonacci(number);
        long end = System.currentTimeMillis();
        long taken = (end-start)/1000;
        System.out.println(String.format("Fib number for first %s is %s. Took: %s", number, result, taken));
    }

    public static int fibonacci(int number) {
        if (number < 2) {
            return number;
        }

        return fibonacci(number-1) + fibonacci(number-2);
    }

    public static int fibonacciDynamic(int number, int[] cache) {
        if (cache[number] == -1) {
            if (number <= 1) {
                return 1;
            } else {
                cache[number] = fibonacci(number - 1) + fibonacci(number - 2);
            }
        }

        return cache[number];
    }

}
