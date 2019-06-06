package marvel;

import graph.*;
import java.util.*;

public class MarvelPaths {

    // Not an ADT

    public static void main (String[] args) {
        Map<String, List<String>> m = MarvelParser.parseData("src/test/resources/marvel/data/marvel.tsv");
        Graph<String, String> g = makeGraph(m);
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to marvel path finder!");
        System.out.println("Please type the name of the " +
                "character you want your path to start at: ");
        String start = console.nextLine();
        System.out.println("Please type the name of the character you " +
                "want your path to end at: ");
        String end = console.nextLine();
        List<GraphNode<String, String>> path = findPath(start, end, g);
        System.out.println("path from " + start + " to " + end + ":");
        if (path == null) {
            System.out.println("no path found");
        } else if ((path.size() - 1 >= 1) && (path.get(0).getLabel().equals("Bad start node") &&
                path.get(1).getLabel().equals("Bad destination node"))) {
            System.out.println("unknown character " + start);
            System.out.println("unknown character" + end);
        } else if ((path.size() >= 1) && path.get(0).getLabel().equals("Bad start node")) {
            System.out.println("unknown character " + start);
        } else if ((path.size() >= 1) && path.get(0).getLabel().equals("Bad destination node")) {
            System.out.println("unknown character " + end);
        }
        else if (!path.isEmpty()){
            GraphNode<String, String> node1 = null;
            for (GraphNode<String, String> n : g.listNodes()) {
                if (n.getLabel().equals(start)) {
                    node1 = n;
                }
            }
            path.add(0, node1);
            for (int i = 0; i < path.size()-1; i++) {
                System.out.print(path.get(i).getLabel() + " to " + path.get(i+1).getLabel());
                boolean printed = false;
                for(GraphEdge<String, String> e : path.get(i).getOutGoing()) {
                    if (!printed && e.getDestination().equals(path.get(i+1))) {
                        System.out.print(" via " + e.getLabel());
                        printed = true;
                    }
                }
                System.out.println();
            }
        }
    }

    public static Graph<String, String> makeGraph(Map<String, List<String>> map) {
        Graph<String, String> g = new Graph<String, String>();
        // adds nodes to graph
        for (List<String> nodes : map.values()) {
           for (String n : nodes) {
               g.addNode(new GraphNode<String, String>(n));
           }
        }
        //add edges to graph
        for (String book : map.keySet()) {
            for (String sourceName : map.get(book)) {
                for (String destinationName : map.get(book)) {
                    GraphNode<String, String> destination = new GraphNode<String, String>(destinationName);
                    GraphNode<String, String> source = new GraphNode<String, String>(sourceName);
                    g.addEdge(new GraphEdge<String, String>(book , source, destination));
                }
            }
        }
        return g;
    }

    public static List<GraphNode<String, String>> findPath(String s, String d, Graph<String, String> g) {
        GraphNode<String, String> start = null;
        GraphNode<String, String> dest = null;
        // get the destination and source nodes asked for
        for (GraphNode<String, String> n : g.listNodes()) {
            if (n.getLabel().equals(s)) {
                start = n;
            }
            if (n.getLabel().equals(d)) {
                dest = n;
            }
        }

        // if the nodes passed aren't in the graph
        List<GraphNode<String, String>> temp = new LinkedList<>();
        if (start == null) {
            temp.add(new GraphNode<String, String>("Bad start node"));
        }
        if (dest == null) {
            temp.add(new GraphNode<String, String>("Bad destination node"));
        }
        if (!temp.isEmpty()) {
            return temp;
        }

        Comparator<GraphNode<String, String>> nodeLabel = Comparator.comparing(GraphNode<String, String>::getLabel);

        Queue<GraphNode<String, String>> worklist = new LinkedList<>();
        Map<GraphNode<String, String>, List<GraphNode<String, String>>> paths = new HashMap<>();

        worklist.add(start);
        paths.put(start, new LinkedList<GraphNode<String, String>>()); // a path from a node to itself will be empty

        // Invariant : worklist contains unvistited nodes, map contains visited nodes and their
        // path of nodes from the start node
        while (!worklist.isEmpty()) {
            GraphNode<String, String> currentNode = worklist.remove();
            if (currentNode.equals(dest)) {
                return paths.get(currentNode);
            }
            List<GraphNode<String, String>> nodes = new LinkedList<>(currentNode.getChildren());
            nodes.sort(nodeLabel);
            //    for each edge e=⟨n,m⟩:
            for (GraphNode<String, String> n : nodes) {
                if (!paths.containsKey(n)) {
                    List<GraphNode<String, String>> p = new LinkedList<>(paths.get(currentNode));
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
