import java.awt.*;
import java.util.*;
import java.util.List;

public class AdjacencyGraph {
    public HashMap<String, Node> nodes;
    public AdjacencyGraph(){
        nodes = new HashMap<>();
    }

    public void addNode(String nodeName){
        if(nodes.containsKey(nodeName.toUpperCase())){
            return;
        }
        Node node = new Node(nodeName.toUpperCase());
        nodes.put(nodeName.toUpperCase(), node);
    }

    public void addEdge(String startNode, String endNode){

        if(!nodes.containsKey(startNode.toUpperCase()) || !nodes.containsKey(endNode.toUpperCase())){
            return;
        }
        Node node = nodes.get(startNode.toUpperCase());
        for (Edge edge: node.listEdges) {
            if(edge.endNode == endNode.toUpperCase()){
                return;
            }
        }
        Edge edge = new Edge(startNode.toUpperCase(),endNode.toUpperCase());
        node.listEdges.add(edge);
    }

    public void removeEdge(String startNode, String endNode){
        if(!nodes.containsKey(startNode) || !nodes.containsKey(endNode)){
            return;
        }
        Node node = nodes.get(startNode);
        node.listEdges.removeIf(edge -> edge.endNode == endNode.toUpperCase());
    }


    public void resetVisited(){
        for (Node node : nodes.values()) {
            node.isVisited = false;
        }
    }

    public void BFS(String startNode){
        if(!nodes.containsKey(startNode.toUpperCase())){
            return;
        }
        Node start = nodes.get(startNode);
        resetVisited();
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        queue.add(null);

        while(!queue.isEmpty()){
            Node node = queue.remove();
            if(node != null){
                // if node is not visited then visit the node
                if(node.isVisited == false){
                    System.out.printf(node.name + ", ");
                    node.isVisited = true;
                    // for all the neighbours of this node add them if they are not
                    // already visited
                    for (Edge edge : node.listEdges) {
                        Node neighbour = nodes.get(edge.endNode);
                        if(neighbour.isVisited == false){
                            queue.add(neighbour);
                        }
                    }// end of for edge
                }// if node is not visited
            }// if node is not null
            else{
                System.out.println();
                if(queue.isEmpty()){
                    break;
                }
                queue.add(null);
            }// end of else
        }// end of while loop
    }// end of function


    public void DFS(String startNode){
        if(!nodes.containsKey(startNode.toUpperCase())){
            return;
        }
        Node start = nodes.get(startNode);
        resetVisited();
        Stack<Node> stack = new Stack<>();
        stack.push(start);
        while(!stack.isEmpty()){
            Node node = stack.pop();
            // if node is not visited then visit the node
            if(node.isVisited == false){
                System.out.printf(node.name + ", ");
                node.isVisited = true;
                // for all the neighbours of this node add them if they are not
                // already visited
                for (Edge edge : node.listEdges) {
                    Node neighbour = nodes.get(edge.endNode);
                    if(neighbour.isVisited == false){
                        stack.push(neighbour);
                    }
                }// end of for edge
            }// if node is not visited
        }// end of while
    }// end of function

    public boolean isCyclic(){
        for (Node node : nodes.values()) {
            //System.out.println("Start Node= " + node.name);
            Stack<Node> stack = new Stack<>();
            Node startNode = nodes.get(node.name);
            BoxValue box = new BoxValue();
            if(containsCycle(startNode, stack, box)){
                return true;
            }
        }
        return false;
    }

    private boolean containsCycle(Node node, Stack<Node> stack, BoxValue box){
        if(stack.contains(node)){
            System.out.printf(node.name + " ");
            while(!stack.empty()){
                System.out.printf(stack.pop().name + " ");
            }
            System.out.println();
            box.value = true;
            return true;
        }
        stack.push(node);
        for (Edge edge : node.listEdges) {
            Node neighbour = nodes.get(edge.endNode);
            if(!containsCycle(neighbour, stack, box) && box.value == false){
                //System.out.printf(neighbour.name + " <- ");
            }else {
                box.value = true;
                return true;
            }
        }
        stack.pop();
        return false;
    }

