package com.company.datastructures.graphs.dijkstras;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private List<Node> nodes = new ArrayList<>();

    public void addNode(Node node) {
        nodes.add(node);
    }
}
