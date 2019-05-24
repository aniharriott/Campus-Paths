package graph;

/**
 * <b>GraphEdge</b> is an immutable representation of an edge on a <b>Graph</b>.
 *
 * <p>GraphEdges are represented by their label and associated source node and destination node.
 *
 * <p>A GraphEdge cannot have a null source node or destination node, but the node this edge
 * points to and from can be the same.
 *
 */

public final class GraphEdge<T, NodeType> {

    /** The label of this edge */
    private final T label;
    /** The node this edge points from (the source) */
    private final GraphNode<NodeType, T> source;
    /** The node the edge points to (the destination) */
    private final GraphNode<NodeType, T> destination;

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
     * Constructs a new GraphEdge
     *
     * @param l the label of the edge to be constructed
     * @param s the source node of the edge to be constructed
     * @param d the destination node of the edge to be constructed
     * @spec.requires l != null, s != null, and d != null
     * @spec.effects Constructs a new GraphEdge e. Adds itself as an in coming edge for GraphNode d,
     * and adds itself as an out going edge for GraphNode s.
     */
    public GraphEdge(T l, GraphNode<NodeType, T> s, GraphNode<NodeType, T> d) {
        this.label = l;
        this.source = s; //new GraphNode(s.getLabel(), s.getInComing(), s.getOutGoing());
        this.destination = d; //new GraphNode(d.getLabel(), d.getInComing(), d.getOutGoing());
        // add to out going edges of s
        //s.addOutGoing(this);
        // add to in coming edges of d
        //d.addInComing(this);
        checkRep();
    }

    /**
     * Gets the label of this GraphEdge.
     *
     * @return the label of this GraphEdge
     */
    public T getLabel() { return label; }

    /**
     * Gets the source of this GraphEdge.
     *
     * @return the source node of this GraphEdge
     */
    public GraphNode<NodeType, T> getSource() {
        return source;
    }

    /**
     * Gets the destination of this GraphEdge.
     *
     * @return the destination node of this GraphEdge
     */
    public GraphNode<NodeType, T> getDestination() {
        return destination;
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
        if (!(o instanceof GraphEdge<?, ?>)) {
            return false;
        }
        return this.label.equals(((GraphEdge<?, ?>) o).getLabel()) &&
                this.source.equals(((GraphEdge<?, ?>) o).getSource()) &&
                this.destination.equals(((GraphEdge<?, ?>) o).getDestination());
    }

    /**
     * Standard hashCode function.
     *
     * @return an int that all objects equal to this will also
     */
    @Override
    public int hashCode() {
        return source.hashCode() ^ label.hashCode();
    }

    /** Throws an exception if the representation invariant is violated. */
    private void checkRep() {
        assert label != null;
        assert source != null;
        assert destination != null;
    }
}
