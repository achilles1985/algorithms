package com.company.datastructures;

public class Node {
    private int data;
    private Node left;
    private Node right;

    public Node(int data) {
        this.data = data;
    }

    public void insert(int value) {
        if (value <= data) {
            if (left == null) {
                left = new Node(value);
            } else {
                left.insert(value);
            }
        } else {
            if (right == null) {
                right = new Node(value);
            } else {
                right.insert(value);
            }
        }
    }

    public boolean contains(int value) {
        if (value == data) {
            return true;
        }
        if (value < data) {
            if (left == null) {
                return false;
            }
            return left.contains(value);
        } else {
            if (right == null) {
                return false;
            }
            return right.contains(value);
        }
    }

    public void printInOrder() {
        if (left != null) {
            left.printInOrder();
        }
        System.out.println(data);
        if (right != null) {
            right.printInOrder();
        }
    }

    public void preOrder() {
        System.out.println(data);
        if (left != null) {
            left.preOrder();
        }
        if (right != null) {
            right.preOrder();
        }
    }

    public void postOrder() {
        if (left != null) {
            left.postOrder();
        }
        if (right != null) {
            right.postOrder();
        }
        System.out.println(data);
    }

    public void printByLevelsFromRoot() {
        int height = height(this);
        for (int i = 1; i <= height; i++) {
            printLevel(this, i);
            System.out.println();
        }
    }

    private void printLevel(Node root, int level) {
        if (root == null) {
            return;
        }
        if (level == 1) {
            System.out.print(root.data + ", ");
        } else if (level > 1) {
            printLevel(root.left, level - 1);
            printLevel(root.right, level - 1);
        }
    }

    private int height(Node root) {
        if (root == null) {
            return 0;
        }
        int lheight = height(root.left);
        int rheight = height(root.right);
        if (lheight > rheight) {
            return lheight + 1;
        }
        return rheight + 1;
    }
}
