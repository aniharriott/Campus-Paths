package pathfinder;
import graph.*;
import pathfinder.datastructures.*;
import java.util.*;

public class DijkstraAlgorithm {

    public static <NodeType, EdgeType extends Number> Path<NodeType> findMinPath(NodeType start, NodeType dest, Graph<NodeType, EdgeType> g) {
        Comparator<Path<NodeType>> byCost = Comparator.comparing(Path<NodeType>::getCost);
        Queue<Path<NodeType>> active = new PriorityQueue<>(byCost);
        Set<NodeType> finished = new HashSet<>();
        // add path from start to itself to active
        Path<NodeType> self = new Path<NodeType>(start);
        active.add(self);
        while(!active.isEmpty()) {
            // minPath = lowest cost active path
            Path<NodeType> minPath = active.remove();
            NodeType minDest = minPath.getEnd();
            if (minDest.equals(dest)) {
                return minPath;
            }
            if (finished.contains(minDest)) {
                for (GraphEdge<EdgeType, NodeType> e : g.getEdges(minDest)) {
                    if (!finished.contains(e.getDestination().getLabel())) {
                        Path<NodeType> newPath = minPath.extend(e.getDestination().getLabel(), (double)e.getLabel());
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
