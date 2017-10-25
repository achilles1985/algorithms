package com.company.datastructures.bst;

public class BinarySearchTree {
    private Node root;

    private static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public void add(int data) {
        root = add(root, data);
    }

    private Node add(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }

        if (data < node.data) {
            node.left = add(node.left, data);
        } else {
            node.right = add(node.right, data);
        }
        return node;
    }

    public boolean isBST() {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBST(Node node, int minValue, int maxValue) {
        if (node == null) {
            return true;
        }

        if (node.data > maxValue - 1 || node.data < minValue - 1) {
            return false;
        }

        return isBST(node.left, Integer.MIN_VALUE, node.data) && isBST(node.right, node.data, Integer.MAX_VALUE);
    }

    public void printPreOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.data);
        printPreOrder(node.left);
        printPreOrder(node.right);
    }

    public void printBylevels() {

    }

    private int height(Node node) {
        if (node == null) {
            return -1;
        }
        return max(height(node.left), height(node.right)) + 1;
    }

    private int max(int left, int right) {
        return left > right ? left : right;
    }

    public static void main(String[] args) {
        // 8, 3, 6, 5, 7, 10, 15, 12
        BinarySearchTree tree = new BinarySearchTree();
        tree.add(8);
        tree.add(3);
        tree.add(6);
        tree.add(5);
        tree.add(7);
        tree.add(10);
        tree.add(15);
        tree.add(12);

        tree.printPreOrder(tree.root);
    }
}
