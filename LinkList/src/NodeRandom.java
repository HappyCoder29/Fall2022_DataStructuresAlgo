
public class NodeRandom <T> {
    public T data;
    public NodeRandom<T> next;
    public NodeRandom<T> random;

    public  NodeRandom(T data){
        this.data = data;
        this.next = null;
        this.random = null ;
    }
}