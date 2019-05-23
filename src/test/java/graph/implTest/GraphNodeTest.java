package graph.implTest;

import org.junit.Rule;
import org.junit.rules.Timeout;

import org.junit.Test;
import static org.junit.Assert.*;
import graph.*;
import java.util.Comparator;

import java.util.*;

/**
 * This class contains a set of test cases that can be used to test the implementation of the
 * GraphNode class.
 */

public final class GraphNodeTest {

    @Rule public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested

    // 0
    private static final String label0 = "label0";
    private static GraphNode<String, String> node0 = new GraphNode<String, String>(label0);
    // 1
    private static GraphNode<String, String> node1 = new GraphNode<String, String>("label1");
    private static final GraphNode<String, String> node1b = new GraphNode<String, String>("label1b");
    private static final GraphEdge<String, String> e1 = new GraphEdge<String, String>("e1", node1, node1b); // out
    private static final GraphEdge<String, String> e1b = new GraphEdge<String, String>("e1b", node1b, node1); // in
    // many
    private static GraphNode<String, String> nodeMulti = new GraphNode<String, String>("multi");
    private static GraphNode<String, String> nodeMultiB = new GraphNode<String, String>("multiB");
    private static GraphNode<String, String> nodeMultiC = new GraphNode<String, String>("multiC");
    private static final GraphEdge<String, String> eM1 = new GraphEdge<String, String>("eM1", nodeMulti, nodeMultiB); // out
    private static final GraphEdge<String, String> eM2 = new GraphEdge<String, String>("eM2", nodeMultiB, nodeMulti); // in
    private static final GraphEdge<String, String> eM3 = new GraphEdge<String, String>("eM3", nodeMulti, nodeMultiC); // out
    private static final GraphEdge<String, String> eM4 = new GraphEdge<String, String>("eM4", nodeMultiC, nodeMulti); // in

    /** Test to check that GraphNode.getLabel() is implemented correctly */
    @Test
    public void testGetLabel() {
        assertEquals("node0.getLabel()", label0, node0.getLabel());
    }

    /** Test to check that GraphNode.getInComing() is implemented correctly */
    @Test
    public void testGetInComing() {
        GraphNode<String, String> n = new GraphNode<String, String>("n");
        Set<GraphEdge<String, String>> n_in = new HashSet<>();
        assertTrue("n.getInComing() should return an empty set when empty",
                n.getInComing().isEmpty());
        n.addInComing(e1);
        n_in.add(e1);
        assertEquals("n.getInComing() when inComing has one edge", n_in,
                n.getInComing());
        n.addInComing(e1b);
        n_in.add(e1b);
        assertEquals("n.getInComing() when inComing has many edges", n_in,
                n.getInComing());
    }

    /** Test to check that GraphNode.getOutGoing() is implemented correctly */
    @Test
    public void testGetOutGoing() {
        GraphNode<String, String> n = new GraphNode<String, String>("n");
        Set<GraphEdge<String, String>> n_out = new HashSet<>();
        assertTrue("n.getOutGoing() should return an empty set when empty",
                n.getOutGoing().isEmpty());
        n.addOutGoing(e1);
        n_out.add(e1);
        assertEquals("n.getOutGoing() when outGoing has one edge", n_out,
                n.getOutGoing());
        n.addOutGoing(e1b);
        n_out.add(e1b);
        assertEquals("n.getOutGoing() when outGoing has many edges", n_out,
                n.getOutGoing());
    }
    /** Test to check that GraphNode.deleteEdge(GraphEdge) is implemented correctly */
    @Test
    public void testDeleteEdge() {
        GraphNode<String, String> n = new GraphNode<String, String>("n");
        Set<GraphEdge<String, String>> edges = new HashSet<>();
        n.addInComing(eM3);
        n.addInComing(eM4);
        edges.add(eM3);
        n.deleteEdge(eM4);
        assertEquals("n.deleteEdge(eM4)", edges, n.getInComing());
        n.addOutGoing(eM1);
        n.addOutGoing(eM2);
        edges.add(eM1);
        edges.remove(eM3);
        n.deleteEdge(eM2);
        assertEquals("n.deleteEdge(eM2)", edges, n.getOutGoing());
        n.deleteEdge(eM3);
        assertTrue("nodeMulti.deleteEdge(em4)", n.getInComing().isEmpty());
        n.deleteEdge(eM1);
        assertTrue("nodeMulti.deleteEdge(em3)", n.getOutGoing().isEmpty());
    }

