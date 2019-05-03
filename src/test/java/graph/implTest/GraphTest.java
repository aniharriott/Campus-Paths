package graph.implTest;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import graph.*;

import java.util.*;

/**
 * This class contains a set of test cases that can be used to test the implementation of the
 * Graph class.
 */

public final class GraphTest {

    // 0
    public static Graph g0 = new Graph();

    // 1
    public static Graph g1 = new Graph();
    public static Set<GraphNode> nodes1 = null;
    public static GraphNode n1 = new GraphNode("n1");

    // 2
    public static Graph g2 = new Graph();
    public static Set<GraphNode> nodes2 = null;
    public static List<GraphEdge> edges2 = null;
    public static GraphNode n2a = new GraphNode("n2a");
    public static GraphNode n2b = new GraphNode("n2b");
    public static GraphEdge e2 = new GraphEdge("e2", n2a, n2b);
    public static List<GraphNode> path2 = null;

    // many
    public static Graph gM = new Graph();
    public static Set<GraphNode> nodesM = null;
    public static List<GraphEdge> edgesM = null;
    public static GraphNode m1 = new GraphNode("m1");
    public static GraphNode m2 = new GraphNode("m2");
    public static GraphNode m3 = new GraphNode("m3");
    public static GraphEdge eM1 = new GraphEdge("eM1", m1, m2);
    public static GraphEdge eM2 = new GraphEdge("eM2", m2, m3);
    public static GraphEdge eM3 = new GraphEdge("eM1", m3, m1);
    public static List<GraphNode> pathM = null;

    @BeforeClass
    public static void setupBeforeTests() throws Exception {
        nodes1.add(n1); // one node in nodes1, no edges

        nodes2.add(n2a); // two nodes
        nodes2.add(n2b);
        edges2.add(e2); // one edge
        path2.add(n2a); // two node path
        path2.add(n2b);

        nodesM.add(m1); // three nodes in nodesM
        nodesM.add(m2);
        nodesM.add(m3);
        edgesM.add(eM1); // three edges in edgesM
        edgesM.add(eM2);
        edgesM.add(eM3);
        pathM.add(m1); // three node path
        pathM.add(m2);
        pathM.add(m3);
    }


    /** Test to check that Graph.addNode(GraphNode) is implemented correctly */
    @Test
    public void testAddNode() {
        g1.addNode(n1);
        assertEquals("g1.addNode(n1) should have one node", nodes1, g1.listNodes());
        gM.addNode(m1);
        gM.addNode(m2);
        gM.addNode(m3);
        assertEquals("gM.addNode of m1, m2, m3 should have three nodes", nodesM, gM.listNodes());
    }

    /** Test to check that Graph.listNodes() is implemented correctly */
    @Test
    public void testListNodes() {
        assertTrue("g0.listNodes() should return an empty list", g0.listNodes().isEmpty());
        assertEquals("g1.listNodes() should have one node", nodes1, g1.listNodes());
        assertEquals("gM.listNodes() should have three nodes listed alphabetically",
                nodesM, gM.listNodes());
    }

    /** Test to check that Graph.addEdge(GraphEdge) is implemented correctly */
    @Test
    public void testAddEdge() {
        gM.addEdge("eM1", m1, m2);
        gM.addEdge("eM2", m2, m3);
        gM.addEdge("eM3", m3, m1);
        assertEquals("gM.addEdge of eM1 and eM2 should have two edges", edgesM, gM.listEdges());
    }

    /** Test to check that Graph.listEdges() is implemented correctly */
    @Test
    public void testListEdges() {
        assertTrue("g0.listEdges() should return an empty list", g0.listEdges().isEmpty());
        assertTrue("g1.listEdges() should return an empty list", g1.listEdges().isEmpty());
        assertEquals("gM.listEdges() should have two edges listed alphabetically",
                edgesM, gM.listEdges());
    }

    /** Test to check that Graph.findPath(GraphNode, GraphNode) is implemented correctly */
    @Test
    public void testFindPath() {
        assertEquals("g2.findPath(n2a, n2b) should give two nodes", path2, g2.findPath(n2a, n2b));
        assertEquals("gM.findPath(m1, m3) should give three nodes", pathM, gM.findPath(m1, m3));
    }

    /** Test to check that Graph.deleteEdge(GraphEdge) is implemented correctly */
    @Test
    public void testDeleteEdge() {
        gM.deleteEdge(eM1);
        edgesM.remove(eM1);
        assertEquals("gM.deleteEdge(eM1) should have two edges left", edgesM, gM.listEdges());
        gM.deleteEdge(eM2);
        gM.deleteEdge(eM3);
        assertTrue("gM.deleteEdge of eM1 eM2 eM3 should leave an empty edge list",
                gM.listEdges().isEmpty());
    }
    /** Test to check that only edges (and not nodes) were deleted from the Graph */
    @Test
    public void testOnlyEdgesDeleted() {
         assertEquals("none of the nodes should have been deleted by deleteEdge",
                 nodesM, gM.listNodes());
    }

    /** Test to check that Graph.deleteNode(GraphNode) is implemented correctly */
    @Test
    public void testDeleteNode() {
        g1.deleteNode(n1);
        assertTrue("g1.deleteNode(n1) should leave an empty node list", g1.listNodes().isEmpty());
        gM.deleteNode(m1);
        nodesM.remove(m1);
        assertEquals("gM.deleteNode(m1) should have two nodes left", nodesM, gM.listNodes());
        gM.deleteNode(m2);
        gM.deleteNode(m3);
        assertTrue("gM.deleteNode of m1, m2, m3 should leave an empty node list",
                gM.listNodes().isEmpty());
    }

    /** Test to check that deleting nodes will delete their edges as well */
    @Test
    public void testDeleteEdgeWithNode() {
        g2.deleteNode(n2a);
        assertTrue("deleting parent node should also delete edges from it",
                g2.listEdges().isEmpty());
        g2.addNode(n2a);
        g2.addEdge("e2", n2a, n2b);
        g2.deleteNode(n2b);
        assertTrue("deleting child node should also delete edges to it",
                g2.listEdges().isEmpty());
    }
}
