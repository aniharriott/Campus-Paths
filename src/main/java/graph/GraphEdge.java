package graph;

import org.apache.commons.lang3.NotImplementedException;

/**
 * <b>GraphEdge</b> is an immutable representation of an edge on a <b>Graph</b>.
 *
 * <p>GraphEdges are represented a label, their source <b>GraphNode</b>, and their destination
 * <b>GraphNode</b>. GraphEdges can have the same label as other GraphEdges in a single graph,
 * but they cannot also have the same source AND destination node.
 *
 * <p>A GraphEdge cannot have a null source or destination node, but source and destination
 * can be the same.
 */

public final class GraphEdge {

    /** The label of this edge */
    private final String label;
    /** The node this edge points from (the source) */
    private final GraphNode source;
    /** The node the edge points to (the destination) */
    private final GraphNode destination;

    // Abstraction Function:
    //      For any GraphEdge e,
    //          label = the label of this edge
    //          source = the GraphNode this edge points away from
    //          destination = the GraphNode this edge points to
    // Representation Invariant:
    //      label != null
    //      source != null
    //      destination != null

    /**
     * @param l the label of the edge to be constructed
     * @param s the source node of the edge to be constructed
     * @param d the destination node of the edge to be constructed
     * @spec.requires l != null, s != null, and d != null
     * @spec.effects Constructs a new GraphEdge e, with e.label = l, e.source = s, and
     * e.destination = d
     */
    public GraphEdge(String l, GraphNode s, GraphNode d) {
        throw new NotImplementedException("GraphEdge constructor not yet implemented");
    }

    /**
     * Gets the label of this GraphEdge.
     *
     * @return the label of this GraphEdge
     */
    public String getLabel() {
        throw new NotImplementedException("getLabel not yet implemented");
    }

    /**
     * Gets the source of this GraphEdge.
     *
     * @return the source of this GraphEdge
     */
    public GraphNode getSource() {
        throw new NotImplementedException("getSource not yet implemented");
    }

    /**
     * Gets the destination of this GraphEdge.
     *
     * @return the destination of this GraphEdge
     */
    public GraphNode getDestination() {
        throw new NotImplementedException("getDestination not yet implemented");
    }

}
