package DP;

import java.util.Arrays;

public class knapsack {
    static int[][] memo;

    /* 
    static int knapsack(int w,int[] wt, int[] val, int n){
        if(n == 0 || w == 0){
            return 0;
        }

        if(wt[n-1] > w){
            return knapsack(w, wt, val, n - 1);
        }

        // return max of 2 cases:
        // a) nth item included 
        // b) nth item not included

        return Math.max(knapsack( w, wt, val, n - 1), val[n-1]+ knapsack( w - wt[n-1] , wt, val, n-1));
    }
    */


    /*
    // Memoization:

    static int knapsack(int w,int[] wt, int[] val, int n){
        if(n == 0 || w == 0){
            return 0;
        }

        // check if the value is already calculated
        if(memo[n][w] != -1) return memo[n][w];

        if(wt[n-1] > w){
            return memo[n][w] = knapsack(w, wt, val, n - 1);
        }

        // return max of 2 cases:
        // a) nth item included 
        // b) nth item not included

        return memo[n][w] = Math.max(knapsack( w, wt, val, n - 1), val[n-1]+ knapsack( w - wt[n-1] , wt, val, n-1));
    }
     */


    // Bottom Up
     static int knapsack(int w,int[] wt, int[] val, int n){
        // dp[i][w] will store the maximum value that can be attained
        // with weight limit 'w' using the first 'i' items.

        int[][] dp = new int[n+1][w+1];

        // Building the table bottom up 

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= w; j++) {
                if( i == 0 || j == 0 ){
                    // Base case: 0 items or 0 capacity means 0 value
                    dp[i][j] = 0;
                } else if( wt[ i -1 ] <= j){
                    dp [i][j] = Math.max(val[i-1] + dp[i-1] [j - wt[i -1]], dp[i-1][j]);
                }else{
                    // Item doesn't fit: value remains same as without it
                    dp[i][j] = dp[i-1][j];
                }
                
            }
            
        }
        return dp[n][w];
    }

    
    public static void main(String[] args) {
        int profit[] = new int[] { 60, 100, 120,50, 60 };
        int weight[] = new int[] { 10, 20, 30,10,10 };
        int n = profit.length;int W = 50;
        // Initialize memo table with -1
        memo = new int[ n+1 ][ W+1 ];
        for (int[] row: memo){
            Arrays.fill(row, -1);
        }

        
        
        System.out.println("Maximum Profit: " + knapsack(W, weight, profit, n));
    }
}
