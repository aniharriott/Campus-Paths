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

package pathfinder.specTest;

import java.io.*;
import java.util.*;
import graph.*;
import pathfinder.DijkstraAlgorithm;
import pathfinder.datastructures.Path;

/**
 * This class implements a test driver that uses a script file format
 * to test an implementation of Dijkstra's algorithm on a graph.
 */
public class PathfinderTestDriver {

  public static void main(String args[]) {
    try {
      if (args.length > 1) {
        printUsage();
        return;
      }

      PathfinderTestDriver td;

      if (args.length == 0) {
        td = new PathfinderTestDriver(new InputStreamReader(System.in),
                new OutputStreamWriter(System.out));
      } else {

        String fileName = args[0];
        File tests = new File(fileName);

        if (tests.exists() || tests.canRead()) {
          td = new PathfinderTestDriver(new FileReader(tests),
                  new OutputStreamWriter(System.out));
        } else {
          System.err.println("Cannot read from " + tests.toString());
          printUsage();
          return;
        }
      }

      td.runTests();

    } catch (IOException e) {
      System.err.println(e.toString());
      e.printStackTrace(System.err);
    }
  }

  private static void printUsage() {
    System.err.println("Usage:");
    System.err.println("to read from a file: java graph.specTest.GraphTestDriver <name of input script>");
    System.err.println("to read from standard in: java graph.specTest.GraphTestDriver");
  }

  /**
   * String -> Graph: maps the names of graphs to the actual graph
   **/
  private final Map<String, Graph<String, Double>> graphs = new HashMap<String, Graph<String, Double>>();
  private final PrintWriter output;
  private final BufferedReader input;

  /**
   * @requires r != null && w != null
   * @effects Creates a new GraphTestDriver which reads command from
   * <tt>r</tt> and writes results to <tt>w</tt>.
   **/
  public PathfinderTestDriver(Reader r, Writer w) {
    input = new BufferedReader(r);
    output = new PrintWriter(w);
  }

  /**
   * @throws IOException if the input or output sources encounter an IOException
   * @effects Executes the commands read from the input and writes results to the output
   **/
  public void runTests()
          throws IOException {
    String inputLine;
    while ((inputLine = input.readLine()) != null) {
      if ((inputLine.trim().length() == 0) ||
              (inputLine.charAt(0) == '#')) {
        // echo blank and comment lines
        output.println(inputLine);
      } else {
        // separate the input line on white space
        StringTokenizer st = new StringTokenizer(inputLine);
        if (st.hasMoreTokens()) {
          String command = st.nextToken();

          List<String> arguments = new ArrayList<String>();
          while (st.hasMoreTokens()) {
            arguments.add(st.nextToken());
          }

          executeCommand(command, arguments);
        }
      }
      output.flush();
    }
  }

  private void executeCommand(String command, List<String> arguments) {
    try {
      if (command.equals("CreateGraph")) {
        createGraph(arguments);
      } else if (command.equals("AddNode")) {
        addNode(arguments);
      } else if (command.equals("AddEdge")) {
        addEdge(arguments);
      } else if (command.equals("ListNodes")) {
        listNodes(arguments);
      } else if (command.equals("ListChildren")) {
        listChildren(arguments);
      } else if (command.equals("FindPath")) {
        findPath(arguments);
      } else {
        output.println("Unrecognized command: " + command);
      }
    } catch (Exception e) {
      output.println("Exception: " + e.toString());
    }
  }

  private void findPath(List<String> arguments) {
    if (arguments.size() != 3) {
      throw new CommandException("Bad arguments to findPath: " + arguments);
    }
    String graphName = arguments.get(0);
    String node1 = arguments.get(1);
    String node2 = arguments.get(2);
    findPath(graphName, node1, node2);
  }

  private void findPath(String graphName, String node1, String node2) {
    Graph<String, Double> g = graphs.get(graphName);
    GraphNode<String, Double> start = g.getNode(node1);
    GraphNode<String, Double> dest = g.getNode(node2);
    output.println("path from " + node1 + " to " + node2 + ":");
    if (start == null) {
      output.println("unknown node " + node1);
    }
    if (dest == null && !node1.equals(node2)) {
      output.println("unknown node " + node2);
    }
    if (start != null && dest != null) {
      Path<String> path = DijkstraAlgorithm.findMinPath(node1, node2, g);
      if (path == null) {
        output.println("no path found");
      } else {
        double totalCost = 0.0;
        Iterator<Path<String>.Segment> pathIterator = path.iterator();
        while (pathIterator.hasNext()) {
          Path<String>.Segment curr = pathIterator.next();
          //System.out.println(curr.getStart());
          totalCost = totalCost + curr.getCost();
          output.println(curr.getStart() + " to " + curr.getEnd() +
                  String.format(" with weight %.3f", curr.getCost()));
        }
        output.println(String.format("total cost: %.3f", totalCost));
      }
    }
  }

