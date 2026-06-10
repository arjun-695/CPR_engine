import java.util.Scanner;
public class GridPaths1 {
    static final int MOD = 1000000007;

    public static int solve(int n, char grid[][]){
        int dp[][] = new int[n][n];

        if(grid[0][0] == '.')
            dp[0][0] = 1;

        for( int i = 0; i < n; i++){
            for (int j = 0; j < n; j++) {
                
                if(grid[i][j] == '*') continue;

                if(i > 0){
                    dp[i][j] = (dp[i][j] + dp[i-1][j]) % MOD;
                }

                if(j > 0){
                    dp[i][j] = (dp[i][j] + dp[i][j-1]) % MOD;
                }
            }
        }
        return (dp[n-1][n-1]);
    }
    static Scanner sc = new Scanner(System.in);
    public static int solve2D(int n, char grid[][]){
        int dp[] = new int[n];
        
        if (grid[0][0] == '.') {
        dp[0] = 1;
    }

        for(int i = 0 ; i < n ; i++){

            for( int j = 0; j < n; j++){
                if(grid[i][j] == '*')
                    dp[j] = 0;
                else{
                    if(j > 0)
                    dp[j] = (dp[j] + dp[j-1] % MOD);
                }
            }
        }
        return dp[n-1];
    }
}
