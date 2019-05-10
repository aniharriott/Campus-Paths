package graph.implTest;

import org.junit.Rule;
import org.junit.rules.Timeout;

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

    @Rule public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested

    // 0
    public static Graph g0 = new Graph();

    // 1
    public static Graph g1 = new Graph();
    public static List<GraphNode> nodes1 = new ArrayList<GraphNode>();
    public static GraphNode n1 = new GraphNode("n1");

    // 2
    public static Graph g2 = new Graph();
    public static List<GraphNode> nodes2 = new ArrayList<GraphNode>();
    public static List<GraphEdge> edges2 = new ArrayList<GraphEdge>();
    public static GraphNode n2a = new GraphNode("n2a");
    public static GraphNode n2b = new GraphNode("n2b");
    public static GraphEdge e2 = new GraphEdge("e2", n2a, n2b);
    public static GraphEdge path2 = e2;

    // many
    public static Graph gM = new Graph();
    public static List<GraphNode> nodesM = new ArrayList<GraphNode>();
    public static List<GraphEdge> edgesM = new ArrayList<GraphEdge>();
    public static GraphNode m1 = new GraphNode("m1");
    public static GraphNode m2 = new GraphNode("m2");
    public static GraphNode m3 = new GraphNode("m3");
    public static GraphEdge eM1 = new GraphEdge("eM1", m1, m2);
    public static GraphEdge eM2 = new GraphEdge("eM2", m2, m3);
    public static GraphEdge eM3 = new GraphEdge("eM1", m3, m1);

    @BeforeClass
    public static void setupBeforeTests() throws Exception {
        nodes1.add(n1); // one node in nodes1, no edges

        nodes2.add(n2a); // two nodes
        nodes2.add(n2b);
        edges2.add(e2); // one edge

        nodesM.add(m1); // three nodes in nodesM
        nodesM.add(m2);
        nodesM.add(m3);
        edgesM.add(eM1); // three edges in edgesM
        edgesM.add(eM2);
        edgesM.add(eM3);
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
        Graph g = new Graph();
        GraphNode n = new GraphNode("n");
        GraphNode n1 = new GraphNode("n1");
        GraphNode n2 = new GraphNode("n2");
        g.addNode(n);
        g.addNode(n1);
        g.addNode(n2);
        g.addEdge("e", n, n1);
        g.addEdge("e1", n1, n2);
        g.addEdge("e2", n2, n);
        assertEquals("g.addEdge of e, e1, e2 should have 3 edges", 3, g.listEdges().size());
    }

    /** Test to check that Graph.listEdges() is implemented correctly */
    @Test
    public void testListEdges() {
        Graph g = new Graph();
        assertTrue("g.listEdges() should return an empty list", g.listEdges().isEmpty());
        GraphNode n = new GraphNode("n");
        g.addNode(n);
        assertTrue("g.listEdges() should return an empty list", g.listEdges().isEmpty());
        GraphNode n1 = new GraphNode("n1");
        g.addNode(n1);
        g.addEdge("e", n, n1);
        assertEquals("g.listEdges() should have one edge",
                1, g.listEdges().size());
        GraphNode n2 = new GraphNode("n2");
        g.addNode(n2);
        g.addEdge("e1", n1, n2);
        g.addEdge("e2", n2, n);
        assertEquals("g.listEdges() should have three edges",
                3, g.listEdges().size());

    }

    /** Test to check that Graph.findConnection(GraphNode, GraphNode) is implemented correctly */
    @Test
    public void testFindConnection() {
        Graph g = new Graph();
        GraphNode n = new GraphNode("n");
        GraphNode n1 = new GraphNode("n1");
        GraphNode n2 = new GraphNode("n2");
        g.addNode(n);
        g.addNode(n1);
        g.addNode(n2);
        GraphEdge e = g.addEdge("e", n, n1);
        assertEquals("g.findConnection(n, n1) should give e2", e, g.findConnection(n, n1));
        assertEquals("g.findConnection(n1, n2) should give null", null, g.findConnection(n1, n2));
    }

    /** Test to check that Graph.deleteEdge(GraphEdge) is implemented correctly */
    @Test
    public void testDeleteEdge() {
        Graph g = new Graph();
        GraphNode n = new GraphNode("n");
        GraphNode n1 = new GraphNode("n1");
        GraphNode n2 = new GraphNode("n2");
        g.addNode(n);
        g.addNode(n1);
        g.addNode(n2);
        GraphEdge e = g.addEdge("e", n, n1);
        GraphEdge e1 = g.addEdge("e1", n1, n2);
        g.deleteEdge(e);
        assertEquals("g.deleteEdge(e) should have one edges left", 1, g.listEdges().size());
        g.deleteEdge(e1);
        assertTrue("g.deleteEdge(e1) of should leave an empty edge list",
                g.listEdges().isEmpty());
    }
    /** Test to check that only edges (and not nodes) were deleted from the Graph */
    @Test
    public void testOnlyEdgesDeleted() {
        Graph g = new Graph();
        GraphNode n = new GraphNode("n");
        GraphNode n1 = new GraphNode("n1");
        g.addNode(n);
        g.addNode(n1);
        GraphEdge e = g.addEdge("e", n, n1);
        g.deleteEdge(e);
        assertEquals("none of the nodes should have been deleted by deleteEdge",
                 2, g.listNodes().size());
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
        Graph g = new Graph();
        GraphNode n = new GraphNode("n");
        GraphNode n1 = new GraphNode("n1");
        g.addNode(n);
        g.addNode(n1);
        GraphEdge e = g.addEdge("e", n, n1);
        g.deleteNode(n);
        assertTrue("deleting parent node should also delete edges from it",
                g.listEdges().isEmpty());
        g.addNode(n);
        g.addEdge("e1", n1, n);
        g.deleteNode(n);
        assertTrue("deleting child node should also delete edges to it",
                g.listEdges().isEmpty());
    }
}
