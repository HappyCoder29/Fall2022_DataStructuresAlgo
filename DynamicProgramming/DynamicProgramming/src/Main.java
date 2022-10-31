import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
//        int[] arr = {-2, -3, -4, -1, -2, -1, -5, -3};
//        System.out.println(kadane(arr) );

//        int[] arr = {10,9,2,5,3,7,101,18};
//        ArrayList<ArrayList<Integer>> allSubsequences = longestIncreasingSubSequence(arr);
//        for (ArrayList<Integer> list :allSubsequences) {
//            for (Integer i : list) {
//                System.out.printf(i + " -> ");
//            }
//            System.out.println();
//        }

        int[] arr = {1,3,5,8,9,2,6,7,6,8,9};
        System.out.println(minJumpsToreachEnd(arr));
    }


    private static int fib(int n){
        if(n <= 1){
            return 1;
        }
        return fib(n-1) + fib(n-2);
    }

    private static Long fibMemoization(int n){
        if(n <= 1){
            return 1L;
        }
        HashMap<Integer, Long> map = new HashMap<>();
        map.put(0, 1L);
        map.put(1, 1L);
        return fibMemoization(map, n);
    }
    private static Long fibMemoization(HashMap<Integer, Long> map, int n){
        if(!map.containsKey(n)){
            Long value = fibMemoization(map, n-1) + fibMemoization(map, n-2);
            map.put(n, value);
        }
        return map.get(n);
    }

    private static Long fibTabulization(int n){
        Long[] arr = new Long[n+1];
        if(n <= 0){
            return 1L;
        }
        arr[0] = 1L;
        arr[1] = 1L;
        for(int i = 2; i < arr.length; i ++){
            arr[i] = arr[i-1] + arr[i-2];
        }
        return arr[n];
    }

    private static Long tribonacciSequence(int n){
        Long[] arr = new Long[n+1];
        arr[0] = 0L;
        arr[1] = 0L;
        arr[2] = 1L;

        for(int i = 3; i < arr.length; i ++){
            arr[i] = arr[i-1] + arr[i-2] + + arr[i-3];
        }
        return arr[n];
    }


    private static int kadaneBruteForce(int[] arr){
        int maxSum = arr[0];
        for(int i = 1; i < arr.length; i ++){
            for(int j = 0; j < i ;j ++){
                int sum = 0 ;
                for(int k = j ; k < i ; k ++){
                    sum += arr[k];
                }
                if(sum > maxSum){
                    maxSum = sum;
                }
            }
        }

        return maxSum;
    }

    private static int kadane(int[] arr){
        int maxSum = arr[0];
        int sum = 0;
        int startIndex = 0;
        int endIndex = 0;

        for(int i = 0 ; i < arr.length; i ++){
            sum = sum + arr[i];
            if(maxSum < sum ){
                maxSum = sum;
                endIndex = i;
            }
            if(sum < 0 ){
                sum = 0;
                startIndex = i + 1;
            }
        }

        System.out.println("Start Index = " + startIndex );
        System.out.println("End Index = " + endIndex );

        return maxSum;
    }

    private static ArrayList<ArrayList<Integer>> longestIncreasingSubSequence(int[] arr){

        ArrayList<ArrayList<Integer>> listOfSubsequences = new ArrayList<>();

        if(arr == null ){
            return  null;
        }
        if(arr.length <= 0 ){
            ArrayList<Integer> list = new ArrayList<>();
            list.add(arr[0]);
            listOfSubsequences.add(list);
            return listOfSubsequences;
        }

        int[] lis = new int[arr.length];
        for(int i = 0; i < arr.length; i ++){
            lis[i] = 1;
        }
        int[] indexes = new int[arr.length];
        for(int i = 0; i < arr.length; i ++){
            indexes[i] = 0;
        }
        int maxLength = 1;
        int maxIndex = 0;
        for(int i = 1; i < arr.length; i ++){
            for(int j = 0 ; j < i ; j ++){
                if(arr[i] > arr[j]){ // We might have an increasing subsequence
                    if(lis[j] + 1 > lis[i]){
                        lis[i] = lis[j] + 1; // Max increasing subsequence will be from j -> i
                        indexes[i] = j; // I came from J
                        if(maxLength < lis[i]){
                            maxLength = lis[i];
                            maxIndex = i;
                        }
                        maxLength = maxLength < lis[i] ? lis[i] : maxLength;
                    }
                }
            }
        }
        // find out which value

        ArrayList<Integer> maxLengthIndexes = new ArrayList<>();
        for(int i = 0 ; i < arr.length; i ++) {
            if(lis[i] == maxLength){
                maxLengthIndexes.add(i);
            }
        }

        for(int i = 0 ; i < maxLengthIndexes.size(); i ++){
            int numValues = maxLength;
            int currentIndex =  maxLengthIndexes.get(i);
            ArrayList<Integer> list = new ArrayList<>();
            while(numValues > 0){
                list.add(0, arr[currentIndex]);
                numValues --;
                currentIndex = indexes[currentIndex];
            }
            listOfSubsequences.add(list);
        }



        return listOfSubsequences;
        //return maxLength;

    }

    private static int minJumpsToreachEnd(int[] arr){
        
        if(arr == null ){
            return  Integer.MIN_VALUE;
        }
        if(arr.length <= 0 ){
            return 1;
        }

        int[] jumps = new int[arr.length];
        Arrays.fill(jumps, Integer.MAX_VALUE);
        jumps[0] = 0;
        int[] indexes = new int[arr.length];
        for(int i = 0; i < arr.length; i ++){
            indexes[i] = 0;
        }

        for(int i = 1 ; i < arr.length; i ++){
            for(int j = 0 ; j < i; j ++){
                if(arr[j] + j >= i){ // We will be able to make the jump
                    // compare and see if the jump is shorter length
                    if(jumps[i] > jumps[j] + 1){
                        jumps[i] = jumps[j] + 1;
                        indexes[i] = j;
                    }
                }
            }
        }

        int numValues = jumps[jumps.length-1];
        int currentIndex =  jumps.length -1;
        ArrayList<Integer> list = new ArrayList<>();
        while(numValues > 0){
            list.add(0, arr[currentIndex]);
            numValues --;
            currentIndex = indexes[currentIndex];
        }
        list.add(0, arr[0]);

        for (Integer i : list) {
            System.out.printf(i + " -> ");
        }
        System.out.println();


        return jumps[jumps.length -1];
    }


}