package graph;

import java.util.Set;
import java.util.HashSet;

/**
 * <b>Graph</b> represents a mutable directed graph represented by
 * a set of unique nodes.
 *
 * <p>A Graph can be empty but it cannot be null.
 *
 * <p>Abstract Invariant:
 * A graph cannot be null, and none of its nodes can be null.
 */

public class Graph {

    /** All the nodes in the graph */
    private Set<GraphNode> nodes;
    /** All the edges in the graph */
    private Set<GraphEdge> edges;
    /** boolean value used for testing levels */
    private static final boolean DEBUG = false;

    // Abstraction Function:
    //      for any Graph g,
    //          nodes = all the nodes in this graph
    //          edges = all the edges in this graph
    // Representation Invariant:
    //      nodes != null
    //          nodes cannot contain duplicates
    //          for all objects n in nodes, n != null
    //      edges != null
    //          edges cannot contain edges that point to or from nodes not
    //              contained in this graph
    //          for all objects e in edges, e != null

    /**
     * Constructs an empty graph.
     *
     * @spec.effects Constructs a new Graph g with no nodes.
     */
    public Graph() {
        nodes = new HashSet<GraphNode>();
        edges = new HashSet<GraphEdge>();
        checkRep();
    }

    /**
     * Adds an edge to this Graph.
     *
     * @param e the edge to be added to the graph
     * @spec.requires both nodes passed must already be contained in this graph, and
     * the edge to be added cannot be a duplicate
     * @spec.modifies this
     * @spec.effects adds an edge to this graph
     * @throws IllegalArgumentException if either node passed is not already contained in
     * this graph
     */
    public void addEdge(GraphEdge e) {
        checkRep();
        boolean added = false;
        for (GraphNode n : nodes) {
            if (e.getSource().equals(n)) {
                n.addOutGoing(e);
                added = true;
            }
            if (e.getDestination().equals(n)) {
                n.addInComing(e);
                added = true;
            }
        }
        if (!added) {
            throw new IllegalArgumentException("nodes must already be contained in this graph");
        }
        edges.add(e);
        checkRep();
    }

    /**
     * Adds a new GraphNode to this Graph.
     *
     * @param n the GraphNode to be added
     * @spec.requires n != null, n cannot equal any other node in this graph
     * @spec.modifies this
     * @spec.effects If this has nodes n0, n1, then this_post has nodes n0, n1, n
     * @throws IllegalArgumentException if n = null or n is already contained in this graph
     */
    public void addNode(GraphNode n) {
        checkRep();
        if (n == null) {
            throw new IllegalArgumentException("node cannot be null");
        }
        for (GraphNode node : nodes) {
            if (node.getLabel().equals(n.getLabel())) {
                throw new IllegalArgumentException("node cannot be a duplicate in this graph");
            }
        }
        nodes.add(n);
        edges.addAll(n.getInComing());
        edges.addAll(n.getOutGoing());
        checkRep();
    }

    /**
     * Deletes an edge from this graph.
     *
     * @param e the GraphEdge to be deleted
     * @spec.requires e is already contained in this graph
     * @spec.modifies this
     * @spec.effects deletes the edge from this graph
     */
    public void deleteEdge(GraphEdge e) {
        checkRep();
        for (GraphNode n : nodes) {
            Set<GraphEdge> in = n.getInComing();
            Set<GraphEdge> out = n.getOutGoing();
            if (in.contains(e)) {
                n.deleteEdge(e);
            } else if (out.contains(e)) {
                n.deleteEdge(e);
            }
        }
        edges.remove(e);
        checkRep();
    }

    /**
     * Deletes a node from this graph.
     *
     * @param n the node to be deleted
     * @spec.requires n is already contained in this graph
     * @spec.modifies this
     * @spec.effects deletes the node from this graph
     * @throws IllegalArgumentException if n is not contained in this graph
     */
    public void deleteNode(GraphNode n) {
        checkRep();
        if (!nodes.contains(n)){
            throw new IllegalArgumentException("node must be contained in this graph");
        } else {
            for (GraphEdge e : n.getInComing()) {
                deleteEdge(e);
            }
            for (GraphEdge e : n.getOutGoing()) {
                deleteEdge(e);
            }
            nodes.remove(n);
        }
        checkRep();
    }

    /**
     * Returns a set of all the nodes in this Graph in alphabetical order.
     *
     * @return a set of nodes equal to all the nodes in this graph
     */
    public Set<GraphNode> listNodes() {
        Set<GraphNode> n = new HashSet<GraphNode>();
        n.addAll(nodes);
        return n;
    }

    /**
     * Returns a set of all the edges in this Graph in alphabetical order.
     *
     * @return a set of edges equal to all the edges contained in this graph
     */
    public Set<GraphEdge> listEdges() {
        Set<GraphEdge> e = new HashSet<GraphEdge>();
        e.addAll(edges);
        return e;
    }

    /**
     * Returns the edges between the nodes.
     *
     * @param n1 the start node of these connections.
     * @param n2 the end node of these connections.
     * @spec.requires n1 and n2 are already contained within this graph
     * @return a set of edges that represent the connections between n1 to n2,
     * if they are not connected or either node is not already in the graph, returns null.
     */
    public Set<GraphEdge> findConnections(GraphNode n1, GraphNode n2) {
        if (!nodes.contains(n1) || !nodes.contains(n2) || !n1.getChildren().contains(n2)) {
            return null;
        }
        return n1.findEdges(n2);
    }

    /** Throws an exception if the representation invariant is violated. */
    private void checkRep() {
        if (DEBUG) {
            assert nodes != null;

            for (GraphNode n : nodes) {
                assert n != null;
            }
        }
    }
}
