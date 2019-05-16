package marvel.implTest;

import marvel.MarvelParser;
import marvel.MarvelPaths;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.*;
import graph.*;

public class MarvelPathsTest {

    @Test
    public void testMakeGraph() {

        Map<String, Set<String>> map = MarvelParser.parseData("src/test/resources/marvel/data/staffSuperheroes.tsv");
        Graph g = MarvelPaths.makeGraph(map);
        Set<GraphNode> nodes = new HashSet<GraphNode>();

        GraphNode Ernst = new GraphNode("Ernst-the-Bicycling-Wizard");
        GraphNode Notkin = new GraphNode("Notkin-of-the-Superhuman-Beard");
        GraphNode Perkins = new GraphNode("Perkins-the-Magical-Singing-Instructor");
        GraphNode Grossman = new GraphNode("Grossman-the-Youngest-of-them-all");

        nodes.add(Ernst);
        nodes.add(Notkin);
        nodes.add(Perkins);
        nodes.add(Grossman);

        Graph m = new Graph();
        m.addNode(Ernst);
        m.addNode(Notkin);
        m.addNode(Perkins);
        m.addNode(Grossman);

        //check map
        assertEquals("map is wrong", 5, map.keySet().size());
        //check nodes
        assertFalse("empty nodes", g.listNodes().isEmpty());
        assertEquals("nodes of graph is not populated correctly", 4, g.listNodes().size());
        assertEquals("maps not equal in nodes", m.listNodes(), g.listNodes());
        //check edges
        assertFalse("empty edges", g.listEdges().isEmpty());
        assertEquals("wrong number of edges", 23, g.listEdges().size());
    }

    @Test
    public void testFindPath() {

        Map<String, Set<String>> map = MarvelParser.parseData(
                "src/test/resources/marvel/data/staffSuperheroes.tsv");
        Graph g = MarvelPaths.makeGraph(map);

        assertEquals("path is not null", null,
                MarvelPaths.findPath("Ernst-the-Bicycling-Wizard", "Joe", g));
        assertFalse("path is empty", MarvelPaths.findPath("Ernst-the-Bicycling-Wizard",
                "Notkin-of-the-Superhuman-Beard", g).isEmpty());
        assertTrue("path to itself should be empty",
                MarvelPaths.findPath("Ernst-the-Bicycling-Wizard",
                        "Ernst-the-Bicycling-Wizard", g).isEmpty());

        //assertEquals("path should contain two nodes", 2,
        //        MarvelPaths.findPath("Notkin-of-the-Superhuman-Beard",
        //                "Perkins-the-Magical-Singing-Instructor", g).size());

        List<GraphNode> path = MarvelPaths.findPath("Notkin-of-the-Superhuman-Beard", "Perkins-the-Magical-Singing-Instructor", g);
        for (GraphNode n : path) {
            System.out.println(n.getLabel());
        }

    }

}
