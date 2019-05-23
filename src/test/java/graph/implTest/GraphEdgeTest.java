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

    private static GraphEdge<String, String> e = null;
    private static String l = "edge";
    private static GraphNode<String, String> n1 = new GraphNode<String, String>("n1");
    private static GraphNode<String, String> n2 = new GraphNode<String, String>("n2");
    private static GraphNode<String, String> n3 = new GraphNode<String, String>("n1");
    private static GraphNode<String, String> n4 = new GraphNode<String, String>("n2");
    private static GraphEdge<String, String> e2 = new GraphEdge<String, String>(l, n3, n4);

    @BeforeClass
    public static void setupBeforeTests() throws Exception {
        e = new GraphEdge<String, String>(l, n1, n2);
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
        GraphEdge<String, String> e3 = new GraphEdge<String, String>(l, n2, n1);
        GraphEdge<String, String> e4 = new GraphEdge<String, String>("e4",
                new GraphNode<String, String>("x"), new GraphNode<String, String>("y"));
        GraphEdge<String, String> e5 = new GraphEdge<String, String>("e5", n1,
                new GraphNode<String, String>("z"));
        assertTrue("e.hashCode() should be true", e.hashCode() == (e2.hashCode()));
        assertFalse("e.hashCode() should be false", e.hashCode() == (e3.hashCode()));
        assertFalse("e.hashCode() should be false", e.hashCode() == (e4.hashCode()));
        assertFalse("e.hashCode() should be false", e.hashCode() == (e5.hashCode()));
    }

    /** Test to check that GraphEdge.equals(GraphEdge) is implemented correctly */
    @Test
    public void testEquals() {
        GraphEdge<String, String> e3 = new GraphEdge<String, String>(l,
                new GraphNode<String, String>("x"), new GraphNode<String, String>("y"));
        assertTrue("e.equals(GraphEdge)", e.equals(e2));
        assertFalse("!e.equal(GraphEdge", e.equals(e3));
    }
}
