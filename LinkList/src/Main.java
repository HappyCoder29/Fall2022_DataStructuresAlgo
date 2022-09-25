import java.sql.PreparedStatement;
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

//        LinkList<Integer> list = new LinkList<>();
//        list.head = createNoCycleList();
//
//        list.zipMerge();
//        list.printList();
////
////        boolean palindrome = list.isPalindrome();
////        System.out.println(palindrome);
//
//       // Node<Integer> secondHalf = list.breakListInHalf();
//
//        //System.out.println( list.length() );

        // Class 2

//        LinkList<Integer> list1 = new LinkList<>();
//        LinkList<Integer> list2 = new LinkList<>();
//
//        list1.addNodeToTail(1);
//        list1.addNodeToTail(2);
//        list1.addNodeToTail(3);
//        list1.addNodeToTail(4);
//
//        list2.addNodeToTail(6);
//        list2.addNodeToTail(5);
//        createIntersectionOfTwoList(list1, list2);
//        Node<Integer> intersection = getIntersectingNode(list1, list2);
//        //addResult.printList();

//        LinkListRandom<Integer> list = createRandomLinkList();
//        LinkListRandom<Integer> copy = list.createCopy();

//        LinkList<Integer> list = new LinkList<>();
//        Integer[] arr = {1,2,3,4,5,6};
//        list.addNodesFromArray(arr);
//        list.swapNodesInPairs();
//        list.printList();

//        LinkList<Integer> list = new LinkList<>();
//        Integer[] arr = {1,2,3,4,5,6,7};
//        list.addNodesFromArray(arr);
//        list.reverseInGroupOfK(3);

//        LinkList<Integer> list1 = new LinkList<>();
//        Integer[] arr = {1,3,5};
//        list1.addNodesFromArray(arr);
//        LinkList<Integer> list2 = new LinkList<>();
//        Integer[] arr2 = {2,4,6};
//        list2.addNodesFromArray(arr2);
//        Node<Integer> sorted = mergeSorted(list1.head, list2.head);
        LinkList<Integer> list = new LinkList<>();
        Integer[] arr = {1,2,3,4,5,6};
        list.addNodesFromArray(arr);
        list.rotateList(2);
        list.printList();

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


    private static LinkList<Integer> addTwoNumbers(LinkList<Integer> list1, LinkList<Integer> list2){

        /// I am assuming both list is not empty
        LinkList<Integer> addResult = new LinkList<>();
        Node<Integer> temp1 = list1.reverseList(list1.head);
        Node<Integer> temp2 = list2.reverseList(list2.head);
        int carry = 0;

        // List is not cyclic
        while(temp1 != null || temp2 != null){
            int result = 0;
            if(temp1 != null){
                result += temp1.data;
                temp1 = temp1.next;
            }
            if(temp2 != null){
                result += temp2.data;
                temp2 = temp2.next;
            }
            result += carry;

            if (result >= 10){
                result = result % 10;
                carry = 1;
            }else{
                carry = 0;
            }
            addResult.addNodeToHead(result);
        }
        if(carry ==1 ){
            addResult.addNodeToHead(1);
        }
        return  addResult;
    }


    private static void createIntersectionOfTwoList(LinkList<Integer> list1, LinkList<Integer> list2){
        Node<Integer> temp = list1.head;
        temp = temp.next; // Now temp points to 2

        list2.head.next.next = temp;

    }

    private static Node<Integer> getIntersectingNode(LinkList<Integer> list1, LinkList<Integer> list2) {
        if(list1 == null || list2 == null){
            return null;
        }
        int len1 = list1.length();
        int len2 = list2.length();

        Node<Integer> temp1 = list1.head;
        Node<Integer> temp2 = list2.head;

        // increment either the temp1 or temp2 so that distance from the ponters to end of list is equal
        if (len1 > len2){
            int count = len1 - len2;
            while(count > 0){
                temp1 = temp1.next;
                count --;
            }
        }else{
            int count = len2 - len1;
            while(count > 0){
                temp2 = temp2.next;
                count --;
            }
        }

        while(temp1 != null && temp2 != null){

            if(temp1 == temp2){
                return temp1;
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        // No intersection
        return  null;





    }


    private  static  LinkListRandom<Integer> createRandomLinkList(){
        LinkListRandom<Integer> list = new LinkListRandom<>();
        NodeRandom<Integer> one = new NodeRandom<>(1);
        NodeRandom<Integer> two = new NodeRandom<>(2);
        NodeRandom<Integer> three = new NodeRandom<>(3);
        NodeRandom<Integer> four = new NodeRandom<>(4);

        one.next = two;
        two.next = three;
        three.next = four;
        four.next = null;

        one.random = three;
        two.random = two;
        three.random = one;
        four.random = three;

        list.head = one;
        return  list;
    }


    private static Node<Integer> mergeSorted(Node<Integer> node1, Node<Integer> node2){
        Node<Integer> result = null;
        if(node1 == null){
            return node2;
        }
        if(node2 == null){
            return node1;
        }
        if(node1.data < node2.data){
            result = node1;
            result.next = mergeSorted(node1.next, node2);
        }else{
            result = node2;
            result.next = mergeSorted(node1, node2.next);
        }
        return result;
    }







}