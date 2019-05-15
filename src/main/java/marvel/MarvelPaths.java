package marvel;

import graph.*;
import java.util.*;

public class MarvelPaths {

    private Graph g;

    public void main (String[] args) {
        g = new Graph();
        Map<String, Set<String>> map = MarvelParser.parseData("staffSuperheroes.tsv");
        makeGraph(map);
    }

    private void makeGraph(Map<String, Set<String>> map) {
        for (String book : map.keySet()) {
            List<GraphNode> characters = new ArrayList<GraphNode>();
            for (String character : map.get(book)) {
                GraphNode n = new GraphNode(character);
                g.addNode(n);
                characters.add(n);
            }
            for (int i = 0; i < characters.size() - 1; i++) {
                for (int j = i+1; j < characters.size(); j++) {
                    GraphEdge e1 = new GraphEdge(book, characters.get(i), characters.get(j));
                    GraphEdge e2 = new GraphEdge(book, characters.get(j), characters.get(i));
                    g.addEdge(e1);
                    g.addEdge(e2);
                }
            }
        }
    }
}
