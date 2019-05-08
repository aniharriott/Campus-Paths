package graph;

import com.sun.source.doctree.StartElementTree;
import org.apache.commons.lang3.NotImplementedException;

import java.util.HashSet;
import java.util.Set;

/**
 * <b>GraphNode</b> is an mutable representation of a node on a Graph.
 *
 * <p>GraphNodes are represented by their label and associated in coming edges and out going
 * edges.
 *
 * <p>A GraphNode can have any number of edges associated with it, including zero.
 *
 * <p>Abstract Invariant:
 * A node cannot be null.
 */

public class GraphNode {

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
     * Constructs a new node with no edges
     *
     * @param l the label of this node
     * @spec.effects constructs a new node with no edges
     */
    public GraphNode(String l) {
        label = l;
        inComing = new HashSet<GraphEdge>();
        outGoing = new HashSet<GraphEdge>();
        checkRep();
    }

    /**
     * Constructs a node with edges
     *
     * @param l the label of this node
     * @param in the in coming edges of this node
     * @param out the outgoing edges of this node
     * @spec.effects constructs a new node with the given label and edges
     */
    public GraphNode(String l, Set<GraphEdge> in, Set<GraphEdge> out){
        this(l);
        // copy the set parameters into this node
        this.inComing.addAll(in);
        this.outGoing.addAll(out);
        checkRep();
    }

    /**
     * Adds a GraphEdge to this node that points to it.
     *
     * @param e the in coming GraphEdge to be added
     * @spec.requires e != null, e cannot ba a duplicate to this node
     * @spec.modifies this
     * @spec.effects adds an in coming GraphEdge to this node
     * @throws IllegalArgumentException if e == null or if e is a duplicate of an in coming edge
     * already associated with this node
     */
    public void addInComing(GraphEdge e) {
        if (e == null) {
            throw new IllegalArgumentException("edge cannot be null");
        }
        for (GraphEdge edge : this.inComing) {
            if (edge.equals(e)) {
                throw new IllegalArgumentException("edge cannot be a duplicate of this mode");
            }
        }
        this.inComing.add(e);
    }

    /**
     * Adds a GraphEdge to this node that points away from it.
     *
     * @param e the out going GraphEdge to be added
     * @spec.requires e != null, e cannot be a duplicate to this node
     * @spec.modifies this
     * @spec.effects adds an out going GraphEdge to this node
     * @throws IllegalArgumentException if e == null or if e is a duplicate of an out going edge
     * already associated with this node
     */
    public void addOutGoing(GraphEdge e) {
        if (e == null) {
            throw new IllegalArgumentException("edge cannot be null");
        }
        for (GraphEdge edge : this.inComing) {
            if (edge.equals(e)) {
                throw new IllegalArgumentException("edge cannot be a duplicate of this mode");
            }
        }
        this.outGoing.add(e);
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
        // sorted set? might need a compareTo for edges?
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

    /**
     * equals method and hashcode method
     */

    /** Throws an exception if the representation invariant is violated. */
    private void checkRep() {
        throw new NotImplementedException("checkRep not yet implemented");
    }
}