    /** Test to check that GraphNode.addInComing(GraphEdge) is implemented correctly */
    @Test
    public void testAddInComing() {
        GraphNode<String, String> multiple = new GraphNode<String, String>("multiple");
        Set<GraphEdge<String, String>> multi_in = new HashSet<GraphEdge<String, String>>();
        multi_in.add(eM4);
        multiple.addInComing(eM4);
        assertEquals("multiple.addInComing(eM4)", multi_in, multiple.getInComing());
        multi_in.add(eM2);
        multiple.addInComing(eM2);
        assertEquals("multiple.addInComing(eM2)", multi_in, multiple.getInComing());
    }

    /** Test to check that GraphNode.addOutGoing(GraphEdge) is implemented correctly */
    @Test
    public void testAddOutGoing() {
        GraphNode<String, String> multiple = new GraphNode<String, String>("multiple");
        Set<GraphEdge<String, String>> multi_out = new HashSet<GraphEdge<String, String>>();
        multi_out.add(eM3);
        multiple.addOutGoing(eM3);
        assertEquals("multiple.addOutGoing(eM3)", multi_out, multiple.getOutGoing());
        multi_out.add(eM1);
        multiple.addOutGoing(eM1);
        assertEquals("multiple.addOutGoing(eM1)", multi_out, multiple.getOutGoing());
    }

    /** Test to check that GraphNode.getParents() is implemented correctly */
    @Test
    public void testGetParents() {
        Graph<String, String> g = new Graph<String, String>();
        GraphNode<String, String> n = new GraphNode<String, String>("n");
        g.addNode(n);
        g.addNode(node0);
        g.addNode(node1);
        Set<GraphNode<String, String>> parents = new HashSet<>();
        assertTrue("n.getParents() should be empty", n.getParents().isEmpty());
        GraphEdge<String, String> e = new GraphEdge<String, String>("e", node0, n);
        g.addEdge(e);
        parents.add(node0);
        assertEquals("n.getParents() should have one", parents, n.getParents());
        GraphEdge<String, String> e1 = new GraphEdge<String, String>("e1", node1, n);
        g.addEdge(e1);
        parents.add(node1);
        assertEquals("n.getParents() should have two", parents, n.getParents());
    }

    /** Test to check that GraphNode.getChildren() is implemented correctly */
    @Test
    public void testGetChildren() {
        Graph<String, String> g = new Graph<String, String>();
        GraphNode<String, String> n = new GraphNode<String, String>("n");
        g.addNode(n);
        g.addNode(node0);
        g.addNode(node1);
        Set<GraphNode<String, String>> children = new HashSet<>();
        assertTrue("n.getChildren() should be empty", n.getChildren().isEmpty());
        GraphEdge<String, String> e = new GraphEdge<String, String>("e", n, node0);
        g.addEdge(e);
        children.add(node0);
        assertEquals("n.getChildren() should have one", children, n.getChildren());
        GraphEdge<String, String> e1 = new GraphEdge<String, String>("e1", n, node1);
        g.addEdge(e1);
        children.add(node1);
        assertEquals("n.getChildren() should have two", children,
                n.getChildren());
    }

    /** Test to check that GraphNode.findEdges(GraphEdge) is implemented correctly */
    @Test
    public void testFindEdges() {
        Graph<String, String> g = new Graph<String, String>();
        GraphNode<String, String> n = new GraphNode<String, String>("n");
        GraphNode<String, String> n1 = new GraphNode<String, String>("n1");
        g.addNode(n);
        g.addNode(n1);
        GraphEdge<String, String> e = new GraphEdge<String, String>("e", n, n1);
        g.addEdge(e);
        assertTrue("n to n1 should be connected by e",
                n.findEdges(n1).contains(e));
        GraphEdge<String, String> e1 = new GraphEdge<String, String>("e1", n, n1);
        g.addEdge(e1);
        assertTrue("n to n1 should also be connected by e1",
                n.findEdges(n1).contains(e1));
    }

    /** Test to check that GraphNode.hashCode is implemented correctly */
    @Test
    public void testHashCode() {
        GraphNode<String, String> n = new GraphNode<String, String>("n");
        GraphNode<String, String> n1 = new GraphNode<String, String>("n");
        GraphNode<String, String> n2 = new GraphNode<String, String>("n2");
        assertEquals("n.hashCode() should be true", n.hashCode(), (n1.hashCode()));
        assertNotEquals("n.hashCode() should be false", n1.hashCode(), (n2.hashCode()));
    }

    /** Test to check that GraphNode.equals(GraphNode) is implemented correctly */
    @Test
    public void testEquals() {
        GraphNode<String, String> n = new GraphNode<String, String>("n");
        GraphNode<String, String> n1 = new GraphNode<String, String>("n");
        GraphNode<String, String> n2 = new GraphNode<String, String>("n2");
        assertEquals("e.equals(GraphNode)", n, n1);
        assertNotEquals("!e.equals(GraphNode", n, n2);
    }
}