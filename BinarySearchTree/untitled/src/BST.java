import javax.swing.text.BoxView;
import java.util.*;

public class BST <T extends Comparable<T>> {

    public Node<T> root;
    public BST(){
    }
    public void preOrder(){
        preOrder(root);
        System.out.println();
    }
    private void preOrder(Node<T> node){
        if(node != null){
            System.out.printf(node.data + ", ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void inOrder(){
        inOrder(root);
        System.out.println();
    }
    private void inOrder(Node<T> node){
        if(node != null){
            inOrder(node.left);
            System.out.printf(node.data + ", ");
            inOrder(node.right);
        }
    }

    public void postOrder(){
        postOrder(root);
        System.out.println();
    }
    private void postOrder(Node<T> node){
        if(node != null){
            postOrder(node.left);
            postOrder(node.right);
            System.out.printf(node.data + ", ");
        }
    }


    public void reverseInOrder(){
        reverseInOrder(root);
        System.out.println();
    }
    private void reverseInOrder(Node<T> node){
        if(node != null){
            reverseInOrder(node.right);
            System.out.printf(node.data + ", ");
            reverseInOrder(node.left);
        }
    }


    public Node<T> getSmallest(){

        if(root == null){
            return null;
        }
        Node<T> curr = root;
        while(curr.left != null){
            curr = curr.left;
        }
        return curr;

    }

    public Node<T> getLargest(){

        if(root == null){
            return null;
        }
        Node<T> curr = root;
        while(curr.right != null){
            curr = curr.right;
        }
        return curr;

    }

    public boolean search(T value){
        if(root == null || value == null){
            return false;
        }

        Node<T> current = root;

        while(current!= null){
            if(current.data.compareTo(value) == 0){
                return true;
            }
            else if (current.data.compareTo(value) < 0){
                current = current.right;
            }else{
                current = current.left;
            }
        }
        return false;



    }

    public void insert(T data){
        Node<T> newNode = new Node<>(data);

        if(root == null){
            root = newNode;
            return;
        }

        Node<T> curr = root;
        Node<T> parent = null;

        while(curr != null){

            parent = curr;
            if( curr.data.compareTo(data) > 0 ){
                curr = curr.left;
            }else{
                curr = curr.right;
            }
        }

        if( parent.data.compareTo(data) > 0 ) {
            parent.left = newNode;
        }else{
            parent.right = newNode;
        }

    }

    public void levelOrder(){
        if(root == null){
            return;
        }

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

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
            }
            else{
                // We have reached to a new level
                System.out.println();
                if(queue.size() == 0){
                    break;
                }
                queue.add(null);
            }
        }
    }

    public ArrayList<T> getSortedList(){

        if(root == null){
            return null;
        }
        ArrayList<T> list = new ArrayList<>();

        getSortedList(root, list);
        return list;

    }

    private void getSortedList(Node<T> node, ArrayList<T> list){
        if(node != null){
            getSortedList(node.left, list);
            list.add(node.data);
            getSortedList(node.right, list);

        }
    }

    public ArrayList<T> getReverseSortedList(){

        if(root == null){
            return null;
        }
        ArrayList<T> list = new ArrayList<>();

        getReverseSortedList(root, list);
        return list;

    }

    private void getReverseSortedList(Node<T> node, ArrayList<T> list){
        if(node != null){
            getReverseSortedList(node.right, list);
            list.add(node.data);
            getReverseSortedList(node.left, list);

        }
    }

    public Node<T> createBSTFromSortedArray(T[] arr){
        if(arr == null || arr.length == 0){
            return null;
        }
        if(arr.length == 1){
            return new Node<>(arr[0]);
        }

        return createBSTFromSortedArray(0, arr.length -1, arr);
    }

    private Node<T> createBSTFromSortedArray(int start, int end, T[] arr){
        if(start > end){
            return null;
        }

        int mid = (start + end)/2;
        Node<T> node = new Node<>(arr[mid]);
        node.left = createBSTFromSortedArray(start, mid -1, arr);
        node.right = createBSTFromSortedArray(mid +1, end, arr);
        return node;
    }


    public Node<T> inOrderPredecessor(Node<T> node){
        if(node == null){
            return null;
        }

        Box<Node<T>> predNode = new Box<>();
        predNode.data = null;
        Box<Boolean> found = new Box<>();
        found.data = false;
        inOrderPredecessor(root, node, found, predNode);
        return predNode.data;
    }


    private void inOrderPredecessor(Node<T> node, Node<T> value, Box<Boolean> found, Box<Node<T>> predNode){
        if(node != null && found.data == false){
            inOrderPredecessor(node.left, value,found,  predNode );

            if(node.data == value.data){
                found.data = true;
            }
            if(found.data == false){
                predNode.data = node;
            }

            inOrderPredecessor(node.right,value, found,  predNode);
        }
    }

    private ArrayList<Node<T>> getSearchPath(Node<T> node1){
        ArrayList<Node<T>> list = new ArrayList<>();
        Node<T> current = root;
        while(current!= null){
            list.add(current);
            if(current.data.compareTo(node1.data) == 0){
               break;
            }
            else if (current.data.compareTo(node1.data) < 0){
                current = current.right;
            }else{
                current = current.left;
            }
        }

        return list;

    }

    public Node<T> LowestCommonAncestor(Node<T> node1, Node<T> node2){
        ArrayList<Node<T>> list1 = getSearchPath(node1);
        ArrayList<Node<T>> list2 = getSearchPath(node2);
        int index1 = 0;
        int index2 = 0;
        Node lca = root;
        while(index1 < list1.size() && index2 < list2.size()){
            if(list1.get(index1).data != list2.get(index2).data){
                return lca;
            }
            lca = list1.get(index1);
            index1++;
            index2 ++;
        }
        return null;

    }


    public Node<T> LCAWithParentNode(Node<T> node1, Node<T> node2){

        HashSet<Node<T>> set = new HashSet<>();

        while(node1 != root && node2 != root){
            if(set.contains(node1)){
                return node1;
            }
            if(set.contains(node2)){
                return node2;
            }

            set.add(node1);
            set.add(node2);
            node1 = node1.parent;
            node2 = node2.parent;

        }
        return null;
    }

    public Node<T> LCARecursive(Node<T> node1, Node<T> node2){
        return LCARecursive(root, node1,node2);
    }

    private Node<T> LCARecursive(Node<T> currentNode, Node<T> node1, Node<T> node2){
        if(currentNode == null){
            return null;
        }
        if(currentNode == node1 || currentNode == node2){
            return currentNode;
        }
        Node<T> left = LCARecursive(currentNode.left, node1, node2);
        Node<T> right = LCARecursive(currentNode.right, node1, node2);

        if(left != null && right != null){
            return currentNode;
        }

       if(left != null ){
           return left ;
       }

       return right;

    }


    public boolean validateBST(){
        return validateBST(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    private boolean validateBST(Node<T> node, Integer min, Integer max){
        if(node == null){
            return true;
        }
        // Do something
        if((Integer)node.data > max || (Integer)node.data < min){
            return false;
        }

        validateBST(node.left,min, (Integer) node.data );
        validateBST(node.right,(Integer) node.data , max);
        return true;
    }






}
