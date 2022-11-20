public class Main {
    public static void main(String[] args) {

        AdjacencyGraph graph = new AdjacencyGraph();
        populateGraph(graph);
        //graph.DFS("A");
        System.out.println(graph.isCyclic());
        System.out.println("");
    }

    private static void populateGraph(AdjacencyGraph graph){
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addNode("F");

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "E");
        graph.addEdge("E", "D");
        graph.addEdge("D", "B");
        graph.addEdge("E", "F");


    }
}