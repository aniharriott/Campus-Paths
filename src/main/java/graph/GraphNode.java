package graph;

import java.util.Set;

/**
 * <b>GraphNode</b> is an mutable representation of a node on a Graph.
 *
 * <p>GraphNodes are represented a label and their child <b>GraphEdges</b>. A node is identifiable by its label
 * which must be unique within a single <b>Graph</b>.
 *
 * <p>A GraphNode can have any number of GraphEdges, including zero.
 */

public final class GraphNode {

    /** The label for this node */
    private String label;
    /** The edges that this node is a parent of */ // or a child of?? or not at all??
    Set<GraphEdge> inComing;
    Set<GraphEdge> outGoing;

    /**
     * @param l the label of the node to be constructed
     * @spec.effects constructs a new node with a label l and no edges
     */
    public GraphNode(String l) {

    }

    /** @spec.effects constructs a new node with a label l and edges e */
    public GraphNode(String l, Set<GraphEdge> e) {

    }

    /** @spec.effects constructs a new node with a label l and one edge e */
    public GraphNode(String l, GraphEdge e) {

    }

    public Set<GraphNode> getChildren() {
        return null;
    }

    public String getName() {
        return null;
    }

    public Set<GraphEdge> getEdges() {
        return null;
    }

    public void addEdge() {

    }

    @Override
    public String toString() {
        return null;
    }
}
