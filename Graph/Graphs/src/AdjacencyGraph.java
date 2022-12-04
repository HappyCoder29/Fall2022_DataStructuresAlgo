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



}
