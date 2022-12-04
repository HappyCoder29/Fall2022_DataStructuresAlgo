public class Main {
    public static void main(String[] args) {

        int INF = Integer.MAX_VALUE;
//        AdjacencyGraph graph = new AdjacencyGraph();
//        populateGraph(graph);
//        //graph.DFS("A");
//        System.out.println(graph.isCyclic());

//        AdjacencyGraph graph = new AdjacencyGraph();
//        populateTopologicalSortGraph(graph);
        //graph.topologicalSorting("4");
        // graph.topologicalSorting();

//        AdjacencyGraph graph = new AdjacencyGraph();
//        populateDirectedCyclicGraph(graph);
//        System.out.println(graph.isCyclicUnionFind());

//        AdjacencyGraph graph = new AdjacencyGraph();
//        String[] arr = {"wrt","wrf","er","ett","rftt"};
//        populateDictionaryAlienDictionary(arr, graph);
//
//        graph.topologicalSortingAllNodes();

//        AdjacencyGraph graph = new AdjacencyGraph();
//        populatePrintAllPathsGraph(graph);
//        graph.printAllPaths("2", "3");

        int[][] graph = new int[][] {
                {INF,5, INF, INF },
                {INF, INF, 3, 2 },
                {1,INF, INF, 1 },
                {5,INF, 2, INF }
        };

        int[][] dist = floydWarshall(graph);
        printMatrix(dist);

        System.out.println("");
    }

    private static void printMatrix(int[][] matrix){
        int len = matrix.length;
        for(int i = 0 ; i < len; i ++){
            for(int j = 0 ; j < len; j ++){
                System.out.printf(matrix[i][j]  + "\t");
            }
            System.out.println();
        }
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

    private static void populateTopologicalSortGraph(AdjacencyGraph graph){
        graph.addNode("0");
        graph.addNode("1");
        graph.addNode("2");
        graph.addNode("3");
        graph.addNode("4");
        graph.addNode("5");
        graph.addNode("6");

        graph.addEdge("5", "0");
        graph.addEdge("4", "0");
        graph.addEdge("5", "2");
        graph.addEdge("2", "3");
        graph.addEdge("3", "6");
        graph.addEdge("3", "1");
        graph.addEdge("4", "1");
    }

    private static void populateDirectedCyclicGraph(AdjacencyGraph graph){
        graph.addNode("0");
        graph.addNode("1");
        graph.addNode("2");
        graph.addNode("3");
        graph.addNode("4");

        graph.addEdge("0", "1");
        graph.addEdge("1", "2");
        graph.addEdge("2", "4");
        graph.addEdge("2", "3");
        graph.addEdge("3", "0");

    }


    private static void populatePrintAllPathsGraph(AdjacencyGraph graph){
        graph.addNode("0");
        graph.addNode("1");
        graph.addNode("2");
        graph.addNode("3");

        graph.addEdge("0", "3");
        graph.addEdge("0", "2");
        graph.addEdge("0", "1");
        graph.addEdge("2", "0");
        graph.addEdge("2", "1");
        graph.addEdge("1", "3");

    }

    private static void populateDictionaryAlienDictionary(String[] arr, AdjacencyGraph graph){

        for (String str: arr) {
            for (Character ch : str.toCharArray()) {
                String strNode = ch.toString().toUpperCase();
                if(!graph.nodes.containsKey(strNode)){
                    graph.addNode(strNode);
                }
            }
        }

        for(int i = 0 ; i < arr.length -1; i ++){
            char[] start = arr[i].toCharArray();
            char[] end = arr[i +1].toCharArray();
            int current = 0;
            while( (current < start.length && current < end.length) &&  start[current] == end[current]){
                current++;
            }
            if(current < start.length && current < end.length){
                graph.addEdge(String.valueOf(start[current]), String.valueOf(end[current]));
            }
        }

    }

    private static int[][] floydWarshall(int[][] graph){
        int length = graph.length;
        int[][] dist = new int[length][length];
        for(int i = 0 ; i < length; i ++){
            for(int j = 0 ; j < length; j ++){
                dist[i][j] = graph[i][j];
            }
        }

        for(int k = 0 ; k < length; k ++){
            for(int i = 0 ; i < length; i ++) {
                for (int j = 0; j < length; j++) {
                    // If there is k where i->K and k-> j is smaller than i -> j
                    if(i == j){
                        continue;
                    }
                    if(dist[i][k] + dist[k][j] < dist[i][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        return dist;
    }
}