import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int[] arr = {6,5,3,1,8,7,2,4};
        int kThLargest = findKthLargest(arr, 3);
        System.out.println(kThLargest);


        int[] arr2 = {-1,1,1,0,0,-1,1,0,1,0,-1,-1,0};
        dutchFlag(arr2, 0);
        printArray(arr2);

        int[] arr3 = {3,1,5,5,3,3,3,5,5,5,5,5,5,5};
        System.out.println(findMajorityElement(arr3));
        int[] arr4 = {1,2,3,4,5,6};
        rotateArray(arr4, 8);
        printArray(arr4);


        int[] arr5 = {1,3,5,7,9};
        int[] arr6 = {2,4,6,8,10};
        int[] merged = mergeTwoSortedArraysRecursive(arr5, arr6);
        printArray(merged);

        int[] sorted = {1,2,3,4,5,6,7,8,9};
        System.out.println(binSearch(sorted, 23));
    }

    private static void printArray(int[] arr){
        System.out.printf("[");
        for (int i : arr) {
            System.out.printf(i + ", ");
        }
        System.out.println("]");
    }

    private static void swap(int[] arr, int i , int j){
        if( arr == null || arr.length <= 1 ){
            return;
        }
        if(i <0 || j < 0 || i >= arr.length || j >= arr.length){
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    // N^2
    // n Log(n)
    // n

    // O(n^2)
    private static void bubbleSort(int[] arr){
        for(int i = 0 ; i < arr.length; i ++){
            for(int j = 0; j < arr.length -1 ; j ++){
                if(arr[j] > arr[i]){
                    swap(arr, i, j);
                }
            }
        }
    }

    private static void selectionSort(int[] arr){
        for(int i= 0 ; i < arr.length -1; i ++){
            int minIndex = i;
            for(int j = i +1; j < arr.length; j ++){
                if(arr[minIndex] > arr[j]){
                    minIndex = j;
                }
            }
            if(minIndex != i){
                swap(arr, minIndex, i);
            }
        }
    }

    private static void mergeSort(int[] arr){
        mergeSort(arr, 0, arr.length -1);
    }


    // O(n Log n)
    private static void mergeSort(int[] arr, int low, int high){
        if(low >= high){
            return;
        }
        int mid  = (low + high)/2;
        mergeSort(arr, low, mid);
        mergeSort(arr, mid +1, high);
        merge(arr, low, high);
    }


    // O(n)
    private static void merge(int[] arr, int low, int high){
        int[] temp = new int[high -low +1];
        int mid = (low + high)/2;
        int i = low;
        int j = mid + 1;
        int index = 0;

        while (i <= mid && j <= high){
            if(arr[i] < arr[j]){
                temp[index++] = arr[i++];
            }
            else{
                temp[index++] = arr[j++];
            }
        }

        // Copy remaining parts
        while(i <= mid){
            temp[index++] = arr[i++];
        }
        while(j <= high){
            temp[index++] = arr[j++];
        }


        // Temp is sorted now and now we will put it back in original array
        i = low;
        for(int value: temp){
            arr[i++] = value;
        }
    }

    public static void quickSort(int[] arr){
        quickSort(arr, 0, arr.length -1);
    }

    private static void quickSort(int[] arr, int low, int high){
        if(low < high){
            int position = partition(arr, low, high);
            quickSort(arr, low, position -1);
            quickSort(arr, position +1, high);
        }
    }

    private static int partition(int[] arr, int low, int high){
        int pivot = arr[high];
        int wall = low -1;
        for(int i = low; i < high; i ++){
            if(arr[i] < pivot){
                wall ++;
                swap(arr, i, wall);
            }
        }
        wall++;
        swap(arr, wall, high);
        return wall;
    }


    public static int findKthLargest( int[] arr, int k){
        if(arr == null || arr.length == 0 || k <0 || k > arr.length) {
            return Integer.MIN_VALUE;
        }
        return findKthLargest(arr, 0, arr.length -1, k);

    }
    private static int findKthLargest(int[] arr, int low, int high, int k){
        if(low <= high ){
            int position = partition(arr, low, high);
            if(position == arr.length - k){
                return arr[position];
            }
            else if(position <  arr.length - k){
                return findKthLargest(arr, position +1, high, k);
            }else{
                return findKthLargest(arr, low, position -1, k);
            }
        }
        return Integer.MIN_VALUE;
    }

    private static  void countSort(int[] arr, int RANGE){
        int [] countArr = new int[RANGE];
        // Increment the count of the value
        for (int j : arr) {
            countArr[j] ++;
        }

        int index = 0;
        for(int i = 0 ; i < RANGE; i ++){
            while(countArr[i] > 0){
                arr[index++] = i;
                countArr[i] --;
            }
        }
    }

    // O(n)
    private static void dutchFlag(int[] arr, int pivot){
        if(arr == null || arr.length <= 1){
            return;
        }
        int low = 0;
        int mid = 0;
        int high = arr.length -1;

        while(mid < high){
            if(arr[mid] == pivot){
                mid ++;
            }
            else if (arr[mid] < pivot){
                swap(arr, mid, low);
                mid ++;
                low ++;
            }else{
                swap(arr, mid, high);
                high--;
            }
        }
    }

    public static int findMajorityElement(int[] arr){
        if(arr == null || arr.length == 0){
            return Integer.MIN_VALUE;
        }
        int majorityElement = arr[0];
        int count = 1;
        for(int i = 1; i < arr.length; i ++){
            if(arr[i] == majorityElement){
                count ++;
            }else{
                count --;
                if(count == 0){
                    majorityElement = arr[i];
                    count = 1;
                }
            }
        }

        count = 0;

        // At this point we might have a majority element
        for(int i = 0 ; i < arr.length; i ++){
            if(arr[i] == majorityElement){
                count++;
            }
        }

        if(count > arr.length/2){
            return majorityElement;
        }
        return Integer.MIN_VALUE;
    }

    private static void rotateArray(int[] arr, int k){
        if(arr == null || arr.length <= 1 || k <= 1){
            return;
        }
        k = k % arr.length;
        reverseArray(arr, 0, arr.length -1);
        reverseArray(arr, 0, k-1);
        reverseArray(arr, k, arr.length -1);

    }

    private static void reverseArray(int[] arr, int start, int end){
        while(start < end){
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start ++;
            end --;
        }
    }

    private static int[] mergeTwoSortedArrays(int[] arr1, int[] arr2){
        int[] result = new int[arr1.length + arr2.length];
        int ptr1 = 0;
        int ptr2 = 0;
        int ptr = 0;

        while(ptr1 < arr1.length  && ptr2 < arr2.length){
            if(arr1[ptr1] < arr2[ptr2]){
                result[ptr ++] = arr1[ptr1++];
            }else{
                result[ptr ++] = arr2[ptr2++];
            }
        }

        while(ptr1 < arr1.length){
            result[ptr ++] = arr1[ptr1++];
        }
        while(ptr2 < arr2.length){
            result[ptr ++] = arr2[ptr2++];
        }
        return result;
    }


    private static int[] mergeTwoSortedArraysRecursive(int[] arr1, int[] arr2){
        int[] merged = new int[arr1.length + arr2.length];
        mergeTwoSortedArraysRecursive(merged, arr1, arr2, 0,0, 0);
        return merged;
    }

    private static void mergeTwoSortedArraysRecursive(int[] merged, int[] arr1, int[] arr2, int ptr1, int ptr2, int ptr){

        if(ptr == merged.length -1){
            return;
        }

        if(ptr1 < arr1.length && ptr2 < arr2.length){
            if(arr1[ptr1] < arr2[ptr2]){
                merged[ptr ++] = arr1[ptr1++];
            }else{
                merged[ptr ++] = arr2[ptr2++];
            }
        }
        else if (ptr1 < arr1.length ){
            merged[ptr ++] = arr1[ptr1++];
        }else{
            merged[ptr ++] = arr2[ptr2++];
        }
        mergeTwoSortedArraysRecursive(merged, arr1, arr2, ptr1, ptr2, ptr);
    }



    private static boolean binSearch(int[] arr, int x){
        if(arr == null || arr.length == 0 ){
            return false;
        }
        int start = 0;
        int end = arr.length -1;
        while(start < end){
            int mid = (start + end)/2;
            if(arr[mid] == x){
                return true;
            }else if (arr[mid] < x){
                start = mid +1;
            }else{
                end = mid -1;
            }
        }
        return false;
    }

    private static boolean binSearchRecursive(int[] arr, int x){
        if(arr == null || arr.length == 0 ){
            return false;
        }
        return binSearchRecursive(arr, x, 0, arr.length-1);
    }

    private static boolean binSearchRecursive(int[] arr, int x, int start, int end){
        if(start >  end){
            return false;
        }
        int mid = (start + end)/2;
        if(arr[mid] == x){
            return true;
        }
        else if (arr[mid] < x){
            return binSearchRecursive(arr, x, mid +1, end);
        }else {
            return binSearchRecursive(arr, x, start, mid-1);
        }
    }

    /*
    * Sort -> n Log (n)
    * Search - > log(n)
    * */



}