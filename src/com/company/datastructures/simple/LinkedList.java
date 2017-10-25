package com.company.datastructures.simple;

public class LinkedList {
    private static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    private Node head;

    public void addToTail(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    public void addToHead(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }

        newNode.next = head;
        head = newNode;
    }

    public void printList() {
        Node current = head;
        while(current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
    }

    public void remove(int data) {
        if (head == null) {
            return;
        }
        if (head.data == data) {
            head = head.next;
        }

        Node current = head;
        while(current.next != null) {
            if (current.next.data == data) {
                current.next = current.next.next;
            }
            current = current.next;
        }
    }

    public boolean isCycle() {
        if (head == null) {
            return false;
        }

        Node slow = head;
        Node fast = head.next;
        while(slow != null && fast != null && fast.next != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return false;
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.addToTail(1);
        list.addToTail(2);
        list.addToTail(3);
        list.addToTail(4);
        list.addToTail(5);
        list.addToTail(3);

        list.printList();

        list.remove(5);
        System.out.println();
        list.printList();

        System.out.println();
        System.out.println(list.isCycle());
    }
}
