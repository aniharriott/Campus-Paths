package graph;

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
 * A node cannot be null, and none of its edges can be null.
 */

public class GraphNode<T, EdgeType> {

    /** The label of this node */
    private final T label;
    /** The edges that point to this node in alphabetical order */
    //private Set<GraphEdge<EdgeType, T>> inComing;
    /** The edges that point away from this node in alphabetical order */
    private Set<GraphEdge<EdgeType, T>> outGoing;
    /** boolean value used for testing levels */
    private static final boolean DEBUG = false;

    // Abstraction Function:
    //      for any GraphNode n,
    //          label = the label of this node
    //          inComing = all the edges that point to this node
    //          outGoing = all the edges that point away from this node
    // Representation Invariant:
    //      label != null
    //      inComing != null,
    //          for all objects i in inComing, i != null
    //          inComing does not contain duplicate edges
    //      outGoing != null,
    //          for objects i in outGoing, i != null
    //          outGoing does not contain duplicate edges

    /**
     * Constructs a new node with no edges
     *
     * @param l the label of this node
     * @spec.effects constructs a new node with no edges
     */
    public GraphNode(T l) {
        label = l;
        //inComing = new HashSet<GraphEdge<EdgeType, T>>();
        outGoing = new HashSet<GraphEdge<EdgeType, T>>();
        checkRep();
    }

    /**
     * Constructs a node with edges
     *
     * @param l the label of this node
     * @param in the in coming edges of this node
     * @param out the outgoing edges of this node
     * @spec.requires no duplicate edges within in or within out
     * @spec.effects constructs a new node with the given label and edges
     */
    //public GraphNode(T l, Set<GraphEdge<EdgeType, T>> in, Set<GraphEdge<EdgeType, T>> out){
    //    this(l);
        //copy the List parameters into this node
    //    this.inComing.addAll(in);
    //    this.outGoing.addAll(out);
    //    checkRep();
    //}

    /**
     * Adds a GraphEdge to this node that points to it.
     *
     * @param e the in coming GraphEdge to be added
     * @spec.requires e != null, e cannot ba a duplicate to this node
     * @spec.modifies this
     * @spec.effects adds an in coming GraphEdge to this node, a duplicate will not be added
     * @throws IllegalArgumentException if e == null
     */
    //public void addInComing(GraphEdge<EdgeType, T> e) {
    //    checkRep();
    //    if (e == null) {
     //       throw new IllegalArgumentException("edge cannot be null");
    //    }
     //   this.inComing.add(e);
     //   checkRep();
    //}

    /**
     * Adds a GraphEdge to this node that points away from it.
     *
     * @param e the out going GraphEdge to be added
     * @spec.requires e != null, e cannot be a duplicate to this node
     * @spec.modifies this
     * @spec.effects adds an out going GraphEdge to this node, a duplicate will not be added
     * @throws IllegalArgumentException if e == null
     */
    public void addOutGoing(GraphEdge<EdgeType, T> e) {
        checkRep();
        if (e == null) {
            throw new IllegalArgumentException("edge cannot be null");
        }
        this.outGoing.add(e);
        checkRep();
    }

    /**
     * Deletes an edge from this node, either in coming or outgoing
     *
     * @param e the edge to be deleted
     * @spec.requires e must already be contained in this node
     * @spec.modifies this
     * @spec.effects deletes the edge e from this node, either in in coming or out going
     * or both
     */
    public void deleteEdge(GraphEdge<EdgeType, T> e) {
        checkRep();
        // remove if in in coming edges
        //inComing.remove(e);
        // remove if in out going edges
        outGoing.remove(e);
        checkRep();
    }

    /**
     * Returns a set of all the nodes that are children of this.
     *
     * @return a set of nodes that contains all the children of this node
     */
    public Set<GraphNode<T, EdgeType>> getChildren() {
        Set<GraphNode<T, EdgeType>> children = new HashSet<GraphNode<T, EdgeType>>();
        for (GraphEdge<EdgeType, T> e : outGoing) {
            children.add(e.getDestination());
        }
        return children;
    }

    /**
     * Returns a set of all the nodes that are parents of this.
     *
     * @return a set of nodes that contains all the parents of this node
     */
    //public Set<GraphNode<T, EdgeType>> getParents() {
     //   Set<GraphNode<T, EdgeType>> parents = new HashSet<GraphNode<T, EdgeType>>();
    //    for (GraphEdge<EdgeType, T> e : inComing) {
    //        parents.add(e.getSource());
     //   }
     //   return parents;
   // }

    /**
     * Returns the in coming edges of this node.
     *
     * @return a set of edges that is equal to the in coming edges of this node
     */
   // public Set<GraphEdge<EdgeType, T>> getInComing() {
   //     Set<GraphEdge<EdgeType, T>> returnSet = new HashSet<GraphEdge<EdgeType, T>>();
   //     returnSet.addAll(inComing);
   //     return returnSet;
   // }

    /**
     * Returns the out going edges of this node.
     *
     * @return a set of edges that is equal to the out going edges of this node
     */
    public Set<GraphEdge<EdgeType, T>> getOutGoing() {
        Set<GraphEdge<EdgeType, T>> returnSet = new HashSet<GraphEdge<EdgeType, T>>();
        returnSet.addAll(outGoing);
        return returnSet;
    }

    /**
     * Returns the label of this node.
     *
     * @return a String 'label' that is equal to the label of this node
     */
    public T getLabel() { return label; }

    /**
     * Returns the edges between this and another node.
     *
     * @param other the node to find an edge to
     * @spec.requires node other is a child of this
     * @return a set of edges that connect this node (parent) and another node (child).
     */
    public Set<GraphEdge<EdgeType, T>> findEdges(GraphNode<T, EdgeType> other) {
        Set<GraphEdge<EdgeType, T>> possibleEdges = new HashSet<GraphEdge<EdgeType, T>>();
        for (GraphEdge<EdgeType, T> e : outGoing) {
            if (e.getDestination().equals(other)) {
                possibleEdges.add(e);
            }
        }
        return possibleEdges;
    }

    /**
     * Standard equality operation.
     *
     * @param o the object to be compared for equality
     * @return true if 'o' is an instance of a GraphNode and 'this' and 'o' have the same
     *     label.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GraphNode<?, ?>)) {
            return false;
        }
        return this.label.equals(((GraphNode<?, ?>) o).getLabel());
    }

    /**
     * Standard hashCode function.
     *
     * @return an int that all objects equal to this will also
     */
    @Override
    public int hashCode() {
        return label.hashCode();
    }

    /** Throws an exception if the representation invariant is violated. */
    private void checkRep() {
        if (DEBUG) {
            assert label != null;
            //assert inComing != null;
            assert outGoing != null;
            //for (GraphEdge<EdgeType, T> e : inComing) {
            //    assert e != null;
            //}
            for (GraphEdge<EdgeType, T> e : outGoing) {
                assert e != null;
            }
        }
    }
}
