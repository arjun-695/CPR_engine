package DP;

import java.util.*;

public class minimizingcoins {
    static int[] memo;
    static int[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sum = sc.nextInt();

        memo = new int[sum + 1];
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.fill(memo, -1);

        dp = new int[sum + 1];
        Arrays.fill(dp, (int) 1e9);

        System.out.println(solve(arr, sum, n));
        System.out.println(solveDP(arr, sum, n));
    }

    // memoized + recursion(dfs)
    static int solve(int[] arr, int sum, int n) {
        if (sum == 0)
            return 0;

        if (memo[sum] != -1)
            return memo[sum];

        int total = (int) 1e9;
        for (int i = 0; i < n; i++) {
            if (sum >= arr[i]) {
                // int res = ;
                total = Math.min(total, solve(arr, sum - arr[i], n) + 1);
            }

        }
        return memo[sum] = total;
    }

    // Bottom up
    static int solveDP(int[] arr, int sum, int n) {
        dp[0] = 0;
        for (int i = 1; i <= sum; i++) {
            for (int coin : arr) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }
        return dp[sum];
    }
}
