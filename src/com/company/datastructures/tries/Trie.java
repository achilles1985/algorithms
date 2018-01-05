package com.company.datastructures.tries;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

public class Trie {

    private Node root = new Node();

    public static class Node {
        private static final int ALPHABET_CHARS = 26;

        private Node[] children = new Node[ALPHABET_CHARS];
        private boolean isEndOfWord;
    }

    public void add(String word) {
        Node current = root;
        int i;
        for (i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (current.children[index] == null) {
                current.children[index] = new Node();
            }
            current = current.children[index];
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
        Node current = root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (current.children[index] == null) {
                return null;
            }
            current = current.children[index];
        }

        Node[] children = current.children;
        List<String> result = new ArrayList<>();
        for (int i = 0; i < children.length; i++) {
            Node node = children[i];
            if (node != null) {
                String word = buildWord(node, i);
                result.add(word);
/*                StringBuilder builder = new StringBuilder();
                while(node != null && !node.isEndOfWord) {
                    int code = i + 'a';
                    builder.append((char) code);
                    node = node.children[i];
                }
                result.add(builder.toString());*/
            }
        }

        return result;
    }

    private String buildWord(Node node, int index) {
        return buildWord(node, index, "");
    }

    private String buildWord(Node node, int index, String word) {
        if (node == null || node.isEndOfWord) {
            return word;
        }

        int code = index + 'a';
        return buildWord(node.children[index], index, word + valueOf((char) code));
    }

    public void print() {

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
        trie.add("amanda");

        System.out.println(trie.exists("aman"));
        String prefix = "tom";
        List<String> result = trie.findAll("tom");
        System.out.println(String.format("Found for prefix %s: ", prefix, result));
    }
}
