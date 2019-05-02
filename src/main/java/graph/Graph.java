package graph;

import java.util.Set;
import java.util.List;
import org.apache.commons.lang3.NotImplementedException;

/**
 * <b>Graph</b> represents a mutable directed graph represented by a
 * a set of unique <b>GraphNodes</b>.
 *
 * <p>A Graph can be empty but it cannot be null.
 */

public final class Graph {

    /** All the nodes in the graph in alphabetical order */
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

    /**
     * Adds a new GraphEdge to this Graph
     *
     * @param n1 the source node of the GraphEdge to be added
     * @param n2 the destination node of the GraphEdge to be added
     * @param l the label of the GraphEdge to be added
     * @spec.modifies n1 and n2
     * @spec.effects Creates a new GraphEdge from the given parameters and associates it with the
     * given nodes
     * @throws IllegalArgumentException if either GraphNode passed is not already contained
     * within this Graph, or if the edge already exists in this graph
     */
    public void addEdge(GraphNode n1, GraphNode n2, String l) {
        throw new NotImplementedException("addEdge not yet implemented");
    }

    /**
     * Adds a new GraphNode to this Graph
     *
     * @param n the GraphNode to be added
     * @spec.requires n != null, n.label cannot equal any other label in this graph
     * @spec.modifies this.nodes
     * @spec.effects If this.nodes = [n0, n1], then this_post.nodes = [n0, n1, n]
     * @throws IllegalArgumentException if the label of n equals the label of a node
     * already contained in this graph
     */
    public void addNode(GraphNode n) {
        throw new NotImplementedException("addNode not yet implemented");
    }

    /**
     * Returns a set of all the GraphNodes in this Graph in alphabetical order.
     *
     * @return a Set</GraphNode> equal to this.nodes
     */
    public Set<GraphNode> listNodes() {
        throw new NotImplementedException("listNodes not yet implemented");
    }

    /**
     * Returns a list of all the GraphEdges in this Graph in alphabetical order.
     *
     * @return a List</GraphNode> equal to all the edges contained in this graph
     */
    private List<GraphEdge> listEdges() {
        throw new NotImplementedException("listEdges not yet implemented");
    }

    /**
     * Returns a set of GraphNodes representing the "shortest" path from one node to another.
     * "Shortest" refers to the fewest number of GraphEdges between.
     *
     * @param n1 the start node of this path
     * @param n2 the end node of this path
     * @return a Set</GraphNode> that represents the shortest path from n1 to n2.
     * @throws IllegalArgumentException if n1 or n2 is not contained within the this graph.
     */
    public Set<GraphNode> findPath(GraphNode n1, GraphNode n2) {
        throw new NotImplementedException("findPath not yet implemented");
    }

    /** Throws an exception if the representation invariant is violated. */
    private void checkRep() {
        throw new NotImplementedException("checkRep not yet implemented");
    }
}
