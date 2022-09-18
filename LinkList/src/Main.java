import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
//        LinkList<Integer> list = new LinkList<Integer>();
//        list.addNodeToTail(5);
//        list.addNodeToTail(6);
//        list.addNodeToTail(8);
//        list.addNodeToTail(-3);
//        list.addNodeToTail(4);
//
//        LinkedList<Integer> defaultLinkedList = new LinkedList<>();
//
//        LinkListCycle listCycle = new LinkListCycle();
//
//        System.out.println(listCycle.containsCycle());
//
//        Node<Integer> startOfCycle = listCycle.findStartOfCycle();
//

        LinkList<Integer> list = new LinkList<>();
        list.head = createNoCycleList();

        list.zipMerge();
        list.printList();
//
//        boolean palindrome = list.isPalindrome();
//        System.out.println(palindrome);

       // Node<Integer> secondHalf = list.breakListInHalf();

        //System.out.println( list.length() );
        System.out.println();

    }

    private static Node<Integer> createCycleList(){
        Node<Integer> node  = new Node<>(1);
        node.next = new Node<>(2);
        node.next.next = new Node<>(3);
        node.next.next.next = new Node<>(4);
        node.next.next.next.next = new Node<>(5);
        node.next.next.next.next.next = new Node<>(6);
        node.next.next.next.next.next.next = new Node<>(7);
        node.next.next.next.next.next.next.next = new Node<>(8);

        node.next.next.next.next.next.next.next.next = node;
        return node;

    }

    private static Node<Integer> createNoCycleList(){
        Node<Integer> node = new Node<>(1);
        node.next = new Node<>(2);
        node.next.next = new Node<>(3);
        node.next.next.next = new Node<>(4);
       // node.next.next.next.next = new Node<>(4);
        node.next.next.next.next = new Node<>(5);
        node.next.next.next.next.next = new Node<>(6);
        node.next.next.next.next.next.next = new Node<>(7);
        return node;
    }



}