public class LLNode <T> {
    public T data;
    public LLNode<T> next;

    public  LLNode(T data){
        this.data = data;
        this.next = null;
    }
}