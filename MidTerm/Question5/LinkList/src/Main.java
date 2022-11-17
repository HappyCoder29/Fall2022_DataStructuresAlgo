public class Main {
    public static void main(String[] args) {
        LinkList<Integer> list = new LinkList<>();
        list.append(5);
        list.appendToTail(-1);
        list.appendToTail(6);
        list.appendToTail(7);
        list.appendToTail(23);
        list.appendToTail(8);
        list.appendToTail(9);
        list.printList();
        System.out.println(list.length());
        System.out.println(list.getNthFromTheEnd(3));
        list.reverseList();
        list.printList();
        System.out.println();
    }
}