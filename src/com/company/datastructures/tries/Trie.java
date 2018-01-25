package com.company.datastructures.tries;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Trie {

    private Node root = new Node();

    public static class Node {
        private static final int ALPHABET_CHARS = 26;

        private Node[] children = new Node[ALPHABET_CHARS];
        private boolean isEndOfWord;
        private boolean visited;
        private Node parent;
        private char data;
    }

    public void add(String word) {
        Node current = root;
        int i;
        for (i = 0; i < word.length(); i++) {
            char character = word.charAt(i);
            int index = character - 'a';
            if (current.children[index] == null) {
                Node node = new Node();
                node.parent = current;
                node.data = character;
                current.children[index] = node;
            }
            current = current.children[index];
            current.isEndOfWord = false;
        }
        current.isEndOfWord = true;
    }

    public boolean exists(String word) {
        Node current = root;
        int i;
        for (i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (current.children[index] == null) {
                return false;
            }
            current = current.children[index];
        }

        return current.isEndOfWord;
    }

    public List<String> findAll(String prefix) {
        List<String> result = new ArrayList<>();

        Node current = getLastPrefixNode(prefix);
        Stack<Node> stack = new Stack<>();
        current.visited = true;
        stack.push(current);
        while(!stack.isEmpty()) {
            Node node = stack.pop();
            if (node.isEndOfWord) {
                String word = buildWord(node);
                result.add(word);
            }
            for (Node child: node.children) {
                if (child != null && !child.visited) {
                    child.visited = true;
                    child.parent = node;
                    stack.push(child);
                }
            }
        }

        return result;
    }

    private Node getLastPrefixNode(String prefix) {
        Node current = root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (current.children[index] == null) {
                return null;
            }
            current = current.children[index];
        }

        return current;
    }

    private String buildWord(Node node) {
        StringBuilder word = new StringBuilder();
        while (node.parent != null) {
            word.append(String.valueOf(node.data));
            node = node.parent;
        }
        return word.reverse().toString();
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("tom");
        trie.add("tomas");
        trie.add("tommy");
        trie.add("tomma");
        trie.add("tompson");
        trie.add("terry");
        trie.add("adam");
        trie.add("adamsom");
        trie.add("amanda");

        System.out.println(trie.exists("amanda"));

        String prefix = "tom";
        List<String> result = trie.findAll(prefix);
        System.out.println(String.format("Found for prefix %s: %s", prefix, result));
    }
}