    public void topologicalSorting(){
        ArrayList<String> list = new ArrayList<>();

        for (Node node : nodes.values()) {
            Stack<Node> stack = new Stack<>();
            topologicalSorting(node, stack);
            String str = "";
            while(!stack.isEmpty()){
                Node temp = stack.pop();
                str += temp.name + " -> ";
            }
            list.add(str);
        }

        for (String str : list) {
            System.out.println(str);
        }

    }


    public void topologicalSorting(String startNode){
        if(!nodes.containsKey(startNode.toUpperCase())){
            return;
        }
        Node node = nodes.get(startNode.toUpperCase());
        Stack<Node> stack = new Stack<>();
        // call topo sort recursive function
        topologicalSorting(node, stack);
        while(!stack.isEmpty()){
            Node temp = stack.pop();
            System.out.printf(temp.name + " -> ");
        }
        System.out.println();
    }

    private void topologicalSorting(Node node, Stack<Node> stack){
        for (String endStr : node.getNeighbours() ) {
            Node endNode = nodes.get(endStr);
            if(!stack.contains(endNode)){
                topologicalSorting(endNode, stack);
            }
        }
        stack.push(node);
    }

    public void topologicalSortingAllNodes(){
        Stack<Node> stack = new Stack<>();
        for (Node node : nodes.values()) {
            if(!stack.contains(node)){
                topologicalSorting(node, stack);
            }
        }

        while(!stack.isEmpty()){
            System.out.printf(stack.pop().name + " -> ");
        }
        System.out.println("Null");
    }

    public Node findParent(Node node){
        Node parent = node.parent;
        if(parent == node){
            return node;
        }
        node.parent = findParent(parent);
        return node.parent;
    }

    public void union(Node node1, Node node2){
        Node parent1 = findParent(node1);
        Node parent2 = findParent(node2);
        if(parent1 == parent2){
            return;
        }
        if(parent1.rank >= parent2.rank){
            if(parent1.rank == parent2.rank){
                parent1.rank ++;
            }
            parent2.parent = parent1;
        }else{
            parent1.parent = parent2;
        }
    }

    public boolean isCyclicUnionFind(){
        // get all the edges in a list
        ArrayList<Edge> allEdges = new ArrayList<>();
        for (Node node : nodes.values()) {
            for (Edge edge : node.listEdges) {
               allEdges.add(edge);
            }
        }

        for (Edge edge: allEdges) {
            Node start = nodes.get(edge.startNode);
            Node end = nodes.get(edge.endNode);
            if(findParent(start) == findParent(end)){
                return true;
            }
            union(start, end);
        }

        return false;
    }

    public void printAllPaths(String source, String dest){
        source = source.toUpperCase();
        dest = dest.toUpperCase();

        if(!nodes.containsKey(source) || !nodes.containsKey(dest) ){
            return;
        }
        LinkedList<String> visited = new LinkedList<>();
        printAllPaths(source, dest, visited);


    }

    private void printAllPaths(String current, String dest , LinkedList<String> visited){
        if(visited.contains(current)){
            return;
        }
        if(dest == current){
            for (String str : visited) {
                System.out.printf(str + " -> ");
            }
            System.out.println(dest);
        }
        visited.add(current);
        Node node = nodes.get(current);
        for (Edge edge : node.listEdges) {
          if( !visited.contains(edge.endNode)) {
              printAllPaths(edge.endNode, dest, visited);
          }
        }
        visited.remove(current);
    }


    public  boolean isReachable(String startNode, String endNode){
        startNode = startNode.toUpperCase();
        endNode = endNode.toUpperCase();
        if(!nodes.containsKey(startNode) || !nodes.containsKey(endNode)){
            return false;
        }
        resetVisited();
        Queue<Node> queue = new LinkedList<>();
        queue.add(nodes.get(startNode));

        while(!queue.isEmpty()){
            Node node = queue.remove();
            if(!node.isVisited){
                System.out.printf(node.name + " -> ");
                node.isVisited = true;
            }
            ArrayList<String> neighbours = node.getNeighbours();
            for (String str : neighbours) {
                if(nodes.get(str).name == endNode){
                    return true;
                }
                if(nodes.get(str).isVisited == false){
                    queue.add(nodes.get(str));
                }
            }
        }
        return false;

    }



}
