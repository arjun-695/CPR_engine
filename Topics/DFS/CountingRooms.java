package Topics.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CountingRooms {
    public static void main(String[] args)throws IOException{

        // Initialize bufferedReader and Tokenizer directly
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;


        // Helper lambda/inline approach to grab tokens
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // Initialize the grid
        char[][] grid = new char[n][m];

        // Read the grid row by row
        for (int i = 0; i < n; i++) {
            // Since each row is a single continuous string
            // we read teh entire line directly without needing the tokenizer
            String row = br.readLine();
         for (int j = 0; j < m; j++) {
            grid[i][j] = row.charAt(j);
         }   
        }

        int roomCount = solve(grid, n, m);

        System.out.println(roomCount);
    }

    public static int solve(char[][] grid, int n, int m){
        int roomCount =  0;
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j< m ; j++ ){
                if( grid[i][j] == '.'){
                    roomCount++;
                    dfs(grid, i, j, n, m);
                }
            }
        }
        return roomCount;
    }

    private static void dfs(char[][] grid, int r, int c, int n, int m){
        if(!isValid(r, c, n, m))
            return;

        // Mark the current floor tile as visited by flipping it to a wall '#'
        // this saves space since we don't have to create a visited 2D array
        grid[r][c] = '#';
        dfs(grid, r - 1, c, n, m);// up
        dfs(grid, r , c-1, n, m); // left
        dfs(grid, r + 1, c, n, m); // down
        dfs(grid, r , c + 1, n, m); // right

    }
    public static boolean isValid(int r, int c, int n, int m){
        // boundary check and wall check
        if( r < 0 || c < 0 || r >= n || c >= m)
            return false;
        return true;
    }
}
