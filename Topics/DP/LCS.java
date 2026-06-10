public class LCS {
    public static String getLCS(String s, String t) {
        int n = s.length();
        int m = t.length();

        int[][] dp = new int[n + 1][m + 1];
        // Forward Pass - Compute LCS Length Matrix
        for (int i = 1; i <= n; i += 1) {
            for(int j = 1; j <= m; j++){
                // if characters match, they must be part of the LCS cofig
                // for this state we add 1 to the optimal result of excluding both characters

                if( s.charAt(i) == t.charAt(j)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    // If they mismatch, the optimal LCS must come from either skipping the current char of 's' || 't'
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        // Backward Pass - Backtrack from dp[n][m] to reconstruct the string
        StringBuilder lcsReverse = new StringBuilder();
        int i = n, j = m;

        while (i > 0 && j > 0){
            if(s.charAt(i-1) == t.charAt(j-1))
            {   // if chars are equal, add into lcs
                lcsReverse.append(s.charAt(i - 1));
                i--; j--;
            }

            else if(dp[i-1][j] >= dp[i][j-1]){
                // if vavlue comes from cell above
                i--;
            } else{ // if value comes from the left cell
                j--;
            }
        }
        return lcsReverse.reverse().toString();
    }
}