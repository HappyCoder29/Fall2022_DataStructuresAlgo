public class LinkList <T>{
    public Node<T> head;

    public LinkList(){
        head = null;
    }


    public void append(T data){
        Node<T> node = new Node<>(data);
        if(head == null){
            head = node;
            return;
        }
        node.next = head;
        head = node;
    }

    public void appendToTail(T data){
        Node<T> node = new Node<>(data);
        if(head == null){
            head = node;
            return;
        }
        Node<T> temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = node;
    }

    public void printList(){
        Node<T> temp = head;
        while(temp != null){
            System.out.printf(temp.data + " -> ");
            temp  = temp.next;
        }
        System.out.println("NULL");

    }


    public int length(){
        Node<T> temp = head;
        int length = 0;
        while(temp != null){
            temp  = temp.next;
            length ++;
        }
        return length;
    }

    public Node<T> getNthFromTheEnd(int n){
        if(head == null || n<=0){
            return null;
        }

        Node<T> front = head;
        Node<T> back = head;
        int count = n;
        while(count != 0){
            if(front == null){
                return null;
            }
            front = front.next;
            count--;
        }
        while (front != null){
            front = front.next;
            back = back.next;
        }
        return back;
    }

    public void reverseList(){
        if(head == null || head.next == null){
            return;
        }
        Node<T> back = null;
        Node<T> mid = head;
        Node<T> front = head.next;

        while(front != null){
            mid.next = back;
            back = mid;
            mid = front;
            front = front.next;
        }

        mid.next = back;
        head = mid;
    }

    public boolean isCyclic(){
        if(head == null){
            return false;
        }
        Node<T> back = head;
        Node<T> front = head;
        while(front != null ){
            if(front != null && front.next != null){
                front = front.next.next;
                back = back.next;
            }else {
                return false;
            }
            if(front == back){
                return true;
            }
        }
        return false;
    }



}
