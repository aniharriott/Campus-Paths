package marvel;

import graph.*;
import org.apache.commons.collections.ArrayStack;

import java.util.*;

public class MarvelPaths {


    public void main (String[] args) {
        //Map<String, Set<String>> map = MarvelParser.parseData("src/test/resources/marvel/data/staffSuperheroes.tsv");
        //Graph graph = makeGraph(map);
    }

    public static Graph makeGraph(Map<String, List<String>> map) {
        Graph g = new Graph();
        // adds nodes to graph
        for (List<String> nodes : map.values()) {
           for (String n : nodes) {
               g.addNode(new GraphNode(n));
           }
        }
        //add edges to graph
        for (String book : map.keySet()) {
            for (String sourceName : map.get(book)) {
                for (String destinationName : map.get(book)) {
                    GraphNode destination = new GraphNode(destinationName);
                    GraphNode source = new GraphNode(sourceName);
                    g.addEdge(new GraphEdge(book , source, destination));
                }
            }
        }




        /**for (String book : map.keySet()) {
            // add nodes : the hero names from the set in the map
            Set<String> heroNames = map.get(book);
            for (String n : heroNames) {
                GraphNode hero = new GraphNode(n);
                g.addNode(hero);
            }
            // add edges : the string keys from the map
            for (String sourceName : map.get(book)) {
                GraphNode source = new GraphNode(sourceName);
                for (String destinationName : map.get(book)) {
                    GraphNode destination = new GraphNode(destinationName);
                    GraphEdge e = new GraphEdge(book , source, destination);
                    g.addEdge(e);
                }
            }
        }*/
        return g;
    }

    public static List<GraphNode> findPath(String s, String d, Graph g) {
        GraphNode start = null;
        GraphNode dest = null;
        for (GraphNode n : g.listNodes()) {
            if (n.getLabel().equals(s)) {
                start = n;
            }
            if (n.getLabel().equals(d)) {
                    dest = n;
            }
        }

        List<GraphNode> temp = new LinkedList<>();
        if (start == null) {
            temp.add(new GraphNode("Bad start node"));
        }
        if (dest == null) {
            temp.add(new GraphNode("Bad destination node"));
        }
        if (!temp.isEmpty()) {
            return temp;
        }

        Comparator<GraphNode> nodeLabel = Comparator.comparing(GraphNode::getLabel);
        Comparator<GraphEdge> edgeLabel = Comparator.comparing(GraphEdge::getLabel);

        Queue<GraphNode> worklist = new LinkedList<>();
        Map<GraphNode, List<GraphNode>> paths = new HashMap<>();

        worklist.add(start);
        paths.put(start, new LinkedList<GraphNode>()); // a path from a node to itself will be empty

        while (!worklist.isEmpty()) {
            GraphNode currentNode = worklist.remove();
            if (currentNode.equals(dest)) {
                return paths.get(currentNode);
            }
            List<GraphNode> nodes = new LinkedList<>(currentNode.getChildren());
            nodes.sort(nodeLabel);
            //    for each edge e=⟨n,m⟩:
            for (GraphNode n : nodes) {
                if (!paths.containsKey(n)) {
                    List<GraphNode> p = new LinkedList<>(paths.get(currentNode));
                    p.add(n);
                    paths.put(n, p);
                    worklist.add(n);
                }
            }

        }

        // if there is no path, return null
        return null;
    }
}
