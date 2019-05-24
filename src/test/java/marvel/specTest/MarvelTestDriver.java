package marvel.specTest;

import java.io.*;
import java.util.*;
import graph.*;
import marvel.MarvelParser;
import marvel.MarvelPaths;
import org.apache.commons.collections.comparators.ComparableComparator;

/**
 * This class implements a testing driver which reads test scripts
 * from files for testing Graph.
 **/
public class MarvelTestDriver {

  public static void main(String args[]) {
    try {
      if (args.length > 1) {
        printUsage();
        return;
      }

      MarvelTestDriver td;

      if (args.length == 0) {
        td = new MarvelTestDriver(new InputStreamReader(System.in),
                new OutputStreamWriter(System.out));
      } else {

        String fileName = args[0];
        File tests = new File(fileName);

        if (tests.exists() || tests.canRead()) {
          td = new MarvelTestDriver(new FileReader(tests),
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
  private final Map<String, Graph<String, String>> graphs = new HashMap<String, Graph<String, String>>();
  private final PrintWriter output;
  private final BufferedReader input;

  /**
   * @requires r != null && w != null
   * @effects Creates a new GraphTestDriver which reads command from
   * <tt>r</tt> and writes results to <tt>w</tt>.
   **/
  public MarvelTestDriver(Reader r, Writer w) {
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
      } else if (command.equals("LoadGraph")) {
        loadGraph(arguments);
      } else if (command.equals("FindPath")) {
        findPath(arguments);
      } else {
        output.println("Unrecognized command: " + command);
      }
    } catch (Exception e) {
      output.println("Exception: " + e.toString());
    }
  }

  private void loadGraph(List<String> arguments) {
    if (arguments.size() != 2) {
      throw new CommandException("Bad arguments to loadGraph: " + arguments);
    }
    String graphName = arguments.get(0);
    String filename = "src/test/resources/marvel/data/" + arguments.get(1);
    loadGraph(graphName, filename);
  }

  private void loadGraph(String graphName, String filename) {
    Map<String, List<String>> map = MarvelParser.parseData(filename);
    Graph<String, String> g = MarvelPaths.makeGraph(map);
    graphs.put(graphName, g);
    output.println("loaded graph " + graphName);
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
    node1 = node1.replace('_', ' ');
    node2 = node2.replace('_', ' ');
    Graph<String, String> g = graphs.get(graphName);
    List<GraphNode<String, String>> nodes = MarvelPaths.findPath(node1, node2, g);
    output.println("path from " + node1 + " to " + node2 + ":");
    if (nodes == null) {
      output.println("no path found");
    } else if ((nodes.size() >= 1) && nodes.get(0).getLabel().equals("Bad start node")) {
      output.println("unknown character " + node1);
    } else if ((nodes.size() >= 1) && nodes.get(0).getLabel().equals("Bad destination node")) {
      output.println("unknown character " + node2);
    } else if ((nodes.size() - 1 >= 1) && (nodes.get(0).getLabel().equals("Bad destination node") ||
            nodes.get(1).getLabel().equals("Bad destination node"))) {
      output.println("unknown character " + node2);
    }
    else if (!nodes.isEmpty()){
      GraphNode<String, String> start = null;
      for (GraphNode<String, String> n : g.listNodes()) {
        if (n.getLabel().equals(node1)) {
          start = n;
        }
      }
      nodes.add(0, start);
      for (int i = 0; i < nodes.size()-1; i++) {
        output.print(nodes.get(i).getLabel() + " to " + nodes.get(i+1).getLabel());
        boolean printed = false;
        for(GraphEdge<String, String> e : nodes.get(i).getOutGoing()) {
          if (!printed && e.getDestination().equals(nodes.get(i+1))) {
            output.print(" via " + e.getLabel());
            printed = true;
          }
        }
        output.println();
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
    Graph<String, String> g = new Graph<String, String>();
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
    Graph<String, String> g = graphs.get(graphName);
    GraphNode<String, String> n = new GraphNode<String, String>(nodeName);
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
    Graph<String, String> g = graphs.get(graphName);
    GraphNode<String, String> n = new GraphNode<String, String>(parentName);
    GraphNode<String, String> n1 = new GraphNode<String, String>(childName);
    for (GraphNode<String, String> node : g.listNodes()) {
      if (node.getLabel().equals(n.getLabel())) {
        n = node;
      }
      if (node.getLabel().equals(n1.getLabel())) {
        n1 = node;
      }
    }
    GraphEdge<String, String> e = new GraphEdge<String, String>(edgeLabel, n, n1);
    g.addEdge(e);
    output.println("added edge " + edgeLabel + " from " + parentName + " to " +
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
    Graph<String, String> g = graphs.get(graphName);
    List<GraphNode<String, String>> sortedList = new ArrayList<GraphNode<String, String>>(g.listNodes());
    Comparator<GraphNode<String, String>> byLabel = Comparator.comparing(GraphNode<String, String>::getLabel);
    sortedList.sort(byLabel);
    output.print(graphName + " contains:");
    for (GraphNode<String, String> n : sortedList) {
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
    Graph<String, String> g = graphs.get(graphName);
    output.print("the children of " + parentName + " in " + graphName + " are:");
    GraphNode<String, String> parent = new GraphNode<String, String>(parentName);
    for (GraphNode<String, String> n : g.listNodes()) {
      if (n.getLabel().equals(parentName)) {
        parent = n;
      }
    }
    List<String> children = new LinkedList<>();
    for (GraphEdge<String, String> e : parent.getOutGoing()) {
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