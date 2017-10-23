package com.company.datastructures.bst;

public class Driver {

    public static void main(String[] args) {
        Node node = new Node(9);
        node.insert(13);
        node.insert(6);
        node.insert(7);
        node.insert(5);
        node.insert(15);
        node.insert(12);

        //node.printInOrder();
        //node.preOrder();
        //node.postOrder();
        node.printByLevelsFromRoot();

    }
}
