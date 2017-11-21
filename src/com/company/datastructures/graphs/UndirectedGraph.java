package com.company.datastructures.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

public class UndirectedGraph {

    private Map<Integer, Node> nodes = new HashMap<>();

    private static class Node {
        private int data;
        private boolean visited;
        private int level;
        private Node parent;
        private List<Node> children = new ArrayList<>();

        public Node(int data) {
            this.data = data;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            return data == node.data;

        }

        @Override
        public int hashCode() {
            return data;
        }

        public int getData() {
            return data;
        }
    }

    public void addEdge(int source, int destination) {
        Node sourceNode = getNode(source);
        Node destinationNode = getNode(destination);

        sourceNode.children.add(destinationNode);
        destinationNode.children.add(sourceNode);
    }

    private Node getNode(int id) {
        if (nodes.get(id) == null) {
            Node node = new Node(id);
            nodes.put(id, node);
            return node;
        }
        return nodes.get(id);
    }

    public List<Node> getNodeChildren(int vertex) {
        return nodes.get(vertex).children;
    }

    public void traverseDFS(Node source) {
        Stack<Node> stack = new Stack<>();
        source.visited = true;
        stack.push(source);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.print(node.data + " ");
            for (Node child : node.children) {
                if (!child.visited) {
                    child.visited = true;
                    stack.push(child);
                }
            }
        }
        System.out.println();
    }

    public void traverseBFS(Node source) {
        Queue<Node> queue = new LinkedList<>();
        source.visited = true;
        queue.add(source);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.print(node.data + " ");
            for (Node child : node.children) {
                if (!child.visited) {
                    child.visited = true;
                    queue.add(child);
                }
            }
        }
        System.out.println();
    }

    public boolean searchВFS(Node source, Node destination) {
        Queue<Node> queue = new LinkedList<>();
        source.visited = true;
        queue.add(source);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (node.data == destination.data) {
                return true;
            }
            for (Node child : node.children) {
                if (!child.visited) {
                    child.visited = true;
                    queue.add(child);
                }
            }
        }

        return false;
    }

    public String printBFSPathTo(Node start, Node goal) {
        StringBuilder builder = new StringBuilder();
        printBFSPathTo(start, goal, builder);

        return builder.toString();
    }

    private void printBFSPathTo(Node start, Node goal, StringBuilder builder) {
        Queue<Node> queue = new LinkedList<>();
        start.visited = true;
        queue.add(start);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            builder.append(node.data).append(", ");
            if (node.data == goal.data) {
                return;
            }
            for (Node child : node.children) {
                if (!child.visited) {
                    child.visited = true;
                    queue.add(child);
                }
            }
        }
    }

    public String printDFSPathTo(Node start, Node goal) {
        StringBuilder builder = new StringBuilder();
        printDFSPathTo(start, goal, builder);

        return builder.toString();
    }

    private void printDFSPathTo(Node source, Node goal, StringBuilder builder) {
        Stack<Node> stack = new Stack<>();
        source.visited = true;
        stack.push(source);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            builder.append(node.data).append(",");
            if (node.data == goal.data) {
                return;
            }
            for (Node child : node.children) {
                if (!child.visited) {
                    child.visited = true;
                    stack.push(child);
                }
            }
        }
    }

    public String printAllPathsBFSS(Node start, Node goal) {
        StringBuilder builder = new StringBuilder();
        Queue<Node> queue = new LinkedList<>();
        start.visited = true;
        queue.add(start);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            builder.append(node.data).append(" ");
            if (node.data == goal.data) {
                return builder.toString();
            }
            for (Node child : node.children) {
                if (!child.visited) {
                    child.visited = true;
                    queue.add(child);
                }
            }
        }

        return "path not found";
    }

    private String gueueToString(Queue<Node> queue) {
        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()) {
            builder.append(queue.remove().data).append(", ");
        }

        return builder.toString();
    }

    public void traverseDFSRecursively(Node node) {
        if (node == null) {
            return;
        }
        node.visited = true;
        System.out.print(node.data + " ");
        for (Node item : node.children) {
            if (!item.visited) {
                traverseDFSRecursively(item);
            }
        }
    }

    public void resetVisited() {
        nodes.values().stream().forEach(node -> node.visited = false);
    }

    public String findTheShortestPathBFS(int start, int goal) {
        Map<Node, Node> nodeToParentMap = new HashMap();
        Queue<Node> queue = new LinkedList<>();

        Node from = getNode(start);
        Node to = getNode(goal);
        nodeToParentMap.put(from, null);
        from.visited = true;
        queue.add(from);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (node.data == to.data) {
                return constructPath(nodeToParentMap, to);
            }
            for (Node child : node.children) {
                if (!child.visited) {
                    child.parent = node;
                    nodeToParentMap.put(child, node);
                    child.visited = true;
                    queue.add(child);
                }
            }
        }

        return null;
    }

    private String constructPath(Map<Node, Node> paths, Node goal) {
        List<Node> result = new LinkedList<>();
        Node parent = paths.get(goal);
        result.add(goal);
        result.add(parent);
        while (parent != null) {
            parent = paths.get(parent);
            result.add(parent);
        }
        Collections.reverse(result);
        return result.stream()
                .filter(Objects::nonNull)
                .map(Node::getData)
                .map(String::valueOf)
                .collect(Collectors.joining("->"));
    }

    public List<List<String>> findAllPossiblePaths(int start, int goal) {
        Map<Node, Node> nodeToParentMap = new HashMap();
        Queue<Node> queue = new LinkedList<>();

        Node from = getNode(start);
        Node to = getNode(goal);
        from.visited = true;
        from.parent = null;
        nodeToParentMap.put(from, null);

        queue.add(from);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (node.data == to.data) {
                return constructPaths(nodeToParentMap);
            }
            for (Node child : node.children) {
                if (!child.visited) {
                    child.level = node.level + 1;
                    child.parent = node;
                    child.visited = true;
                    nodeToParentMap.put(child, node);
                    queue.add(child);
                }
            }

        }

        return null;
    }

    private List<List<String>> constructPaths(Map<Node, Node> nodeToParentMap) {
        List<List<String>> paths = new LinkedList<>();
        for (Map.Entry<Node, Node> entry : nodeToParentMap.entrySet()) {
            LinkedList path = new LinkedList();
            Node node = entry.getKey();
            while (node != null) {
                path.addFirst(String.valueOf(node.data));
                node = node.parent;
            }
            paths.add(path);
        }

        return paths;
    }

    public static void main(String[] args) {
        UndirectedGraph graph = new UndirectedGraph();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);
        graph.addEdge(3, 9);
        graph.addEdge(4, 5);
        graph.addEdge(4, 7);
        graph.addEdge(2, 6);
        graph.addEdge(2, 8);

        Node source = graph.nodes.get(1);
        Node destination = new Node(4);
        graph.resetVisited();
        boolean found = graph.searchВFS(source, destination);
        System.out.println(String.format("Is node %s found in path: %s", destination.data, found));

        System.out.println("DFS");
        graph.resetVisited();
        graph.traverseDFS(source);

        System.out.println("BFS");
        graph.resetVisited();
        graph.traverseBFS(source);

        System.out.println("DFS recursively");
        graph.resetVisited();
        graph.traverseDFSRecursively(source);

        graph.resetVisited();
        String path = graph.printAllPathsBFSS(source, destination);
        System.out.println();
        System.out.println(String.format("Print path from %s to %s. Path: %s", source.data, destination.data, path));

        graph.resetVisited();
        System.out.println(String.format("Find shortest path from %s to %s", source.data, 5));
        System.out.println(graph.findTheShortestPathBFS(4, 8));

        graph.resetVisited();
        List<List<String>> paths = graph.findAllPossiblePaths(1, 6);
        paths.forEach(list ->  {
            System.out.print(list.stream().collect(Collectors.joining("->")));
            System.out.println();
            return;
        });
    }
}

