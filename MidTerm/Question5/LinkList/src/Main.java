public class Main {
    public static void main(String[] args) {
        LinkList<Integer> list = new LinkList<>();
        createCyclicList(list);
        System.out.println(list.isCyclic());
        System.out.println();

    }

    public static void createCyclicList(LinkList<Integer> list){
        list.append(1);
        list.appendToTail(2);
        list.appendToTail(3);
        list.appendToTail(4);
        list.appendToTail(5);
        list.appendToTail(6);
        list.appendToTail(7);
        list.head.next.next.next.next.next.next.next = list.head.next.next;

    }
}