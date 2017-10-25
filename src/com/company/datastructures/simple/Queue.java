package com.company.datastructures.simple;

public class Queue {
    private Node head;
    private Node tail;

    private static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void add(int data) {
        Node node = new Node(data);
        if (head == null) {
            head = node;
        }
        if (tail == null) {
            tail = node;
        }

        tail.next = node;
        tail = node;
    }

    public int remove() {
        if (head == null) {
            throw new IllegalStateException();
        }
        int data = head.data;
        head = head.next;

        return data;
    }

    public static void main(String[] args) {
        Queue queue =  new Queue();
        queue.add(3);
        queue.add(1);
        queue.add(5);

        System.out.println(queue.remove());
        System.out.println(queue.remove());
    }
}
