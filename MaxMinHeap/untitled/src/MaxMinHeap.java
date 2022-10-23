import java.util.ArrayList;

public class MaxMinHeap {
    ArrayList<Integer> arrList = null;
    int currentIndex = -1;
    boolean isMinHeap = true;

    public MaxMinHeap(boolean minHeap){
        arrList = new ArrayList<>();
        currentIndex = -1;
        isMinHeap = minHeap;
    }

    private int parent(int n){
        return (n-1)/2;
    }

    private int leftChild(int n){
        return 2*n + 1;
    }
    private int rightChild(int n){
        return 2*n + 2;
    }

    public Integer peek(){
        if(currentIndex < 0){
            return Integer.MIN_VALUE;
        }

        return arrList.get(0);
    }

    public void add(Integer value){
        currentIndex ++;
        arrList.add(currentIndex, value);
        siftUp(currentIndex);
    }

    private void siftUp(int index){
        if(index == 0){
            return;
        }
        int parent = parent(index);
        if(isMinHeap){
            if(arrList.get(parent) < arrList.get(index)){
                return;
            }
        }else{
            if(arrList.get(parent) > arrList.get(index)){
                return;
            }
        }

        int temp = arrList.get(parent);
        arrList.set(parent, arrList.get(index));
        arrList.set(index, temp);
        siftUp(parent);
    }

    public Integer remove(){
        if(currentIndex < 0){
            return Integer.MIN_VALUE;
        }

        int minValue = arrList.get(0);
        arrList.set(0, arrList.get(currentIndex));
        currentIndex --;
        siftDown(0);
        return minValue;
    }

    private void siftDown(int index){
        if(index == currentIndex){
            return;
        }

        int leftChild = leftChild(index);
        int rightChild = rightChild(index);

        if(leftChild > currentIndex && rightChild > currentIndex){
            return;
        }
        int compareIndex = -1;
        if(rightChild > currentIndex){
            compareIndex = leftChild; // Only one child exist
        }else{
            // Both child exist
            if(isMinHeap){
                compareIndex = arrList.get(leftChild) < arrList.get(rightChild)? leftChild : rightChild;
            }else{
                compareIndex = arrList.get(leftChild) > arrList.get(rightChild)? leftChild : rightChild;
            }
        }
        if(isMinHeap){
            if(arrList.get(index) < arrList.get(compareIndex)){
                return;
            }
        }else{
            if(arrList.get(index) > arrList.get(compareIndex)){
                return;
            }
        }

        int temp = arrList.get(index);
        arrList.set(index, arrList.get(compareIndex));
        arrList.set(compareIndex, temp);

        siftDown(compareIndex);

    }

}
