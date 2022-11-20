import java.util.LinkedList;

public class Node {
    public String name;
    public boolean isVisited = false;
    public LinkedList<Edge> listEdges;

    public Node(String name){
        this.name = name;
        this.listEdges = new LinkedList<>();
    }


}
