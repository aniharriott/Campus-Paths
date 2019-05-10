package graph.implTest;

import org.junit.Rule;
import org.junit.rules.Timeout;

import graph.GraphEdge;
import graph.GraphNode;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class contains a set of test cases that can be used to test the implementation of the
 * GraphEdge class.
 */

public final class GraphEdgeTest {

    @Rule public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested

    private static GraphEdge e = null;
    private static String l = "edge";
    private static GraphNode n1 = new GraphNode("n1");
    private static GraphNode n2 = new GraphNode("n2");
    private static GraphNode n3 = new GraphNode("n1");
    private static GraphNode n4 = new GraphNode("n2");
    private static GraphEdge e2 = new GraphEdge(l, n3, n4);

    @BeforeClass
    public static void setupBeforeTests() throws Exception {
        e = new GraphEdge(l, n1, n2);
    }

    /** Test to check that GraphEdge.getLabel() is implemented correctly */
    @Test
    public void testGetLabel() {
        assertEquals("e.getLabel()", l, e.getLabel());
    }

    /** Test to check that GraphEdge.getSource() is implemented correctly */
    @Test
    public void testGetSource() {
        assertEquals("e.getSource()", n1, e.getSource());
    }

    /** Test to check that GraphEdge.getDestination() is implemented correctly */
    @Test
    public void testGetDestination() {
        assertEquals("e.getDestination()", n2, e.getDestination());
    }

    /** Test to check that GraphEdge.hashCode is implemented correctly */
    @Test
    public void testHashCode() {
        GraphEdge e3 = new GraphEdge(l, n2, n1);
        GraphEdge e4 = new GraphEdge("e4", new GraphNode("x"), new GraphNode("y"));
        GraphEdge e5 = new GraphEdge("e5", n1, new GraphNode("z"));
        assertTrue("e.hashCode() should be true", e.hashCode() == (e2.hashCode()));
        assertFalse("e.hashCode() should be false", e.hashCode() == (e3.hashCode()));
        assertFalse("e.hashCode() should be false", e.hashCode() == (e4.hashCode()));
        assertFalse("e.hashCode() should be false", e.hashCode() == (e5.hashCode()));
    }

    /** Test to check that GraphEdge.equals(GraphEdge) is implemented correctly */
    @Test
    public void testEquals() {
        GraphEdge e3 = new GraphEdge(l, new GraphNode("x"), new GraphNode("y"));
        assertTrue("e.equals(GraphEdge)", e.equals(e2));
        assertFalse("!e.equal(GraphEdge", e.equals(e3));
    }
}
