import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BinaryTree <T>{
    public Node<T> root;
    public BinaryTree(){

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

    public void levelOrderSameLine(){
        if(root == null){
            return;
        }

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);

        while(queue.size() != 0){
            Node<T> node = queue.remove();
            System.out.printf(node.data + " , ");
            if(node.left!= null){
                queue.add(node.left);
            }
            if(node.right!= null){
                queue.add(node.right);
            }
        }
        System.out.println();
    }

    public int size(){
        return size(root);
    }

    public int size(Node<T> node){
        if(node == null){
            return 0;
        }
        return  size(node.left)  + size(node.right) + 1 ;
    }

    public int height(){
        return height(root);
    }

    public int height(Node<T> node){
        if(node == null){
            return 0;
        }
        return 1 +  Math.max( height(node.left), height(node.right)) ;
    }


    public void printLeftView(){
        if(root == null){
            return;
        }

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        boolean bPrint = true;

        while(queue.size() != 0){
            Node<T> node = queue.remove();

            if(node != null){
                if(bPrint == true){
                    System.out.println(node.data);
                    bPrint = false;
                }
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
                bPrint = true;
            }
        }
    }


    public void printRightView(){
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
                prevNode = node;
                if(node.left!= null){
                    queue.add(node.left);
                }
                if(node.right!= null){
                    queue.add(node.right);
                }
            }
            else{
                // We have reached to a new level
                System.out.println(prevNode.data);
                if(queue.size() == 0){
                    break;
                }
                queue.add(null);
            }
        }
    }


    public void printZigZag(){
        if(root == null){
            return;
        }

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        boolean bPrint = true;
        Stack<Node<T>> stack = new Stack<>();

        while(queue.size() != 0){
            Node<T> node = queue.remove();

            if(node != null){
                if(bPrint){
                    System.out.printf(node.data + " , ");
                }else{
                   stack.push(node);
                }
                if(node.left!= null){
                    queue.add(node.left);
                }
                if(node.right!= null){
                    queue.add(node.right);
                }
            }
            else{
                // We have reached to a new level
                while(!stack.empty()){
                    System.out.printf(stack.pop().data + ", ");
                }
                System.out.println();
                if(queue.size() == 0){
                    break;
                }
                queue.add(null);
                bPrint = !bPrint;
            }
        }
    }

    public void printTopLevelView(){
        if(root == null){
            return;
        }
        BoxValue<Integer> minValue = new BoxValue<>(0);
        BoxValue<Integer> maxValue = new BoxValue<>(0);

        HashMap<Integer,Node<T>> map = new HashMap<>();

        printTopView(root, 0, map, minValue, maxValue);

        for(int i = minValue.data ; i <= maxValue.data; i ++){
            System.out.printf(map.get(i).data + ", ");
        }
        System.out.println();

    }

    private void printTopView( Node<T> node,
                                    Integer distance,
                                    HashMap<Integer, Node<T>> map,
                                    BoxValue<Integer> minValue,
                                    BoxValue<Integer> maxValue){
        if(node != null){
            // adding value to the hash map
            if(!map.containsKey(distance)){
                map.put(distance, node);
            }
            if(distance < minValue.data){
                minValue.data = distance;
            }
            if(distance > maxValue.data){
                maxValue.data = distance;
            }
            printTopView(node.left, distance - 1, map, minValue, maxValue);
            printTopView(node.right, distance + 1, map, minValue, maxValue);

        }
    }


    public void printBottomView(){
        if(root == null){
            return;
        }
        BoxValue<Integer> minValue = new BoxValue<>(0);
        BoxValue<Integer> maxValue = new BoxValue<>(0);

        HashMap<Integer,Node<T>> map = new HashMap<>();

        printBottomView(root, 0, map, minValue, maxValue);

        for(int i = minValue.data ; i <= maxValue.data; i ++){
            System.out.printf(map.get(i).data + ", ");
        }
        System.out.println();

    }

    private void printBottomView( Node<T> node,
                                    Integer distance,
                                    HashMap<Integer, Node<T>> map,
                                    BoxValue<Integer> minValue,
                                    BoxValue<Integer> maxValue){
        if(node != null){
            // adding value to the hash map
             map.put(distance, node);
            if(distance < minValue.data){
                minValue.data = distance;
            }
            if(distance > maxValue.data){
                maxValue.data = distance;
            }
            printBottomView(node.left, distance - 1, map, minValue, maxValue);
            printBottomView(node.right, distance + 1, map, minValue, maxValue);

        }
    }

    public void printBoundary(){
        Stack<Node<T>> stack = new Stack<>();
        printBoundary(root, 0, 0, stack);
        while(!stack.empty()){
            System.out.printf(stack.pop().data + ", ");
        }
        System.out.println();
    }

    private void printBoundary(Node<T> node, int left, int right, Stack<Node<T>> stack){

        if(node != null){
            // figure out which nodes to print
            if(node.left == null && node.right == null){
                // leaf node print it
                System.out.printf(node.data + ", ");
            }else if( right == 0 ){
                System.out.printf(node.data + ", ");
            }else if (left == 0){
                stack.push(node);
            }
            printBoundary(node.left, left +1, right, stack);
            printBoundary(node.right, left, right + 1, stack);
        }
    }


    public void mirrorTree(){
        mirrorTree(root);
    }

    private void mirrorTree(Node<T> node){
        if(node != null){
            // Post order
            mirrorTree(node.left);
            mirrorTree(node.right);
            Node<T> temp = node.left;
            node.left = node.right;
            node.right = temp;
        }
    }

    public boolean areSameTrees(Node<T> node1, Node<T> node2){
        if(node1 == null && node2 == null){
            return true;
        }
        if(node1 == null || node2 == null){
            return false;
        }

        return node1.data == node2.data &&
                areSameTrees(node1.left, node2.left) &&
                areSameTrees(node1.right, node2.right);
    }

    public boolean areSymmetricTrees(Node<T> node1, Node<T> node2){
        if(node1 == null && node2 == null){
            return true;
        }
        if(node1 == null || node2 == null){
            return false;
        }

        return  areSymmetricTrees(node1.left, node2.left) &&
                areSymmetricTrees(node1.right, node2.right);
    }



    public void printAllPaths(){
        ArrayList<T> list = new ArrayList<>();
        printAllPaths(root, list, 0);
    }
    private void printAllPaths(Node<T> node, ArrayList<T> list, int index){
        if(node != null){
            list.add(index, node.data);
            if(node.left == null && node.right == null){
               for(int i = 0 ; i < index + 1; i ++) {
                    System.out.printf(list.get(i) + " -> ");
                }
                System.out.println("Null");
            }
            printAllPaths(node.left, list, index +1);
            printAllPaths(node.right, list, index +1);

        }
    }

    public ArrayList<ArrayList<T>> getAllPaths(){
        ArrayList<T> list = new ArrayList<>();
        ArrayList<ArrayList<T>> fullList = new ArrayList<>();

        getAllPaths(root, list,fullList, 0);
        return fullList;
    }

    private void getAllPaths(Node<T> node, ArrayList<T> list, ArrayList<ArrayList<T>> fullList, int index){
        if(node != null){
            list.add(index, node.data);
            if(node.left == null && node.right == null){
                ArrayList<T> path = new ArrayList<>();
                for(int i = 0 ; i < index + 1; i ++) {
                    path.add(i, list.get(i));
                }
                fullList.add(path);
            }
            getAllPaths(node.left, list, fullList, index +1);
            getAllPaths(node.right, list, fullList, index +1);
        }
    }

    public boolean hasPathSum(int sum){
        ArrayList<T> list = new ArrayList<>();
        BoxValue<Boolean> hasPath = new BoxValue<>(false);
        hasPathSum(root, hasPath,list, sum, 0);
        return hasPath.data;
    }

    private void hasPathSum(Node<T> node, BoxValue<Boolean> hasPath, ArrayList<T> list,  int sum, int index) {

        if (node != null && hasPath.data == false) {
            // Do something
            sum = sum - (Integer) node.data;
            list.add(index, node.data);
            if (node.left == null && node.right == null) {
                if (sum == 0) {
                    hasPath.data = true;
                    for(int i = 0 ; i < index + 1; i ++) {
                        System.out.printf(list.get(i) + " -> ");
                    }
                    System.out.println("Null");
                }
            }
            hasPathSum(node.left, hasPath, list, sum, index + 1);
            hasPathSum(node.right, hasPath, list, sum, index + 1);
        }
    }

    public Integer maxPathSum(){
        ArrayList<T> list = new ArrayList<>();
        BoxValue<Integer> boxMaxValue = new BoxValue<>(0);
        maxPathSum(root, list, boxMaxValue, 0);
        return boxMaxValue.data;
    }
    private void maxPathSum(Node<T> node,ArrayList<T> list, BoxValue<Integer> boxMaxValue,  int index ){
        if(node != null){
            list.add(index, node.data);
            if(node.left == null && node.right == null){
                Integer value = 0;
                for(int i = 0 ; i < index + 1; i ++) {
                    value += (Integer) list.get(i);
                }
                if(value > boxMaxValue.data){
                    boxMaxValue.data = value;
                }

            }
            maxPathSum(node.left, list, boxMaxValue,  index +1);
            maxPathSum(node.right, list, boxMaxValue, index +1);

        }
    }

    public int diameter(){
        return diameter(root);
    }
    private int diameter(Node<T> node){
        if(node == null){
            return 0;
        }

        int lHeight = height(node.left);
        int rHeight = height(node.right);

        int lDiameter = height(node.left);
        int rDiameter = height(node.right);

        return  Math.max(lHeight + rHeight + 1 , Math.max(lDiameter, rDiameter));

    }

    public int sumOfLeftleaves(){
        BoxValue<Integer> box = new BoxValue<>(0);
        sumOfLeftleaves(root, box);
        return box.data;
    }

    public void sumOfLeftleaves(Node<T> node, BoxValue<Integer> box){
        if(node != null){
            // If Node.left exist and is a leaf node
            if( node.left != null && ( node.left.left == null && node.left.right == null) ){
                box.data += (Integer) node.left.data;
            }

            sumOfLeftleaves(node.left, box);
            sumOfLeftleaves(node.right, box);

        }
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
