
class SudokuHashing{

    static boolean[][] rowHash = new boolean[9][10];
    static boolean[][] colHash = new boolean[9][10];
    static boolean[][] boxHash = new boolean[9][10];
    public static void main(String[] args){
        int[][] mat = {
            {3, 0, 6, 5, 0, 8, 4, 0, 0},
            {5, 2, 0, 0, 0, 0, 0, 0, 0},
            {0, 8, 7, 0, 0, 0, 0, 3, 1},
            {0, 0, 3, 0, 1, 0, 0, 8, 0},
            {9, 0, 0, 8, 6, 3, 0, 0, 5},
            {0, 5, 0, 0, 9, 0, 6, 0, 0},
            {1, 3, 0, 0, 0, 0, 2, 5, 0},
            {0, 0, 0, 0, 0, 0, 0, 7, 4},
            {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };

        solveSudoku(mat);

        for(int[] row: mat){
            for( int val: row)System.out.println(val + " ");
            System.out.println();
        }
    }

    private static void solveSudoku(int[][] mat){
        // Initialize Hash tables with existing numbers on the board
        for(int i = 0; i< 9; i++){
            for (int j = 0; j < 9; j++) {
                if (mat[i][j] != 0) {
                    int num = mat[i][j];
                    int boxIdx = (i/3) * 3 + (j/3);
                    rowHash[i][num]= true;
                    colHash[j][num]= true;
                    boxHash[boxIdx][num]= true;
                }
            }
        }
        solveSudokuRec(mat, 0, 0);

    }

    private static boolean solveSudokuRec(int[][] mat, int row, int col){
        if(row == 8 && col == 0)return true;

        if(col == 9){
            col = 0;
            row++;
        }

        if( mat[row][col] != 0 ) return solveSudokuRec(mat, row, col+1);
        
        int boxIdx = (i/3) * 3 + (j/3);
        
        for(int num = 1; num< 10; num++){
            if(!rowHash[row][num] && !colHash[col][num] && !boxHash[boxIdx][num]){

                // Mark as seen (Hash Insert)
                mat[row][col] = num;
                rowHash[row][num] = colHash[col][num] = boxHash[boxIdx][num] = true;

                if(solveSudokuRec(mat, row, col + 1)) return true;


                // BackTrack
                mat[row][col] = 0;
                rowHash[row][num] = colHash[col][num] = boxHash[boxIdx][num] = true;
            }
        }
        return false;
        }
}