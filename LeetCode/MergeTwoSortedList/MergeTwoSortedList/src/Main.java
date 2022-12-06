public class Main {
    //https://leetcode.com/problems/merge-two-sorted-lists/
    public static void main(String[] args) {
        Node<Integer> node1 = getNode1();
        Node<Integer> node2 = getNode2();
        Node<Integer> sorted = mergeSortedNodes(node1, node2);


        System.out.println("Hello world!");
    }

    public static Node<Integer> getNode1(){
        Node<Integer> node = new Node<>(1);
        node.next = new Node<>(3);
        node.next.next = new Node<>(5);
        node.next.next.next = new Node<>(7);
        node.next.next.next.next = new Node<>(9);
        return node;
    }

    public static Node<Integer> getNode2(){
        Node<Integer> node = new Node<>(2);
        node.next = new Node<>(4);
        node.next.next = new Node<>(6);
        node.next.next.next = new Node<>(8);
        node.next.next.next.next = new Node<>(10);
        return node;
    }

    public static Node<Integer> mergeSortedNodes(Node<Integer> node1, Node<Integer> node2 ){
        Node<Integer> result = null;

        if(node1 == null ){
            return node2;
        }
        if(node2 == null){
            return node1;
        }
        if(node1.data < node2.data){
            result = node1;
            result.next = mergeSortedNodes(node1.next, node2);
        }else{
            result = node2;
            result.next = mergeSortedNodes(node1, node2.next);
        }
        return result;
    }
}