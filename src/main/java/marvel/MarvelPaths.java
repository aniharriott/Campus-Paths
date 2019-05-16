package marvel;

import graph.*;
import java.util.*;

public class MarvelPaths {


    public void main (String[] args) {
        Map<String, Set<String>> map = MarvelParser.parseData("src/test/resources/marvel/data/staffSuperheroes.tsv");
        Graph graph = makeGraph(map);
    }

    public static Graph makeGraph(Map<String, Set<String>> map) {
        Graph g = new Graph();
        for (String book : map.keySet()) {
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
        }
        return g;
    }

    public static List<GraphNode> findPath(String s, String d, Graph g) {
        GraphNode start = null;
        GraphNode destination = null;
        boolean foundS = false;
        boolean foundD = false;
        for (GraphNode n : g.listNodes()) {
            if (n.getLabel().equals(s)) {
                start = n;
                foundS = true;
            }
            if (n.getLabel().equals(d)) {
                destination = n;
                foundD = true;
            }
        }
        if (!foundD || !foundS) {
            return null;
        }

        Comparator<GraphNode> nodeLabel = Comparator.comparing(GraphNode::getLabel);
        Comparator<GraphEdge> edgeLabel = Comparator.comparing(GraphEdge::getLabel);

        Queue<GraphNode> nodesToVisit = new PriorityQueue<>(start.getChildren().size(), nodeLabel);
        nodesToVisit.addAll(start.getChildren());
        Map<GraphNode, List<GraphNode>> nodesToPaths = new HashMap<>();
        nodesToVisit.add(start);
        nodesToPaths.put(start, new ArrayList<GraphNode>());

        while(!nodesToVisit.isEmpty()) {
            GraphNode currentNode = nodesToVisit.remove();
            if (currentNode.equals(destination)) {
                return nodesToPaths.get(currentNode);
            }

            List<GraphNode> children = new ArrayList<GraphNode>(currentNode.getChildren());
            children.sort(nodeLabel);
            for (GraphNode child : children) {
                List<GraphEdge> edges = new ArrayList<GraphEdge>(currentNode.findEdges(child));
                edges.sort(edgeLabel);
                if (!nodesToPaths.containsKey(child)) {
                    List<GraphNode> path = nodesToPaths.get(currentNode);
                    path.add(child);
                    nodesToPaths.put(child, path);
                    nodesToVisit.add(child);
                }
            }
        }

        GraphNode noPath = null;
        List<GraphNode> returnList = new ArrayList<>();
        returnList.add(noPath);
        return returnList;
    }
}
