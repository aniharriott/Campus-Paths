package pathfinder;
import graph.*;
import pathfinder.datastructures.*;
import java.util.*;

public class DijkstraAlgorithm {

    public static Path<GraphNode<String, Double>> findMinPath(GraphNode<String, Double> s, GraphNode<String, Double> d) {
        GraphNode<String, Double> start = s;
        GraphNode<String, Double> dest = d;
        Comparator<Path<GraphNode<String, Double>>> byCost = Comparator.comparing(Path<GraphNode<String, Double>>::getCost);
        Queue<Path<GraphNode<String, Double>>> active = new PriorityQueue<>(byCost);
        List<GraphNode<String, Double>> finished = new LinkedList<>();
        // add path from start to itself to active
        Path<GraphNode<String, Double>> self = new Path<GraphNode<String, Double>>(start);
        active.add(self);
        //finished.add(self.getEnd());
        while(!active.isEmpty()) {
            // minPath = lowest cost active path
            Path<GraphNode<String, Double>> minPath = active.remove();
            GraphNode<String, Double> minDest = minPath.getEnd();
            if (minDest.equals(dest)) {
                return minPath;
            }
            if (finished.contains(minDest)) {
            for (GraphEdge<Double, String> e : minDest.getOutGoing()) {
                if (!finished.contains(e.getDestination())) {
                    Path<GraphNode<String, Double>> newPath = minPath.extend(e.getDestination(), e.getLabel());
                    active.add(newPath);
                    }
                }
            }
            finished.add(minDest);
        }
        // no path exists from start to dest
        return null;
    }
}
