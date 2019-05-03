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
 *
 * Specification fields:
 * @spec.specfield label : String // The label of this edge.
 * @spec.specfield source : GraphNode  // The GraphNode that this edge points from.
 * @spec.specfield destination : GraphNode  // The GraphNode that this edge points to.
 *
 * Abstract Invariant:
 * An edge cannot have a null label, source, or destination.
 */

public final class GraphEdge {

    /** The label of this edge */
    private final String label;
    /** The node this edge points from (the source) */
    private final GraphNode source;
    /** The node the edge points to (the destination) */
    private final GraphNode destination;

    // Abstraction Function:
    //      for any GraphEdge e,
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
     * @throws IllegalArgumentException if l, s, or d is null.
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

    /**
     * Standard equality operation.
     *
     * @param o the object to be compared for equality
     * @return true if 'o' is an instance of a GraphEdge and 'this' and 'o' have the same
     *     label, source, and destination values.
     */
    @Override
    public boolean equals(Object o) {
        throw new NotImplementedException("equals not yet implemented");
    }

    /**
     * Standard hashCode function.
     *
     * @return an int that all objects equal to this will also
     */
    @Override
    public int hashCode() {
        throw new NotImplementedException("hashCode not yet implemented");
    }

    /** Throws an exception if the representation invariant is violated. */
    private void checkRep() {
        throw new NotImplementedException("checkRep not yet implemented");
    }
}
