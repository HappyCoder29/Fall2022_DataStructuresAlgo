public class LinkListCycle {
    public Node<Integer> head;

    public  LinkListCycle (){
        createCycleList();
    }

    private void createCycleList(){
        head = new Node<>(1);
        head.next = new Node<>(2);
        head.next.next = new Node<>(3);
        head.next.next.next = new Node<>(4);
        head.next.next.next.next = new Node<>(5);
        head.next.next.next.next.next = new Node<>(6);
        head.next.next.next.next.next.next = new Node<>(7);
        head.next.next.next.next.next.next.next = new Node<>(8);

        head.next.next.next.next.next.next.next.next = head;
    }

    private void createNoCycleList(){
        head = new Node<>(1);
        head.next = new Node<>(2);
        head.next.next = new Node<>(3);
        head.next.next.next = new Node<>(4);
        head.next.next.next.next = new Node<>(5);
        head.next.next.next.next.next = new Node<>(6);
        head.next.next.next.next.next.next = new Node<>(7);
        head.next.next.next.next.next.next.next = new Node<>(8);

    }

    // O(n)
    public boolean containsCycle(){
        if(head == null || head.next == null ){
            return false;
        }
        Node<Integer> front = head;
        Node<Integer> back = head;

        while(back != null && back.next != null ){
            front = front.next;
            back = back.next.next;
            if(front == back){
                return true;
            }
        }
        return false;
    }


    // O(n)
    public Node<Integer> findStartOfCycle(){
        if(head == null || head.next == null ){
            return null;
        }
        Node<Integer> front = head;
        Node<Integer> back = head;

        boolean containsCycle = false;
        while(back != null && back.next != null ){
            front = front.next;
            back = back.next.next;
            if(front == back){
                containsCycle = true;
                break;
            }
        }

        if(!containsCycle){
            return null;
        }

        // There is a cycle

        front = head;

        while(front != back){
            front = front.next;
            back = back.next;
        }

        return front;
    }



}
