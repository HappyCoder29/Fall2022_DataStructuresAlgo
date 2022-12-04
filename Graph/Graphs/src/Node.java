import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Node {
    public String name;
    public boolean isVisited = false;
    public LinkedList<Edge> listEdges;
    public int rank;
    public Node parent;


    public Node(String name){
        this.name = name;
        this.listEdges = new LinkedList<>();
        this.parent = this;
        this.rank = 0;
    }

    public ArrayList<String> getNeighbours(){
        ArrayList<String> neighbours = new ArrayList<String>();

        for(int i = 0 ; i < listEdges.size(); i ++){
            neighbours.add(listEdges.get(i).endNode);
        }
        return  neighbours;
    }

}
