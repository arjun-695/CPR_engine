import java.util.*;

public class MoneySums {
    // Memoization + recursion solution
    public static List<Integer> solveMoneySumsMemo(int n, int[] coins){
        int maxSums = 0;
        for(int i: coins){
            maxSums += i;
        }

        int memo[][] = new int[n][maxSums + 1];
        for(int[] row: memo){
            Arrays.fill(row, -1);// 1 for possible, 0 for not possible and -1 for not visited
        }

        // TreeSet to store unique sums automatically in sorted order 
        TreeSet<Integer> uniqueSet = new TreeSet();

        // Recursion starting from 0 index with initial sum 0
        calculateSums(0,0,coins, n, memo, uniqueSet);

        return new ArrayList<>(uniqueSet);
    }

    private static void calculateSums(int index, int currentSum, int[] coins, int n, int[][] memo, TreeSet<Integer> uniqueSet ){
        if( index == n){
            if(currentSum > 0){
                uniqueSet.add(currentSum);
            }
            return;
        }

        if( memo[index][currentSum] != -1) {
            return;
        }
        // choice 1: skip the current coin
        calculateSums(index+1, currentSum, coins, n, memo, uniqueSet);
        // choice 2: include the current coin
        calculateSums(index+1, currentSum + coins[index], coins, n, memo, uniqueSet);

        // Mark this state as visited
        memo[index][currentSum] = 1;
    }

    // DP solution
    public static List<Integer> moneySumDP(int n, int[] coins){
        int maxSums = 0;
        for(int i: coins) maxSums += i;

        boolean[] dp = new boolean[maxSums+1];

        dp[0] = true;

        for(int i = 1; i < n; i++){
            int currentCoin = coins[i];

            for(int j = maxSums; j >= currentCoin; j--){
                dp[j] |= dp[j - currentCoin];
            }
        }
        List<Integer> result = new List<>();
        for(int j = 1; j <= maxSums; j++){
            if(dp[j]) result.add(j);
        }
        return result;
    }
}