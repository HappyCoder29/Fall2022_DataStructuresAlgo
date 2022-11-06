import com.sun.source.tree.BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        BST<Integer> bst = new BST<>();
//        bst.root = getBST();
//        bst.insert(12);
//        bst.inOrder();
//        System.out.println(bst.search(2));
//
//        ArrayList<Integer> list = bst.getReverseSortedList();
//        System.out.println(Arrays.toString(list.toArray()) );
//
//        Integer[] arr = {1,2,3,4,5,6,7,8};
//        Node<Integer> node = bst.createBSTFromSortedArray(arr);
//        System.out.println();
//
//        bst.root = getBinaryTree();
//        bst.inOrder();
//        convertBinaryTreeToBST(bst);
//        bst.inOrder();

//        bst.root = getBST();
//
//        Node<Integer> four = bst.root.left.right.left;
//
//        Node<Integer> pred = bst.inOrderPredecessor(four);
//        System.out.println();

//        bst.root = getBST();
//
//        Node<Integer> one = bst.root.left.left;
//        Node<Integer> four = bst.root.left.right.left;
//
//        Node<Integer> lca = bst.LowestCommonAncestor(one, four);
//        System.out.println(lca.data);
//
//        Node<Integer> lcaParent = bst.LCAWithParentNode(one, four);
//        System.out.println(lca.data);

        bst.root = getBST();
        System.out.println(bst.validateBST());

    }

    private static Node<Integer> getBinaryTree(){
        Node<Integer> root = new Node<>(1);

        root.left = new Node<>(2);
        root.right = new Node<>(3);

        root.left.left = new Node<>(4);
        root.left.right = new Node<>(5);

        root.right.left = new Node<>(6);
        root.right.right = new Node<>(7);

        return root ;


    }


    private static Node<Integer> getBST(){
        Node<Integer> root = new Node<>(8);
        root.parent = root;

        root.left = new Node<>(3);
        root.right = new Node<>(10);
        root.left.parent = root;
        root.right.parent = root;


        root.left.left = new Node<>(1);
        root.left.right = new Node<>(6);
        root.left.left.parent = root.left;
        root.left.right.parent = root.left;

        root.right.right = new Node<>(14);
        root.right.right.parent = root.right;

        root.left.right.left = new Node<>(4);
        root.left.right.right = new Node<>(17);
        root.left.right.left.parent = root.left.right;
        root.left.right.right.parent = root.left.right;


        root.right.right.left = new Node<>(13);
        root.right.right.left.parent = root.right.right;



        return root;
    }



    public  static void convertBinaryTreeToBST(BST<Integer> bst){
        ArrayList<Integer> list = bst.getSortedList();
        Integer[] arr = new Integer[list.size()];
        list.toArray(arr);
        Arrays.sort(arr);

        if(arr == null || arr.length == 0){
            return;
        }

        Box<Integer> current = new Box();
        current.data = 0;


        convertBinaryTreeToBST(arr, bst.root, current);

    }

    public static void convertBinaryTreeToBST(Integer[] arr, Node<Integer> node, Box<Integer> current){

        if(node != null ){
            convertBinaryTreeToBST(arr, node.left, current);
            // do something
            node.data = arr[current.data];
            current.data ++;
            convertBinaryTreeToBST(arr, node.right, current);

        }

    }





}