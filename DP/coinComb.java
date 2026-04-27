package DP;

import java.util.Arrays;

class coinComb {
    static int[] dp;

    public static void main(String[] args) {
        int coin[] = { 2, 3, 5 };
        int target = 9;
        dp = new int[target + 1];
        Arrays.fill(dp, -1); // for memoization
        Arrays.fill(dp, 0); // for DP
        System.out.println(combCounter(coin, target));
        // System.out.println(combCounterMemo(coin, target));
        System.out.println(combCounterDP(coin, target));
        for (int i = 0; i < dp.length; i++) {
            System.out.print(dp[i] + " ");
        }
    }

    static int combCounter(int[] coin, int target) {
        if (target == 0)
            return 1;
        if (target < 0)
            return 0;
        int sum = 0;
        for (int j = 0; j < coin.length; j++) {
            sum += combCounter(coin, target - coin[j]);
        }

        return sum;
    }

    static int combCounterMemo(int[] coin, int target) {
        if (target == 0)
            return 1;

        if (target < 0)
            return 0; // Invalid path

        if (dp[target] != -1)
            return dp[target];

        int sum = 0;

        for (int i = 0; i < coin.length; i++) {
            sum += combCounterMemo(coin, target - coin[i]);
        }

        return dp[target] = sum;
    }

    static int combCounterDP(int[] coin, int target) {
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int c : coin) {
                if (i - c >= 0) {
                    dp[i] = dp[i] + dp[i - c];
                }
            }
        }
        return dp[target];
    }
}
