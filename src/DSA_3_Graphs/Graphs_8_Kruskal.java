package DSA_3_Graphs;

public class Graphs_8_Kruskal {
    public static void main(String[] args) {
        Graphs_1_Creation_And_Basics graph = new Graphs_1_Creation_And_Basics();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        System.out.println();
        graph.addEdge("A", "B", 2);
        graph.addEdge("A", "D", 10);
        graph.addEdge("B", "C", 3);
        graph.addEdge("C", "D", 1);
        graph.addEdge("D", "E", 8);
        graph.addEdge("E", "F", 5);
        graph.addEdge("E", "G", 6);
        graph.addEdge("F", "G", 4);

        graph.display();

        graph.kruskal();
    }
}
