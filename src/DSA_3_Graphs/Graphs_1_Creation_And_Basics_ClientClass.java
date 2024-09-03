package DSA_3_Graphs;

public class Graphs_1_Creation_And_Basics_ClientClass {
    public static void main(String[] args) {
        Graphs_1_Creation_And_Basics graph = new Graphs_1_Creation_And_Basics();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");

        graph.addEdge("A", "B", 2);
        graph.addEdge("A", "D", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("C", "D", 8);
        graph.addEdge("D", "E", 10);
        graph.addEdge("E", "F", 45);
        graph.addEdge("E", "G", 7);
        graph.addEdge("F", "G", 8);

        graph.display();

        System.out.println(graph.numberOfVertex());
        System.out.println(graph.containsVertex("G"));
        System.out.println(graph.numberOfEdges());

        System.out.println(graph.containsEdge("A", "C"));
        System.out.println(graph.containsEdge("E", "F"));

        graph.removeEdge("A", "B");
        graph.display();

        graph.removeVertex("F");
        graph.display();
    }
}
