public class LinkListRandom <T> {

    public NodeRandom<T> head;

    public LinkListRandom(){

    }


    public LinkListRandom<Integer> createCopy(){
        if(head == null){
            return null;
        }

        // Create Copy of all the nodes inside the main lost
        NodeRandom temp = head;
        while(temp != null){
            NodeRandom copyNode = new NodeRandom(temp.data);
            copyNode.next = temp.next;
            temp.next = copyNode;
            temp = copyNode.next;
        }

        // Populate the random node
        NodeRandom orig = head;
        NodeRandom copy = head.next;
        while(copy.next != null){
            copy.random = orig.random.next;
            orig = orig.next.next;
            if(copy.next != null){
                copy = copy.next.next;
            }
        }
        copy.random = orig.random.next;

        //Break the list
        orig = head;
        copy = head.next;
        NodeRandom copyHead = copy;
        while(orig != null){
            orig.next = orig.next.next;
            if(copy.next != null){
                copy.next = copy.next.next;
            }
            orig = orig.next;
            copy = copy.next;
        }


        LinkListRandom list = new LinkListRandom();
        list.head = copyHead;
        return  list;
    }


}
