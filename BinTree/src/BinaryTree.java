import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
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


}
