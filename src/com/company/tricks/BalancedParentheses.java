package com.company.tricks;

import java.util.Stack;

public class BalancedParentheses {

    public static void main(String[] args) {
        String balanced = "()(())";
        String unbalanced = "(()((((()";
        String balanced2 = ")([{}({)}[{}}()]";

        System.out.println(isBalanced(balanced2));
    }

    public static boolean isBalanced(String input) {
        Stack<Character> stack = new Stack();
        char[] array = input.toCharArray();
        for (int i = 0; i < array.length; i++) {
            char symbol = array[i];
            if (stack.isEmpty()) {
                stack.push(symbol);
                continue;
            }
            if (stack.peek() != symbol) {
                stack.pop();
            } else {
                stack.push(symbol);
            }
        }

        return stack.isEmpty();
    }
}
