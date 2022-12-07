public class Main {
    public static void main(String[] args) {
        int[] arr = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxWater(arr));
    }
//https://leetcode.com/problems/container-with-most-water/
    private static int maxWater(int[] arr){
        int maxArea = 0;
        int left = 0;
        int right = arr.length -1;
        int maxLeft = 0;
        int maxRight = arr.length -1;
        while(left < right){
            int area = Math.min(arr[left], arr[right]) * (right-left);
            if(maxArea < area){
                maxArea = area;
                maxLeft = left;
                maxRight = right;
            }
            if(arr[left] <= arr[right]){
                left ++;
            }else{
                right--;
            }
        }

        System.out.println("Left = " + maxLeft);
        System.out.println("Right = " + maxRight);


        return maxArea;
    }
}