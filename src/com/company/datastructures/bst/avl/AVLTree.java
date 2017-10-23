package com.company.datastructures.bst.avl;

public class AVLTree {
    private Node root;

    public void insert(int data) {
        Node node = new Node(data);
        if (root == null) {
            root = node;
            return;
        }

        insert(root, node);
        checkBalance(node);
    }

    private void insert(Node parent, Node node) {
        if (node.data < parent.data) {
            parent.height++;
            if (parent.left == null) {
                parent.left = node;
                node.parent = parent;
                node.height++;
            } else {
                insert(parent.left, node);
            }
        } else {
            parent.height++;
            if (parent.right == null) {
                parent.right = node;
                node.parent = parent;
                node.height++;
            } else {
                insert(parent.right, node);
            }
        }
    }

    private void checkBalance(Node node) {
        if ((getHeight(node.left) - getHeight(node.right)) > 1 || (getHeight(node.left) - getHeight(node.right)) < -1) {
            rebalance(node);
        }

        if (node.parent == null) {
            return;
        }
        checkBalance(node.parent);
    }

    private void rebalance(Node node) {
        // unbalance in left subtree
        if ((getHeight(node.left) - getHeight(node.right)) > 1) {
            // left left violation
            if (getHeight(node.left.left) > getHeight(node.left.right)) {
                rotateRight(node);
            } else {
                // left right violation
                node.left = rotateLeft(node.left);
                rotateRight(node);
            }
        } else {
            // unbalance in right
            if (getHeight(node.right.right) > getHeight(node.right.left)) {
                // right right violation
                rotateLeft(node);
            } else {
                // right left violation
                node.right = rotateRight(node.right);
                rotateLeft(node);
            }
        }
    }

    private Node rotateLeft(Node node) {
        Node temp = node.right;
        temp.parent = node.parent;
        node.right = temp.left;
        node.parent = temp;
        temp.left = node;
        return temp;
    }

    private Node rotateRight(Node node) {
        Node temp = node.left;
        temp.parent = node.parent;
        node.left = temp.right;
        node.parent = temp;
        temp.right = node;
        return temp;
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    public boolean contains(Node node) {

        return false;
    }

    public Node delete(int data) {

        return null;
    }

    private class Node {
        private Node left;
        private Node right;
        private Node parent;
        private int data;
        private int height;

        public Node(int data) {
            this.data = data;
        }

        public void printPreOrder() {
            System.out.println(data);
            if (left != null) {
                left.printPreOrder();
            }
            if (right != null) {
                right.printPreOrder();
            }
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.insert(43);
        tree.insert(18);
        tree.insert(22);
        //tree.insert(9);
        //tree.insert(21);

        tree.printInOrder();
    }

    private void printInOrder() {
        root.printPreOrder();
    }
}
