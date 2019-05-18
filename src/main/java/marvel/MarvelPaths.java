package marvel;

import graph.*;
import java.util.*;

public class MarvelPaths {

    // Not an ADT

    public static void main (String[] args) {
        Map<String, List<String>> m = MarvelParser.parseData("src/test/resources/marvel/data/marvel.tsv");
        Graph g = makeGraph(m);
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to marvel path finder!");
        System.out.println("Please type the name of the " +
                "character you want your path to start at: ");
        String start = console.nextLine();
        System.out.println("Please type the name of the character you " +
                "want your path to end at: ");
        String end = console.nextLine();
        List<GraphNode> path = findPath(start, end, g);

        System.out.println("path from " + start + " to " + end + ":");
        if (path == null) {
            System.out.println("no path found");
        } //else if (nodes.get(0).getLabel().equals("Bad start node")) {
        //  output.println("unknown character " + node1);
        //} else if (nodes.get(0).getLabel().equals("Bad destination node") ||
        //        nodes.get(1).getLabel().equals("Bad destination node")) {
        //  output.println("unknown character " + node2);
        //}
        else if (!path.isEmpty()){
            path.add(0, new GraphNode(start));
            for(int i = 0; i < path.size()-1; i++) {
                System.out.print(path.get(i).getLabel() + " to " + path.get(i+1).getLabel());
                for(GraphEdge e : path.get(i+1).getInComing()) {
                    if (e.getSource().getLabel().equals(path.get(i).getLabel())) {
                        System.out.print(" via " + e.getLabel());
                    }
                }
                System.out.println();
            }
        }
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
        return g;
    }

    public static List<GraphNode> findPath(String s, String d, Graph g) {
        GraphNode start = null;
        GraphNode dest = null;
        // get the destination and source nodes asked for
        for (GraphNode n : g.listNodes()) {
            if (n.getLabel().equals(s)) {
                start = n;
            }
            if (n.getLabel().equals(d)) {
                    dest = n;
            }
        }

        // if the nodes passed aren't in the graph
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

        Queue<GraphNode> worklist = new LinkedList<>();
        Map<GraphNode, List<GraphNode>> paths = new HashMap<>();

        worklist.add(start);
        paths.put(start, new LinkedList<GraphNode>()); // a path from a node to itself will be empty

        // Invariant : worklist contains unvistited nodes, map contains visited nodes and their
        // path of nodes from the start node
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
