package graph;

import java.util.Set;
import java.util.List;
import org.apache.commons.lang3.NotImplementedException;

/**
 * <b>Graph</b> represents a mutable directed graph represented by
 * a set of unique <b>GraphNodes</b>.
 *
 * <p>A Graph can be empty but it cannot be null.
 *
 * Specification fields:
 * @spec.specfield nodes : Set  // The GraphNode contained in this graph in alphabetical order.
 *
 * Abstract Invariant:
 * A graph cannot be null, and none of its nodes can be null.
 */

public final class Graph {

    /** All the nodes in the graph in alphabetical order */
    private Set<GraphNode> nodes;

    // Abstraction Function:
    //      for any Graph g,
    //          nodes = all the nodes in this graph
    // Representation Invariant:
    //      nodes != null
    //      nodes is in sorted alphabetical order
    //      for all indexes i, nodes.get(i) != null

    /**
     * @spec.effects Constructs a new Graph g, with g.nodes = empty
     */
    public Graph() {
        throw new NotImplementedException("Graph constructor not yet implemented");
    }

    /**
     * Adds a new GraphEdge to this Graph.
     *
     * @param l the label of the GraphEdge to be added
     * @param n1 the source node of the GraphEdge to be added
     * @param n2 the destination node of the GraphEdge to be added
     * @spec.requires both GraphNodes passed cannot already be contained in this graph, and
     * the edge to be added cannot be a duplicate
     * @spec.modifies n1 and n2
     * @spec.effects Creates a new GraphEdge from the given parameters and associates it with the
     * given nodes
     */
    public void addEdge(String l, GraphNode n1, GraphNode n2) {
        throw new NotImplementedException("addEdge not yet implemented");
    }

    /**
     * Adds a new GraphNode to this Graph.
     *
     * @param n the GraphNode to be added
     * @spec.requires n != null, n.label cannot equal any other label in this graph
     * @spec.modifies this.nodes
     * @spec.effects If this.nodes = [n0, n1], then this_post.nodes = [n0, n1, n]
     */
    public void addNode(GraphNode n) {
        throw new NotImplementedException("addNode not yet implemented");
    }

    /**
     * Deletes an edge from this graph.
     *
     * @param e the GraphEdge to be deleted
     * @spec.requires e is already contained in this graph
     * @spec.modifies e.source and e.destination
     * @spec.effects deletes the edge from this graph
     */
    public void deleteEdge(GraphEdge e) {
        throw new NotImplementedException("deleteEdge not yet implemented");
    }

    /**
     * Deletes a node from this graph.
     *
     * @param n the GraphNode to be deleted
     * @spec.requires n is already contained in this graph
     * @spec.modifies this.nodes
     * @spec.effects deletes the node from this graph
     */
    public void deleteNode(GraphNode n) {
        throw new NotImplementedException("deleteNode not yet implemented");
    }

    /**
     * Returns a set of all the GraphNodes in this Graph in alphabetical order.
     *
     * @return a set of GraphNodes equal to this.nodes
     */
    public Set<GraphNode> listNodes() {
        throw new NotImplementedException("listNodes not yet implemented");
    }

    /**
     * Returns a list of all the GraphEdges in this Graph in alphabetical order.
     *
     * @return a list of GraphEdges equal to all the edges contained in this graph
     */
    public List<GraphEdge> listEdges() {
        throw new NotImplementedException("listEdges not yet implemented");
    }

    /**
     * Returns a list of GraphNodes representing the "shortest" path from one node to another.
     * "Shortest" refers to the fewest number of GraphEdges between.
     *
     * @param n1 the start node of this path
     * @param n2 the end node of this path
     * @spec.requires n1 and n2 are already contained within this graph
     * @return a list of GraphNodes that represents the shortest path from n1 to n2,
     * including n1 and n2.
     */
    public List<GraphNode> findPath(GraphNode n1, GraphNode n2) {
        throw new NotImplementedException("findPath not yet implemented");
    }

    /** Throws an exception if the representation invariant is violated. */
    private void checkRep() {
        throw new NotImplementedException("checkRep not yet implemented");
    }
}
