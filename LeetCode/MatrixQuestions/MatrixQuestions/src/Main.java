public class Main {
    //https://leetcode.com/problems/search-a-2d-matrix/description/
    public static void main(String[] args) {
        int[][] matrix =  {
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,60}
        };
        System.out.println(searchInAMatrix(matrix, 18));
        System.out.println("Hello world!");
    }

    public static boolean searchInAMatrix(int[][] matrix, int target){
        int rows = matrix.length;
        int cols = matrix[0].length;
        int currentRow =  rows -1;
        int currentCol = 0;

        while(currentRow >= 0  && currentCol <= cols -1){
            if(matrix[currentRow][currentCol] == target){
                return true;
            }
            else if (matrix[currentRow][currentCol] < target){
                currentCol ++;
            }else{currentRow --;
            }
        }
        return false;
    }
}