public class Main {
    public static void main(String[] args) {
//https://leetcode.com/problems/number-of-islands/
        String[][] matrix = {
                {"1","1","0","0","0"},
                {"1","1","0","0","0"},
                {"0","0","1","0","0"},
                {"0","0","0","1","1"}
        };

        System.out.println("Number of Islands = " + findNumIslands(matrix));

        System.out.println("Hello world!");
    }
    public static int findNumIslands(String[][] matrix){
        int count = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for(int i = 0 ; i < rows; i ++){
            for(int j = 0 ; j < cols; j ++){
                if( matrix[i][j].equals("1") && visited[i][j] == false ){
                    count++;
                    visitNeighbours(matrix, visited, i, j);
                }
            }
        }

        return count;
    }

    private static void visitNeighbours(String[][] matrix, boolean[][] visited, int row, int col){
        int maxRow = matrix.length;
        int maxCol = matrix[0].length;

        if(row < 0 || col < 0 || row >= maxRow || col >= maxCol || matrix[row][col].equals("0") || visited[row][col] == true){
            return;
        }
        visited[row][col] = true;
        visitNeighbours(matrix, visited, row -1, col);
        visitNeighbours(matrix, visited, row, col -1);
        visitNeighbours(matrix, visited, row, col + 1);
        visitNeighbours(matrix, visited, row + 1, col );
    }
}