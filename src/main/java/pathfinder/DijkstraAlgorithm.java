package pathfinder;
import graph.*;
import pathfinder.datastructures.*;
import java.util.*;

/**
 * DijkstrasAlgorithm represents a class that can find the lowest cost path in a graph based on
 * path weight.
 */
public class DijkstraAlgorithm {

    // Not an ADT

    /**
     * Finds the lowest cost path between two nodes in the graph based on path weight.
     *
     * @param start the starting node
     * @param dest the ending node
     * @param g the graph to be examined
     * @param <NodeType> the type parameter of the nodes
     * @param <EdgeType> the type parameter of the edges
     * @return a path of the node type representing the lowest cost path, returns null
     * if there is no path between two nodes
     */
    public static <NodeType, EdgeType extends Number> Path<NodeType> findMinPath(NodeType start, NodeType dest, Graph<NodeType, EdgeType> g) {
        Comparator<Path<NodeType>> byCost = Comparator.comparing(Path<NodeType>::getCost);
        Queue<Path<NodeType>> active = new PriorityQueue<>(byCost);
        Set<NodeType> finished = new HashSet<>();
        // add path from start to itself to active
        Path<NodeType> self = new Path<NodeType>(start);
        active.add(self);
        // Invariant: active = nodes not visited yet, finished = nodes visited
        while(!active.isEmpty()) {
            // minPath = lowest cost active path
            Path<NodeType> minPath = active.remove();
            NodeType minDest = minPath.getEnd();
            if (minDest.equals(dest)) {
                return minPath;
            }
            if (finished.contains(minDest)) {
                continue;
            }
                for (GraphEdge<EdgeType, NodeType> e : g.getEdges(minDest)) {
                    if (!finished.contains(e.getDestination().getLabel())) {
                        Path<NodeType> newPath = minPath.extend(e.getDestination().getLabel(), (double)e.getLabel());
                        active.add(newPath);
                    }
                }
            finished.add(minDest);
        }
        // no path exists from start to dest
        return null;
    }
}
