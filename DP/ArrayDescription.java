public class ArrayDescription {

    // https://cses.fi/problemset/task/1746
    static final int MOD = 1000000007;
    /**
     * Approach 1: Bottom-Up Iterative Tabulation (Iterative DP)
     * * Time Complexity: O(N * M)
     * Space Complexity: O(N * M) - Can be optimized to O(M) because row 'i' only depends on row 'i-1'.
     * * State Definition:
     * dp[i][v] = Number of valid sub-arrays from index 0 to i ending with value 'v'.
     */

    public static int solveProb(int n, int m, int[] arr){
        int[][] dp = new int[n][m+2]; // m+2 to avoid out of bound 

        // Base case: initializing the first row
        if( arr[0] == 0 ){
            for(int i = 1; i <= m ; i++)
                dp[0][i] = 1; // first value is 0 so it can be any value;
        } else {
            dp[0][arr[0]] = 1;
        }

        // Iterative state transition
        for(int i = 1; i< n ; i++){
            if( arr[i] != 0 ){
            // If the current element is fixed, only compute the value for arr[i]
              int v = arr[i];
              long ways = 0;
              ways = (ways + dp[i - 1][ v - 1 ]) % MOD;  
              ways = (ways + dp[i - 1][ v ]) % MOD;  
              ways = (ways + dp[i - 1][ v + 1 ]) % MOD;
              dp[i][v] = (int)ways;  
            } else {
                // Try all possible values for the current element
                long ways = 0;
                for (int v = 1; v <= m; v++) {
                    ways = (ways + dp[i - 1][ v - 1 ]) % MOD;  
                    ways = (ways + dp[i - 1][ v ]) % MOD;  
                    ways = (ways + dp[i - 1][ v + 1 ]) % MOD;
                    dp[i][v] = (int)ways;  
                }
            }
        }

        int totalways = 0;
        for (int idx = 1; idx <= m; idx++) {
            totalways += dp[n-1][i];
        }

        return totalways;
    }
}
