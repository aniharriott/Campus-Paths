package graph;

import org.apache.commons.lang3.NotImplementedException;
import java.util.Set;

/**
 * <b>GraphNode</b> is an mutable representation of a node on a Graph.
 *
 * <p>GraphNodes are represented by their label, their in coming <b>GraphEdges</b>, and their out going
 * <b>GraphEdges</b>. A node is identifiable by its label which must be unique within a
 * single <b>Graph</b>.
 *
 * <p>A GraphNode can have any number of GraphEdges associated with it, including zero.
 *
 * @spec.specfield label : String // The label of this node
 * @spec.specfield inComing : Set  // The GraphEdges that point to this node in alphabetical order
 * @spec.specfield outGoing : Set  // The GraphEdges that point away from this node in alphabetical order
 *
 * <p>Abstract Invariant:
 * A node cannot be null.
 */

public final class GraphNode {

    /** The label of this node */
    private final String label;
    /** The GraphEdges that point to this node in alphabetical order */
    private Set<GraphEdge> inComing;
    /** The GraphEdges that point away from this node in alphabetical order */
    private Set<GraphEdge> outGoing;

    // Abstraction Function:
    //      for any GraphNode n,
    //          label = the label of this node
    //          inComing = all the edges that point to this node
    //          outGoing = all the edges that point away from this node
    // Representation Invariant:
    //      label != null
    //      inComing != null,
    //          for all indexes i in inComing, inComing.get(i) != null
    //          inComing is in alphabetical order
    //      outGoing != null,
    //          for all indexes i in outGoing, outGoing.get(i) != null
    //          outGoing is in alphabetical order

    /**
     * @param l the label of this node
     * @spec.effects constructs a new node with no edges
     */
    public GraphNode(String l) {
        throw new NotImplementedException("GraphNode constructor not yet implemented");
    }

    /**
     * Adds a GraphEdge to this node that points to it.
     *
     * @param e the in coming GraphEdge to be added
     * @spec.requires e != null, e cannot ba a duplicate to this node
     * @spec.modifies this.inComing
     * @spec.effects adds an in coming GraphEdge to this node
     */
    public void addInComing(GraphEdge e) {
        throw new NotImplementedException("addInComing not yet implemented");
    }

    /**
     * Adds a GraphEdge to this node that points away from it.
     *
     * @param e the out going GraphEdge to be added
     * @spec.requires e != null, e cannot be a duplicate to this node
     * @spec.modifies this.outGoing
     * @spec.effects adds an out going GraphEdge to this node
     */
    public void addOutGoing(GraphEdge e) {
        throw new NotImplementedException("addOutGoing not yet implemented");
    }

    /**
     * Deletes a GraphEdge from this node, either in coming or outgoing
     *
     * @param e the GraphEdge to be deleted
     * @spec.requires e must already be contained in this node
     * @spec.modifies this
     * @spec.effects deletes the GraphEdge e from this node, either in inComing or outGoing
     * or both
     */
    public void deleteEdge(GraphEdge e) {
        throw new NotImplementedException("deleteEdge not yet implemented");
    }

    /**
     * Returns a set of all the GraphNodes that are children of this in alphabetical order.
     *
     * @return a set of GraphNodes that contains all the children of this node in alphabetical
     * order
     */
    public Set<GraphNode> getChildren() {
        throw new NotImplementedException("getChildren not yet implemented");
    }

    /**
     * Returns a set of all the GraphNodes that are parents of this in alphabetical order.
     *
     * @return a set of GraphNodes that contains all the parents of this node in alphabetical
     * order
     */
    public Set<GraphNode> getParents() {
        throw new NotImplementedException("getParents not yet implemented");
    }

    /**
     * Returns the in coming edges of this node.
     *
     * @return a set of GraphEdges that is equal to this.inComing
     */
    public Set<GraphEdge> getInComing() {
        throw new NotImplementedException("getInComing not yet implemented");
    }

    /**
     * Returns the out going edges of this node.
     *
     * @return a set of GraphEdges that is equal to this.outGoing
     */
    public Set<GraphEdge> getOutGoing() {
        throw new NotImplementedException("getOutGoing not yet implemented");
    }

    /**
     * Returns the label of this node.
     *
     * @return a String 'label' that is equal to this.label
     */
    public String getLabel() {
        throw new NotImplementedException("getLabel not yet implemented");
    }

    /**
     * Returns an edge between this and another node.
     *
     * @param other the GraphNode to find an edge to
     * @spec.requires GraphNode other is a child of this
     * @return the GraphEdge that connects this GraphNode (parent) and another GraphNode (child)
     * and is the first alphabetically if there are multiple.
     */
    public GraphEdge findEdge(GraphNode other) {
        throw new NotImplementedException("findEdge not yet implemented");
    }

    /** Throws an exception if the representation invariant is violated. */
    private void checkRep() {
        throw new NotImplementedException("checkRep not yet implemented");
    }
}
