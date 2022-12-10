import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        Integer min = Integer.MIN_VALUE;
//        int[][] matrix =  {
//                {1,3,5,7},
//                {10,11,16,20},
//                {23,30,34,60}
//        };
//        System.out.println(searchInAMatrix(matrix, 18));
//        int[][] matrix =  {
//                {0,1,2,0},
//                {3,4,5,2},
//                {1,3,1,5}
//        };
//        setZerosInMatrix(matrix);
//        printMatrix(matrix);

//        int[][] matrix =  {
//                {1,2,3},
//                {4,5,6}
//        };
//        System.out.println(minimumPathSum(matrix));
        int[][] matrix =  {
                {0,0,0,0},
                {0,min,0,0},
                {0,0,0,0},
                {min,0,0,0},
                {0,min,0,0}
        };
        System.out.println(numWaysToReachEndWithObstacles(matrix));
        System.out.println("Hello world!");
    }

    //    //https://leetcode.com/problems/search-a-2d-matrix/description/
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

    // https://leetcode.com/problems/set-matrix-zeroes/
    private static void setZerosInMatrix(int[][] matrix){
        HashSet<Integer> rowSet = new HashSet<>();
        HashSet<Integer> colSet = new HashSet<>();

        int rows = matrix.length;
        int cols = matrix[0].length;

        for(int i = 0 ; i < rows; i ++){
            for(int j = 0 ; j < cols; j ++){
                if(matrix[i][j] == 0 ){
                    if(!rowSet.contains(i)){
                        rowSet.add(i) ;
                    }
                    if(!colSet.contains(j)){
                        colSet.add(j) ;
                    }
                }
            }
        }


        for(int i = 0 ; i < rows; i ++){
            for(int j = 0 ; j < cols; j ++){
                if(rowSet.contains(i) || colSet.contains(j)){
                    matrix[i][j] = 0;
                }
            }
        }

    }

    private static void printMatrix(int[][] matrix){
        int rows = matrix.length;
        int cols = matrix[0].length;

        for(int i = 0 ; i < rows; i ++){
            for(int j = 0 ; j < cols; j ++){
                System.out.printf(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    //https://leetcode.com/problems/minimum-path-sum/
    private static int minimumPathSum(int[][] matrix){
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[rows][cols];
        result[0][0] = matrix[0][0];

        // Initialize the first row and first col
        for(int i = 1 ; i < rows;i ++){
            result[i][0] = matrix[i][0] + result[i-1][0];
        }
        for(int i = 1 ; i < cols;i ++){
            result[0][i] = matrix[0][i] + result[0][i-1];
        }

        for(int row = 1 ; row < rows; row ++) {
            for(int col = 1 ; col < cols; col ++) {
                result[row][col] = matrix[row][col] + Math.min(result[row-1][col],result[row][col -1] );
            }
        }

        return result[rows-1][cols-1];

    }

    //https://leetcode.com/problems/unique-paths-ii/
    private static int numWaysToReachEndWithObstacles(int[][] matrix){
        int rows = matrix.length;
        int cols = matrix[0].length;
        if(matrix[0][0] == Integer.MIN_VALUE){
            return 0;
        }
        matrix[0][0] = 1;
        // Initialize the first row and first col
        for(int i = 1 ; i < rows;i ++){
            if(matrix[i][0] == Integer.MIN_VALUE){
                continue;
            }
            matrix[i][0] = matrix[i-1][0] == Integer.MIN_VALUE ? Integer.MIN_VALUE : 1;
        }
        for(int i = 1 ; i < cols;i ++){
            if(matrix[0][i] == Integer.MIN_VALUE){
                continue;
            }
            matrix[0][i] = matrix[0][i-1] == Integer.MIN_VALUE ? Integer.MIN_VALUE : 1;
        }

        for(int i = 1; i < rows; i ++){
            for(int j = 1; j < cols; j ++){
                if(matrix[i][j] == Integer.MIN_VALUE){
                    continue;
                }
                if(matrix[i-1][j] == Integer.MIN_VALUE){
                    matrix[i][j] = matrix[i][j-1];
                }
                else if (matrix[i][j-1] == Integer.MIN_VALUE){
                    matrix[i][j] = matrix[i-1][j];
                }else{
                    matrix[i][j] = matrix[i-1][j] + matrix[i][j-1];
                }
            }
        }
        return matrix[rows-1][cols-1];
    }
}