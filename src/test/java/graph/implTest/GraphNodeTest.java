package graph.implTest;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import graph.*;

import java.util.*;

/**
 * This class contains a set of test cases that can be used to test the implementation of the
 * GraphNode class.
 */

public final class GraphNodeTest {

    // 0
    private static final String label0 = "label0";
    private static GraphNode node0 = new GraphNode(label0);
    // 1
    private static GraphNode node1 = new GraphNode("label1");
    private static final GraphNode node1b = new GraphNode("label1b");
    private static final GraphEdge e1 = new GraphEdge("e1", node1, node1b); // out
    private static final GraphEdge e1b = new GraphEdge("e1b", node1b, node1); // in
    private static Set<GraphEdge> edges1_in = null;
    private static Set<GraphEdge> edges1_out = null;
    private static Set<GraphNode> parents1 = null;
    private static Set<GraphNode> children1 = null;
    // many
    private static GraphNode nodeMulti = new GraphNode("multi");
    private static GraphNode nodeMultiB = new GraphNode("multiB");
    private static GraphNode nodeMultiC = new GraphNode("multiC");
    private static final GraphEdge eM1 = new GraphEdge("eM1", nodeMulti, nodeMultiB); // out
    private static final GraphEdge eM2 = new GraphEdge("eM2", nodeMultiB, nodeMulti); // in
    private static final GraphEdge eM3 = new GraphEdge("eM3", nodeMulti, nodeMultiC); // out
    private static final GraphEdge eM4 = new GraphEdge("eM4", nodeMultiC, nodeMulti); // in
    private static Set<GraphEdge> edgesM_in = null;
    private static Set<GraphEdge> edgesM_out = null;
    private static Set<GraphNode> parents2 = null;
    private static Set<GraphNode> children2 = null;

    @BeforeClass
    public static void setupBeforeTests() throws Exception {
        edges1_in.add(e1b);
        edges1_out.add(e1);
        edgesM_in.add(eM2);
        edgesM_in.add(eM4);
        edgesM_out.add(eM1);
        edgesM_out.add(eM3);
    }

    /** Test to check that GraphNode.getLabel() is implemented correctly */
    @Test
    public void testGetLabel() {
        assertEquals("node0.getLabel()", label0, node0.getLabel());
    }

    /** Test to check that GraphNode.getInComing() is implemented correctly */
    @Test
    public void testGetInComing() {
        assertTrue("node0.getInComing() should return an empty set when empty",
                node0.getInComing().isEmpty());
        assertEquals("node1.getInComing() when inComing has one edge", edges1_in,
                node1.getInComing());
        assertEquals("nodeMulti.getInComing() when inComing has many edges", edgesM_in,
                nodeMulti.getInComing());
    }

    /** Test to check that GraphNode.getOutGoing() is implemented correctly */
    @Test
    public void testGetOutGoing() {
        assertTrue("node0.getOutGoing() should return an empty set when empty",
                node0.getOutGoing().isEmpty());
        assertEquals("node1.getOutGoing() when outGoing has one edge", edges1_out,
                node1.getOutGoing());
        assertEquals("nodeMulti.getOutGoing() when outGoing has many edges", edgesM_out,
                nodeMulti.getOutGoing());
    }
    /** Test to check that GraphNode.deleteEdge(GraphEdge) is implemented correctly */
    @Test
    public void testDeleteEdge() {
        edgesM_in.remove(eM2);
        nodeMulti.deleteEdge(eM2);
        edgesM_out.remove(eM1);
        nodeMulti.deleteEdge(eM1);
        assertEquals("nodeMulti.deleteEdge(em2)", edgesM_in, nodeMulti.getInComing());
        assertEquals("nodeMulti.deleteEdge(eM1)", edgesM_out, nodeMulti.getOutGoing());

        edgesM_in.remove(eM4);
        nodeMulti.deleteEdge(eM4);
        edgesM_out.remove(eM3);
        nodeMulti.deleteEdge(eM3);
        assertTrue("nodeMulti.deleteEdge(em4)", nodeMulti.getInComing().isEmpty());
        assertTrue("nodeMulti.deleteEdge(em3)", nodeMulti.getOutGoing().isEmpty());
    }

    /** Test to check that GraphNode.addInComing(GraphEdge) is implemented correctly */
    @Test
    public void testAddInComing() {
        edgesM_in.add(eM4);
        nodeMulti.addInComing(eM4);
        assertEquals("nodeMulti.addInComing(eM4)", edgesM_in, nodeMulti.getInComing());
        edgesM_in.add(eM2);
        nodeMulti.addInComing(eM2);
        assertEquals("nodeMulti.addInComing(eM2)", edgesM_in, nodeMulti.getInComing());
    }

    /** Test to check that GraphNode.addOutGoing(GraphEdge) is implemented correctly */
    @Test
    public void testAddOutGoing() {
        edgesM_out.add(eM3);
        nodeMulti.addOutGoing(eM3);
        assertEquals("nodeMulti.addOutGoing(eM3)", edgesM_out, nodeMulti.getOutGoing());
        edgesM_out.add(eM1);
        nodeMulti.addOutGoing(eM1);
        assertEquals("nodeMulti.addOutGoing(eM1)", edgesM_out, nodeMulti.getOutGoing());
    }

    /** Test to check that GraphNode.getParents() is implemented correctly */
    @Test
    public void testGetParents() {
        assertTrue("node0.getParents() should be empty", node0.getParents().isEmpty());
        parents1.add(node1b);
        assertEquals("node1.getParents() should have one", parents1, node1.getParents());
        parents2.add(nodeMultiB);
        parents2.add(nodeMultiC);
        assertEquals("nodeMulti.getParents() should have two", parents2, nodeMulti.getParents());
    }

    /** Test to check that GraphNode.getChildren() is implemented correctly */
    @Test
    public void testGetChildren() {
        assertTrue("node0.getChildren() should be empty", node0.getChildren().isEmpty());
        children1.add(node1b);
        assertEquals("node1.getChildren() should have one", children1, node1.getChildren());
        children2.add(nodeMultiB);
        children2.add(nodeMultiC);
        assertEquals("nodeMulti.getChildren() should have two", children2, nodeMulti.getChildren());
    }

    /** Test to check that GraphNode.findEdge(GraphEdge) is implemented correctly */
    @Test
    public void testFindEdge() {
        assertEquals("node1 to node1b should be connected by e1", e1, node1.findEdge(node1b));
        GraphEdge e1c = new GraphEdge("e1c", node1, node1b);
        assertEquals("node1 to node1b should be alphabetically connected by e1", e1,
                node1.findEdge(node1b));
    }
}