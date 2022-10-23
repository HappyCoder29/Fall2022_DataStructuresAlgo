import java.util.LinkedList;
import java.util.List;

public class Node {
    public Character ch;
    public boolean isTerminalCharacter;
    public List<Node> children;

    public Node(Character ch){
        this.ch = ch;
        this.isTerminalCharacter = false;
        children = new LinkedList<>();
    }
}
