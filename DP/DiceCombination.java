
package DP;

// https://cses.fi/problemset/task/1633
// Dice Combinations

// task is to count number of ways to construct sum n

public class DiceCombination {
    /*
     * // Naive Approach
     * static int countway(int n){
     * if( n == 0 ) return 1;
     * if( n < 0 ) return 0;
     * 
     * int total = 0;
     * for(int i = 1; i< 7; i++){
     * total += countway(n-i);
     * }
     * return total;
     * }
     */

    /*
     * // Memoization
     * // static int[] dp;
     * 
     * static int countway(int n, int[] dp){
     * if(n == 0) return 1;
     * if( n < 0 ) return 0;
     * if(dp[n] != 0) return dp[n];
     * 
     * int total = 0;
     * for(int i = 1; i< 7; i++){
     * total += countway(n-i, dp);
     * }
     * dp[n] = total;
     * return total;
     * }
     * 
     */

    // Bottom UP approach
    static int countway(int n) {
        int dp[] = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 6; j++) {
                if (i - j >= 0) {
                    dp[i] = dp[i] + dp[i - j];
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        // int dp[]= new int[3+1];
        // System.out.println(countway(3, dp)) ;
        System.out.println(countway(3));
    }
}
