package com.company.datastructures.simple;

public class Stack {
    private int size;
    private Node top;

    private static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(int data) {
        Node node = new Node(data);
        if (top == null) {
            top = node;
        }
        node.next = top;
        top = node;
        size++;
    }

    public int pop() {
        if (top == null) {
            throw new IllegalStateException();
        }

        int result = top.data;
        top = top.next;
        return result;
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(5);
        stack.push(1);
        stack.push(4);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
