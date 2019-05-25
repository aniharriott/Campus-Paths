/*
 * Copyright ©2019 Hal Perkins.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Spring Quarter 2019 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

package pathfinder;

import pathfinder.datastructures.*;
import pathfinder.parser.CampusBuilding;
import pathfinder.parser.CampusPath;
import pathfinder.parser.CampusPathsParser;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import graph.*;

/*
In the pathfinder homework, the text user interface calls these methods to talk
to your model. In the campuspaths homework, your graphical user interface
will ultimately make class to these methods (through a web server) to
talk to your model the same way.

This is the power of the Model-View-Controller pattern, two completely different
user interfaces can use the same model to display and interact with data in
different ways, without requiring a lot of work to change things over.
*/

/**
 * This class represents the connection between the view and controller and the model
 * for the pathfinder and campus paths applications.
 */
public class ModelConnector {

  /** graph of the campus map data */
  private Graph<Point, Double> graph;
  /** list of all the campus buildings */
  private List<CampusBuilding> buildings;

  /**
   * Creates a new {@link ModelConnector} and initializes it to contain data about
   * pathways and buildings or locations of interest on the campus of the University
   * of Washington, Seattle. When this constructor completes, the dataset is loaded
   * and prepared, and any method may be called on this object to query the data.
   */
  public ModelConnector() {
    graph = new Graph<>();
    // parse campus buildings --> nodes
    buildings = CampusPathsParser.parseCampusBuildings();
    // parse campus paths --> edges
    List<CampusPath> paths = CampusPathsParser.parseCampusPaths();
    // add intersections as nodes to graph
    for (CampusPath p : paths) {
      Point intersection1 = new Point(p.getX1(), p.getY1());
      Point intersection2 = new Point(p.getX2(), p.getY2());
      if (graph.getNode(intersection1) == null) {
        GraphNode<Point, Double> node1 = new GraphNode<>(intersection1);
        graph.addNode(node1);
      }
      if (graph.getNode(intersection2) == null) {
        GraphNode<Point, Double> node2 = new GraphNode<>(intersection2);
        graph.addNode(node2);
      }
    }
    // add paths edges to graph
    for (CampusPath p : paths) {
      Double label =p.getDistance();
      GraphEdge<Double, Point> edge = new GraphEdge<>(label, getSource(p), getDest(p));
      graph.addEdge(edge);
    }
  }

  /**
   * Finds the source of the given path.
   *
   * @param p the path to examine
   * @return the source of this path, returns null if not found.
   */
  private GraphNode<Point, Double> getSource(CampusPath p) {
    GraphNode<Point, Double> source = null;
    for (GraphNode<Point, Double> n : graph.listNodes()){
      if (n.getLabel().getX() == p.getX1() && n.getLabel().getY() == p.getY1()) {
        source = n;
      }
    }
    return source;
  }

  /**
   * Finds the destination of the given path.
   *
   * @param p the path to examine
   * @return the destination of this path, returns null if not found.
   */
  private GraphNode<Point, Double> getDest(CampusPath p) {
    GraphNode<Point, Double> dest = null;
    for (GraphNode<Point, Double> n : graph.listNodes()){
      if (n.getLabel().getX() == p.getX2() && n.getLabel().getY() == p.getY2()) {
        dest = n;
      }
    }
    return dest;
  }

  /**
   * @param shortName The short name of a building to query.
   * @return {@literal true} iff the short name provided exists in this campus map.
   */
  public boolean shortNameExists(String shortName) {
    boolean exists = false;
    for (CampusBuilding b : buildings) {
      if (b.getShortName().equals(shortName)) {
        exists = true;
      }
    }
    return exists;
  }

  /**
   * @param shortName The short name of a building to look up.
   * @return The long name of the building corresponding to the provided short name.
   * @throws IllegalArgumentException if the short name provided does not exist.
   */
  public String longNameForShort(String shortName) {
    if (!shortNameExists(shortName)) {
      throw new IllegalArgumentException("name does not exist");
    }
    String name = null;
    for (CampusBuilding b : buildings) {
      if (b.getShortName().equals(shortName)){
        name = b.getLongName();
      }
    }
    return name;
  }

  /**
   * @return The mapping from all the buildings' short names to their long names in this campus map.
   */
  public Map<String, String> buildingNames() {
    Map<String, String> names = new HashMap<>();
    for (CampusBuilding b : buildings) {
      names.put(b.getShortName(), b.getLongName());
    }
    return names;
  }

  /**
   * Finds the shortest path, by distance, between the two provided buildings.
   *
   * @param startShortName The short name of the building at the beginning of this path.
   * @param endShortName   The short name of the building at the end of this path.
   * @return A path between {@code startBuilding} and {@code endBuilding}, or {@literal null}
   * if none exists.
   * @throws IllegalArgumentException if {@code startBuilding} or {@code endBuilding} are
   *                                  {@literal null}, or not valid short names of buildings in
   *                                  this campus map.
   */
  public Path<Point> findShortestPath(String startShortName, String endShortName) {
    if (startShortName == null || endShortName == null || !shortNameExists(startShortName)
            || ! shortNameExists(endShortName)){
      throw new IllegalArgumentException("short names must be valid");
    }
    // Dijkstra's algorithm
    // make a start point
    CampusBuilding s = getBuilding(startShortName);
    CampusBuilding d = getBuilding(endShortName);
    Point start = new Point(s.getX(), s.getY());
    // make an end point
    Point end = new Point(d.getX(), d.getY());
    return DijkstraAlgorithm.findMinPath(start, end, graph);
  }

  /**
   * Finds the building in this map of the given short name.
   *
   * @param shortName the short name of the building to be found
   * @return the building with the short name given, returns null if not found.
   */
  private CampusBuilding getBuilding (String shortName) {
    for (CampusBuilding b : buildings) {
      if (b.getShortName().equals(shortName)) {
        return b;
      }
    }
    return null;
  }

}
