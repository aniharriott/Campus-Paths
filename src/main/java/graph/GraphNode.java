package graph;

import org.apache.commons.lang3.NotImplementedException;
import java.util.Set;

/**
 * <b>GraphNode</b> is an mutable representation of a node on a Graph.
 *
 * <p>GraphNodes are represented a label and their child <b>GraphEdges</b>. A node is identifiable by its label
 * which must be unique within a single <b>Graph</b>.
 *
 * <p>A GraphNode can have any number of GraphEdges associated with it, including zero.
 */

public final class GraphNode {

    /** The label for this node */
    private String label;
    /** The GraphEdges that point to this node */
    Set<GraphEdge> inComing;
    /** The GraphEdges that point away from this node */
    Set<GraphEdge> outGoing;

    // Abstraction Function:
    //      for any GraphNode n,
    //          label = the label for this node
    //          inComing = all the edges that point to this node
    //          outGoing = all the edges that point away from this node
    // Representation Invariant:
    //      label != null
    //      inComing != null, for all indexes i in inComing inComing.get(i) != null
    //      outGoing != null, for all indexes i in outGoing outGoing.get(i) != null

    /**
     * @param l the label of the node to be constructed
     * @spec.effects constructs a new node with a label l and no edges
     */
    public GraphNode(String l) {
        throw new NotImplementedException("GraphNode constructor not yet implemented");
    }

    /** @spec.effects constructs a new node with a label l and edges e */
    public GraphNode(String l, Set<GraphEdge> e) {
        throw new NotImplementedException("GraphNode constructor not yet implemented");
    }

    /**
     * Adds a GraphEdge to this node that points to it
     *
     * @param e the in coming GraphEdge to be added
     * @spec.modifies this.inComing
     * @spec.effects
     */
    public void addInComing(GraphEdge e) {
        throw new NotImplementedException("addInComing not yet implemented");
    }

    public void addOutGoing(GraphEdge e) {
        throw new NotImplementedException("addOutGoing not yet implemented");
    }

    public Set<GraphNode> getChildren() {
        throw new NotImplementedException("getChildren not yet implemented");
    }

    public String getName() {
        throw new NotImplementedException("getName not yet implemented");
    }

    public Set<GraphEdge> getInComing() {
        throw new NotImplementedException("getInComing not yet implemented");
    }

    public Set<GraphEdge> getOutGoing() {
        throw new NotImplementedException("getOutGoing not yet implemented");
    }

    @Override
    public String toString() {
        throw new NotImplementedException("toString not yet implemented");
    }
}
