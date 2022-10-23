import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {

        MaxMinHeap heap = new MaxMinHeap(false);

        heap.add(4);
        heap.add(5);
        heap.add(7);
        heap.add(13);
        heap.add(2);
        heap.add(23);
        heap.add(6);
        heap.add(8);

        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.peek());
        heap.add(-1);
        System.out.println(heap.peek());




        System.out.println("Hello world!");
    }
}