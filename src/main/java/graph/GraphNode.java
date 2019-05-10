package graph;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

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

public class GraphNode {

    /** The label of this node */
    private final String label;
    /** The edges that point to this node in alphabetical order */
    private List<GraphEdge> inComing;
    /** The edges that point away from this node in alphabetical order */
    private List<GraphEdge> outGoing;
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
        inComing = new ArrayList<GraphEdge>();
        outGoing = new ArrayList<GraphEdge>();
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
    public GraphNode(String l, List<GraphEdge> in, List<GraphEdge> out){
        this(l);
        // copy the List parameters into this node
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
        checkRep();
        if (e == null) {
            throw new IllegalArgumentException("edge cannot be null");
        }
        for (GraphEdge edge : this.inComing) {
            if (edge.equals(e)) {
                throw new IllegalArgumentException("edge cannot be a duplicate of this node");
            }
        }
        this.inComing.add(e);
        checkRep();
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
        checkRep();
        if (e == null) {
            throw new IllegalArgumentException("edge cannot be null");
        }
        for (GraphEdge edge : this.outGoing) {
            if (edge.equals(e)) {
                throw new IllegalArgumentException("edge cannot be a duplicate of this node");
            }
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
    public void deleteEdge(GraphEdge e) {
        checkRep();
        // look through the in coming edges
        if (inComing.contains(e)) {
            inComing.remove(e);
        }
        // look through the out going edges
        if (outGoing.contains(e)) {
            outGoing.remove(e);
        }
        checkRep();
    }

    /**
     * Returns a list of all the nodes that are children of this in alphabetical order.
     *
     * @return a list of nodes that contains all the children of this node in alphabetical
     * order
     */
    public List<GraphNode> getChildren() {
        List<GraphNode> children = new ArrayList<GraphNode>();
        for (GraphEdge e : outGoing) {
            children.add(e.getDestination());
        }
        Comparator<GraphNode> byLabel = Comparator.comparing(GraphNode::getLabel);
        children.sort(byLabel);
        return children;
    }

    /**
     * Returns a list of all the nodes that are parents of this in alphabetical order.
     *
     * @return a list of nodes that contains all the parents of this node in alphabetical
     * order
     */
    public List<GraphNode> getParents() {
        List<GraphNode> parents = new ArrayList<GraphNode>();
        for (GraphEdge e : inComing) {
            parents.add(e.getSource());
        }
        Comparator<GraphNode> byLabel = Comparator.comparing(GraphNode::getLabel);
        parents.sort(byLabel);
        return parents;
    }

    /**
     * Returns the in coming edges of this node.
     *
     * @return a list of edges that is equal to the in coming edges of this node
     */
    public List<GraphEdge> getInComing() {
        List<GraphEdge> returnList = new ArrayList<GraphEdge>();
        for (GraphEdge e : inComing) {
            returnList.add(e);
        }
        return returnList;
    }

    /**
     * Returns the out going edges of this node.
     *
     * @return a list of edges that is equal to the out going edges of this node
     */
    public List<GraphEdge> getOutGoing() {
        List<GraphEdge> returnList = new ArrayList<GraphEdge>();
        for (GraphEdge e : outGoing) {
            returnList.add(e);
        }
        return returnList;
    }

    /**
     * Returns the label of this node.
     *
     * @return a String 'label' that is equal to the label of this node
     */
    public String getLabel() { return label; }

    /**
     * Returns the edges between this and another node.
     *
     * @param other the node to find an edge to
     * @spec.requires node other is a child of this
     * @return a list of edges that connect this node (parent) and another node (child)
     * alphabetically sorted.
     */
    public List<GraphEdge> findEdges(GraphNode other) {
        List<GraphEdge> possibleEdges = new ArrayList<GraphEdge>();
        for (GraphEdge e : other.getInComing()) {
            if (e.getSource().equals(this)) {
                possibleEdges.add(e);
            }
        }
        Comparator<GraphEdge> byLabel = Comparator.comparing(GraphEdge::getLabel);
        possibleEdges.sort(byLabel);
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
        if (!(o instanceof GraphNode)) {
            return false;
        }
        return this.label.equals(((GraphNode) o).getLabel());
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
       assert label != null;
       assert inComing != null;
       assert outGoing != null;
       if (DEBUG) {
            for (GraphEdge e : inComing) {
                assert e != null;
            }
           for (GraphEdge e : outGoing) {
               assert e != null;
           }
       }
    }
}
