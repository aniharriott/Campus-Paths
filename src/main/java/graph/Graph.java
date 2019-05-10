package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

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
    private List<GraphNode> nodes;
    /** boolean value used for testing levels */
    private static final boolean DEBUG = false;

    // Abstraction Function:
    //      for any Graph g,
    //          nodes = all the nodes in this graph
    // Representation Invariant:
    //      nodes != null
    //      for all indexes i, nodes.get(i) != null

    /**
     * Constructs an empty graph.
     *
     * @spec.effects Constructs a new Graph g with no nodes.
     */
    public Graph() {
        nodes = new ArrayList<GraphNode>();
        checkRep();
    }

    /**
     * Adds a new edge to this Graph.
     *
     * @param l the label of the edge to be added
     * @param n1 the source node of the edge to be added
     * @param n2 the destination node of the edge to be added
     * @spec.requires both nodes passed must already be contained in this graph, and
     * the edge to be added cannot be a duplicate
     * @spec.modifies this
     * @spec.effects Creates a new edge and adds it to this graph
     * @returns the edge created
     * @throws IllegalArgumentException if either node passed is not already contained in
     * this graph
     */
    public GraphEdge addEdge(String l, GraphNode n1, GraphNode n2) {
        checkRep();
        if(!nodes.contains(n1) || !nodes.contains(n2)) {
            throw new IllegalArgumentException("nodes must already be contained in this graph");
        }
        // create edge
        GraphEdge e = new GraphEdge(l, n1, n2);
        checkRep();
        return e;
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
            List<GraphEdge> in = n.getInComing();
            List<GraphEdge> out = n.getOutGoing();
            if (in.contains(e)) {
                n.deleteEdge(e);
            } else if (out.contains(e)) {
                n.deleteEdge(e);
            }
        }
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
    public List<GraphNode> listNodes() {
        List<GraphNode> n = new ArrayList<GraphNode>();
        n.addAll(nodes);
        Comparator<GraphNode> byLabel = Comparator.comparing(GraphNode::getLabel);
        n.sort(byLabel);
        return n;
    }

    /**
     * Returns a list of all the edges in this Graph in alphabetical order.
     *
     * @return a list of edges equal to all the edges contained in this graph
     */
    public List<GraphEdge> listEdges() {
        List<GraphEdge> edges = new ArrayList<GraphEdge>();
        for (GraphNode n : nodes) {
            for (GraphEdge e : n.getInComing()) {
                if (!edges.contains(e)) {
                    edges.add(e);
                }
            }
            for (GraphEdge e : n.getOutGoing()) {
                if (!edges.contains(e)) {
                    edges.add(e);
                }
            }
        }
        Comparator<GraphEdge> byLabel = Comparator.comparing(GraphEdge::getLabel);
        edges.sort(byLabel);
        return edges;
    }

    /**
     * Returns the edge (first alphabetically) between the nodes.
     *
     * @param n1 the start node of this connection.
     * @param n2 the end node of this connection
     * @spec.requires n1 and n2 are already contained within this graph
     * @return an edge that represents the first connection (alphabetically) between n1 to n2,
     * if they are not connected or either node is not already in the graph, returns null.
     */
    public GraphEdge findConnection(GraphNode n1, GraphNode n2) {
        if (!nodes.contains(n1) || !nodes.contains(n2) || !n1.getChildren().contains(n2)) {
            return null;
        }
        return n1.findEdges(n2).get(0);
    }

    /** Throws an exception if the representation invariant is violated. */
    private void checkRep() {
        assert nodes != null;
        if (DEBUG) {
            for (GraphNode n : nodes) {
                assert n != null;
            }
        }
    }
}
