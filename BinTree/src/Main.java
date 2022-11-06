import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        BinaryTree<Integer> tree = getTree();
//        tree.preOrder();
//        tree.inOrder();
//        tree.postOrder();
//
//        tree.levelOrderSameLine();
//        System.out.println(tree.height());
//        tree.printZigZag();
//
//        tree.printBottomView();

       // tree.printBoundary();
//
//        tree.mirrorTree();
//        tree.levelOrder();

//        BinaryTree<Integer> tree1 = getTree();
//        BinaryTree<Integer> tree2 = getTree();
//
//        BinaryTree<Integer> test  = new BinaryTree<>();
//        System.out.println( test.areSameTrees(tree1.root, tree2.root) );
//
//        Integer[] preOrder = {1,2,4,8,5,9,3,6,10, 7, 11};
//        Integer[] inOrder = {8,4,2,9,5,1,6,10,3,7,11};
//        Node<Integer> root = getRootFromPreAndInOrderTraversal(preOrder, inOrder);
//        System.out.println();

        BinaryTree<Integer> tree = getTree();
//        tree.printAllPaths();
//
//        ArrayList<ArrayList<Integer>> fullList = tree.getAllPaths();
//
//        for (ArrayList<Integer> path: fullList) {
//            for (Integer i : path) {
//                System.out.printf(i + " -> ");
//            }
//            System.out.println("Null");
//        }
       // System.out.println( tree.hasPathSum(20) );
        System.out.println(tree.maxPathSum());
        tree.populateNextRight();


    }
    private static BinaryTree<Integer> getTree(){
        BinaryTree<Integer> tree = new BinaryTree<>();

        tree.root = new Node<>(1);

        tree.root.left = new Node<>(2);
        tree.root.right = new Node<>(3);

        tree.root.left.left = new Node<>(4);
        tree.root.left.right = new Node<>(5);
        tree.root.right.left = new Node<>(6);
        tree.root.right.right = new Node<>(7);


        tree.root.left.left.left = new Node<>(8);
        tree.root.left.right.left = new Node<>(9);
        tree.root.right.left.right = new Node<>(10);
        tree.root.right.right.right = new Node<>(11);
        tree.root.right.right.right.right = new Node<>(12);


        return tree;
    }

    public static Node<Integer> getRootFromPreAndInOrderTraversal(Integer[] preOrder, Integer[] inorder){
        BoxValue<Integer> preIndex = new BoxValue<>(0);

        return getRootFromPreAndInOrderTraversal(preOrder, inorder, preIndex, 0, preOrder.length -1 );

    }

    private static int findIndex(Integer[] arr, int start, int end, int data){
        for(int i = start; i < end; i ++){
            if(arr[i] == data){
                return i;
            }
        }
        return -1;
    }

    private static Node<Integer> getRootFromPreAndInOrderTraversal(Integer[] preorder,
                                                                   Integer[] inorder,
                                                                   BoxValue<Integer> preIndex,
                                                                   int start,
                                                                   int end ) {

        if (start > end || preIndex.data >= preorder.length){
            return null;
        }

        Node<Integer> node = new Node<>(preorder[preIndex.data]);
        preIndex.data ++;
        int inOrderIndex = findIndex(inorder, start, end , node.data);
        if(inOrderIndex == -1){
            node.left = null;
        }else{
            node.left = getRootFromPreAndInOrderTraversal(preorder, inorder, preIndex, start, inOrderIndex -1);
        }

        if(inOrderIndex == -1){
            node.right = null;
        }else{
            node.right = getRootFromPreAndInOrderTraversal(preorder, inorder, preIndex, inOrderIndex +1,  end);
        }

        return node;
    }

    private static String stringCompression(String str){
        if(str == null){
            return null;
        }
        char[] arr = str.toCharArray();
        if(arr.length <=1){
            return  arr.toString();
        }
        int index= 0;
        char prevChar = arr[index];
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for(int i = 1; i < arr.length; i ++){
            if(prevChar == arr[i]){
                count++;
            }else{
                sb.append(prevChar);
                sb.append(count);
                prevChar = arr[i];
                count = 1;
            }
        }
        sb.append(prevChar);
        sb.append(count);
        return sb.toString();
    }

}