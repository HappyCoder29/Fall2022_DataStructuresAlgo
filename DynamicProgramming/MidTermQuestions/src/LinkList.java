public class LinkList <T> {

        public LLNode<T> head;
        public LLNode<T> tail;

        public int length = 0;

        public  LinkList (){
            head = null;
            tail = null;
        }

        public void removeKthFromEnd(int k){
            if( k <= 0){
                return;
            }
            if(head == null){
                return;
            }

            LLNode<T> front = head;
            LLNode<T> back = head;


            // Move front by k +1
            int count = k;
            while(count != 0){
                front = front.next;
                count--;
                if(front == null){
                    break;
                }
            }
            if(count != 0 ){
                // num of elements inside list is less than k
                return;
            }
            if(count == 0){
                LLNode temp = head;
                head = head.next;
                temp = null;
                return;
            }
            while(front!= null){
                front = front.next;
                back = back.next;
            }

            back.next = back.next.next;
            return;
        }
}
