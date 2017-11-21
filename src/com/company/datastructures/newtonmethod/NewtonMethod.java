package com.company.datastructures.newtonmethod;

public class NewtonMethod {

    public static void main(String[] args) {
        int number = 10;
        double result = findRoot(number);
        System.out.println(String.format("Square root from %s is %s", number, result));

        double result2 = findRootRecurse(number, 5);
        System.out.println(String.format("Square root from %s is %s", number, result2));
    }

    private static double findRoot(int a) {
        double guess = a/2.0;

        double current = guess;
        double previous = 0;
        while (Math.abs(previous - current) > 0.000001) {
            previous = current;
            current = (current + a/current)/2;
        }

        return current;
    }

    private static double findRootRecurse(double number, double guess) {
        if (isGood(number, guess)) {
            return guess;
        }

        return findRootRecurse(number, nextGuess(number, guess));
    }

    private static double nextGuess(double number, double guess) {
        return (guess + number/guess)/2;
    }

    private static boolean isGood(double number, double guess) {
        return Math.abs(guess*guess - number)/number < 0.000001;
    }
}
