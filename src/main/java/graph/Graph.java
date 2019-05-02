package graph;

import java.util.Set;
import org.apache.commons.lang3.NotImplementedException;

/**
 * <b>Graph</b> represents a mutable directed graph represented by a
 * a set of <b>GraphNodes</b>.
 *
 * <p>A Graph can be empty but it cannot be null.
 */

public final class Graph {

    /** All the nodes in the graph in alphabetical order*/
    private final Set<GraphNode> nodes;

    // Abstraction Function:
    //      for any Graph g,
    //          nodes = all the nodes in this graph
    // Representation Invariant:
    //      nodes != null
    //      nodes is in sorted alphabetical order
    //      for all indexes i, nodes.get(i) != null

    /**
     * @param n the set of GraphNodes of the graph to be constructed
     * @spec.requires n != null
     * @spec.requires n is in sorted alphabetical order
     * @spec.effects Constructs a new Graph g, with g.nodes = n
     */
    public Graph(Set<GraphNode> n) {
        throw new NotImplementedException("Graph constructor not yet implemented");
    }

    /**
     * @spec.effects Constructs a new Graph g, with g.nodes = empty
     */
    public Graph() {
        throw new NotImplementedException("Graph constructor not yet implemented");
    }

    // SPEC EFFECTS? DUPLICATE EDGE?
    /**
     * Adds a new GraphEdge to this Graph
     *
     * @param n1 the source node of the GraphEdge to be added
     * @param n2 the destination node of the GraphEdge to be added
     * @param l the label of the GraphEdge to be added
     * @spec.effects Creates a new GraphEdge from the given parameters
     * @throws IllegalArgumentException if either GraphNode passed is not
     * already contained within this Graph
     */
    public void addEdge(GraphNode n1, GraphNode n2, String l) {
        throw new NotImplementedException("addEdge not yet implemented");
    }

    // WHAT IF A DUPLICATE NODE?
    /**
     * Adds a new GraphNode to this Graph
     *
     * @param n the GraphNode to be added
     * @spec.requires n != null
     * @spec.modifies this
     * @spec.effects If this.nodes = [n0, n1], then this_post.nodes = [n0, n1, n]
     */
    public void addNode(GraphNode n) {
        throw new NotImplementedException("addNode not yet implemented");
    }

    /**
     * Returns a set of all the GraphNodes in this Graph
     *
     * @return a Set</GraphNode> equal to this.nodes
     */
    public Set<GraphNode> listNodes() {
        throw new NotImplementedException("listNodes not yet implemented");
    }

    /**
     * Returns a set of ??
     *
     * @param n1
     * @param n2
     * @return
     */
    public Set<GraphEdge> findPath(GraphNode n1, GraphNode n2) {
        throw new NotImplementedException("findPath not yet implemented");
    }

}
