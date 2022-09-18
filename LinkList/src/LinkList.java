import com.sun.jdi.PrimitiveValue;

public class LinkList <T> {

    public Node<T> head;
    public Node<T> tail;

    public int length = 0;

    public  LinkList (){
        head = null;
        tail = null;
    }

    public void addNodeToHead(T data){
        Node<T> addNode = new Node<>(data);
        addNode.next = head;
        head = addNode;
        if(head.next == null){
            tail = head;
        }
        length ++;
    }

    // O(n)
    public void addNodeToTail(T data){
        Node<T> addNode = new Node<>(data);

        if( head == null){
            head = addNode;
            tail = addNode;
            length ++;
            return;
        }

        Node<T> temp = head;

        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = addNode;
        length ++;
    }


    // O(1)
    public void addNodeToTailOOne(T data){
        Node<T> addNode = new Node<>(data);

        if( head == null){
            head = addNode;
            tail = addNode;
            return;
        }
        tail.next = addNode;
        tail = tail.next;

    }

    // O(n)
    public void printList(){
        if(head == null){
            System.out.println("List is empty");
        }

        Node<T> temp = head;
        while(temp != null){
            System.out.printf(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("NULL");

    }

    // O(n)
    public int length(){
        if(head == null){
            return 0;
        }
        Node<T> temp = head;
        int count = 0;
        while(temp != null){
            count ++;
            temp = temp.next;
        }
        return count;
    }
    // Assumes there is no cycle
    public Node<T> getNthFromEnd(int n){
        if(head == null || n <= 0){
            return null;
        }

        Node<T> front = head;
        Node<T> back = head;
        int count = n;
        while(count != 0){
            if(back == null){
                return null;
            }
            back = back.next;
            count --;
        }
        while(back != null){
            back = back.next;
            front = front.next;
        }
        return front;
    }


    public Node<T> getNthFromEndWithLength(int n){
        if(head == null || n <= 0){
            return null;
        }
        int count = length - n;
        if(count <= 0){
            return null;
        }
        Node<T> temp = head;
        while(count != 0){
            temp = temp.next;
            count --;
        }
        return temp;

    }

    public void  breakCycleIfExist(){
        if(head == null || head.next == null ){
            return;
        }
        Node<T> front = head;
        Node<T> back = head;

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
            return ;
        }

        // There is a cycle
        // if front and back are at same point

        front = head;
        Node<T> temp = back;
        front = front.next;
        back = back.next;
        while(front != back){
            front = front.next;
            back = back.next;
            temp = temp.next;
        }
        temp.next = null;

    }


    public void reverseList(){
        if(head == null || head.next == null){
            return;
        }

        Node<T> back = null;
        Node<T> mid = head;
        Node<T> front = head.next;

        while(front != null){
            // reversing the next pointer
            mid.next = back;

            // Moving the front, mid and back
            back = mid;
            mid = front;
            front = front.next;
        }

        mid.next = back;
        head = mid;

    }


    public Node<T> reverseList(Node<T> node){
        if(node == null || node.next == null){
            return node;
        }

        Node<T> back = null;
        Node<T> mid = node;
        Node<T> front = node.next;

        while(front != null){
            // reversing the next pointer
            mid.next = back;

            // Moving the front, mid and back
            back = mid;
            mid = front;
            front = front.next;
        }

        mid.next = back;
        node = mid;

        return mid;
    }


    public Node<T> breakListInHalf(){
        if(head == null || head.next == null){
            return head;
        }

        Node<T> front = head;
        Node<T> back = head;

        while(front.next != null){
            front = front.next;
            if(front.next != null){
                front = front.next;
                back = back.next;
            }
        }

        Node<T> secondHalf = back.next;
        back.next = null;
        return secondHalf;
    }

    private Node<T> getLastNode(Node<T> node){
        if(node == null  || node.next == null){
            return node;
        }

        Node<T> temp = node;
        while(temp.next != null){
            temp = temp.next;
        }
        return temp;
    }
    // Assuming no cycle
    public boolean isPalindrome(){
        if(head == null || head.next == null){
            return false;
        }
        Node<T> secondHalf = breakListInHalf();

        secondHalf = reverseList(secondHalf);

        Node<T> temp1 = head;
        Node<T> temp2 = secondHalf;

        boolean bPalindrome = true;
        while(temp1 != null && temp2 != null){
            if(temp1.data != temp2.data){
                bPalindrome = false;
                break;
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        // Putting back the list
        secondHalf = reverseList(secondHalf);
        Node<T> lastNode = getLastNode(head);
        lastNode.next = secondHalf;

        return bPalindrome;

    }

    public void zipMerge(){
        if(head == null || head.next == null){
            return;
        }

        Node<T> secondHalf = breakListInHalf();
        secondHalf = reverseList(secondHalf);
        head = zipMerge(head,secondHalf, true );
    }

    private Node<T> zipMerge(Node<T> node1, Node<T> node2, boolean bSwitch){
        Node<T> result = null;
        if(node1 == null){
            return node2;
        }
        if(node2 == null){
            return node1;
        }
        if(bSwitch == true){
            result = node1;
            result.next = zipMerge(node1.next, node2, false);
        }else{
            result = node2;
            result.next = zipMerge(node1, node2.next, true);
        }
        return result;
    }


}