  private void createGraph(List<String> arguments) {
    if (arguments.size() != 1) {
      throw new CommandException("Bad arguments to CreateGraph: " + arguments);
    }

    String graphName = arguments.get(0);
    createGraph(graphName);
  }

  private void createGraph(String graphName) {
    Graph<String, Double> g = new Graph<String, Double>();
    graphs.put(graphName, g);
    output.println("created graph " + graphName);
  }

  private void addNode(List<String> arguments) {
    if (arguments.size() != 2) {
      throw new CommandException("Bad arguments to addNode: " + arguments);
    }

    String graphName = arguments.get(0);
    String nodeName = arguments.get(1);

    addNode(graphName, nodeName);
  }

  private void addNode(String graphName, String nodeName) {
    Graph<String, Double> g = graphs.get(graphName);
    GraphNode<String, Double> n = new GraphNode<String, Double>(nodeName);
    g.addNode(n);
    output.println("added node " + nodeName + " to " + graphName);
  }

  private void addEdge(List<String> arguments) {
    if (arguments.size() != 4) {
      throw new CommandException("Bad arguments to addEdge: " + arguments);
    }

    String graphName = arguments.get(0);
    String parentName = arguments.get(1);
    String childName = arguments.get(2);
    String edgeLabel = arguments.get(3);

    addEdge(graphName, parentName, childName, edgeLabel);
  }

  private void addEdge(String graphName, String parentName, String childName,
                       String edgeLabel) {
    Graph<String, Double> g = graphs.get(graphName);
    GraphNode<String, Double> n = new GraphNode<String, Double>(parentName);
    GraphNode<String, Double> n1 = new GraphNode<String, Double>(childName);
    for (GraphNode<String, Double> node : g.listNodes()) {
      if (node.getLabel().equals(n.getLabel())) {
        n = node;
      }
      if (node.getLabel().equals(n1.getLabel())) {
        n1 = node;
      }
    }
    double label = Double.valueOf(edgeLabel);
    GraphEdge<Double, String> e = new GraphEdge<Double, String>(label, n, n1);
    g.addEdge(e);
    output.println(String.format("added edge %.3f", label) + " from " + parentName + " to " +
            childName + " in " + graphName);
  }

  private void listNodes(List<String> arguments) {
    if (arguments.size() != 1) {
      throw new CommandException("Bad arguments to listNodes: " + arguments);
    }

    String graphName = arguments.get(0);
    listNodes(graphName);
  }

  private void listNodes(String graphName) {
    Graph<String, Double> g = graphs.get(graphName);
    List<GraphNode<String, Double>> sortedList = new ArrayList<GraphNode<String, Double>>(g.listNodes());
    Comparator<GraphNode<String, Double>> byLabel = Comparator.comparing(GraphNode<String, Double>::getLabel);
    sortedList.sort(byLabel);
    output.print(graphName + " contains:");
    for (GraphNode<String, Double> n : sortedList) {
      output.print(" " + n.getLabel());
    }
    output.println();
  }

  private void listChildren(List<String> arguments) {
    if (arguments.size() != 2) {
      throw new CommandException("Bad arguments to listChildren: " + arguments);
    }

    String graphName = arguments.get(0);
    String parentName = arguments.get(1);
    listChildren(graphName, parentName);
  }

  private void listChildren(String graphName, String parentName) {
    Graph<String, Double> g = graphs.get(graphName);
    output.print("the children of " + parentName + " in " + graphName + " are:");
    GraphNode<String, Double> parent = new GraphNode<String, Double>(parentName);
    for (GraphNode<String, Double> n : g.listNodes()) {
      if (n.getLabel().equals(parentName)) {
        parent = n;
      }
    }
    List<String> children = new LinkedList<>();
    for (GraphEdge<Double, String> e : parent.getOutGoing()) {
      if (!e.getDestination().equals(parent)){
        children.add(" " + e.getDestination().getLabel() + "(" + e.getLabel() + ")");
      }
    }
    Collections.sort(children);
    for (String s : children) {
      output.print(s);
    }
    output.println();
  }

  /**
   * This exception results when the input file cannot be parsed properly
   **/
  static class CommandException extends RuntimeException {

    public CommandException() {
      super();
    }

    public CommandException(String s) {
      super(s);
    }

    public static final long serialVersionUID = 3495;
  }
}
