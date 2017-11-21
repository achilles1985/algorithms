package com.company.datastructures.graphs.dijkstras;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DijkastrasAlgorithms {

    public static String findShortestPath(Node start, Node goal) {
        Set<Node> unsettled = new HashSet<>();
        Set<Node> settled = new HashSet<>();

        start.setDistance(0);
        unsettled.add(start);
        while (!unsettled.isEmpty()) {
            Node current = findNodeWithShortestDistance(unsettled);
            if (current.getData() == goal.getData()) {
                return buildPath(current);
            }
            unsettled.remove(current);
            for (Map.Entry<Node, Integer> adjacent: current.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacent.getKey();
                Integer adjacentEdge = adjacent.getValue();
                if (!settled.contains(adjacentNode)) {
                    if (adjacentNode.getDistance() > current.getDistance() + adjacentEdge) {
                        adjacentNode.setDistance(current.getDistance() + adjacentEdge);
                        adjacentNode.setPrevious(current);
                    }
                    unsettled.add(adjacentNode);
                }
            }
            settled.add(current);
        }

        return null;
    }

    private static String buildPath(Node current) {
        LinkedList<Node> path = new LinkedList<>();
        while(current != null) {
            path.addFirst(current);
            current = current.getPrevious();
        }

        return path.stream()
                .map(Node::getData)
                .map(String::valueOf)
                .collect(Collectors.joining("->"));
    }

    private static Node findNodeWithShortestDistance(Set<Node> nodes) {
        Node minNode = nodes.stream().findFirst().orElse(new Node(String.valueOf(Integer.MAX_VALUE)));
        for (Node node: nodes) {
            if (minNode.getDistance() > node.getDistance()) {
                minNode = node;
            }
        }

        return minNode;
    }

    public static void main(String[] args) {
        Graph graph = new Graph();

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");
        Node nodeG = new Node("G");
        Node nodeH = new Node("H");

        nodeA.addEdge(nodeB, 8);
        nodeA.addEdge(nodeC, 2);
        nodeA.addEdge(nodeD, 5);

        nodeB.addEdge(nodeA, 5);
        nodeB.addEdge(nodeF, 13);
        nodeB.addEdge(nodeD, 2);

        nodeD.addEdge(nodeA, 5);
        nodeD.addEdge(nodeB, 2);
        nodeD.addEdge(nodeF, 6);
        nodeD.addEdge(nodeG, 3);
        nodeD.addEdge(nodeE, 1);
        nodeD.addEdge(nodeC, 2);

        nodeC.addEdge(nodeA, 2);
        nodeC.addEdge(nodeD, 2);
        nodeC.addEdge(nodeE, 5);

        nodeE.addEdge(nodeC, 2);
        nodeE.addEdge(nodeD, 1);
        nodeE.addEdge(nodeG, 1);

        nodeF.addEdge(nodeB, 13);
        nodeF.addEdge(nodeD, 6);
        nodeF.addEdge(nodeG, 2);
        nodeF.addEdge(nodeH, 3);

        nodeH.addEdge(nodeF, 3);
        nodeH.addEdge(nodeG, 6);

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);
        graph.addNode(nodeG);
        graph.addNode(nodeH);

        System.out.println(String.format("From %s to %s: %s", nodeA.getData(), nodeF.getData(), findShortestPath(nodeA, nodeF)));
    }
}
