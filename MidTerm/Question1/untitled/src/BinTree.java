public class BinTree <T> {
    public Node<T> root;

    public BinTree() {

    }

    public void populateNextRight(){
        if(root == null){
            return;
        }

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        Node<T> prevNode = null;

        while(queue.size() != 0){
            Node<T> node = queue.remove();

            if(node != null){
                System.out.printf(node.data + " , ");
                if(node.left!= null){
                    queue.add(node.left);
                }
                if(node.right!= null){
                    queue.add(node.right);
                }
                if(prevNode != null){
                    prevNode.nextRight = node;
                }
                prevNode = node;
            }
            else{
                // We have reached to a new level
                System.out.println();
                if(queue.size() == 0){
                    break;
                }
                prevNode = null;
                queue.add(null);
            }
        }
        System.out.println();
    }
}