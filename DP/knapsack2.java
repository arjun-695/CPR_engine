// Constraints trap otherwise similar to knapsack- 1 problem 
// N <= 100
// W <= 10^9: TOO BIG: we will not make a dp table so big other wise memory leak error; dp[N+1][W+1]
// wi <= W
// vi <= 10^3 : helpful afterwards to tweak the knapsack algo

// THE MIND SHIFT
// If we cannot make the CAPACITY(W) as an array dimention then what will take it place?
// VALUE(V) can take it's place!

// MAX VAlUE = N * max(vi) = 100 * 1000 = 100,000
// dp[j] = to achieve value j what is the minimum weight that we require;

import java.util.Arrays;

public class knapsack2 {
    private static final long INF = (long) 1e12;

    public static int knapsackSecond(int n, int maxCapacity, int[] Weight, int[] value, int maxPossibleValue) {
        long[] dp = new long[maxPossibleValue + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int currentValue = value[i];
            int currentwt = Weight[i];

            for (int j = maxPossibleValue; j >= currentValue; j--) {
                if (dp[j - currentValue] != INF) {
                    dp[j] = Math.min(dp[j], dp[j - currentValue] + currentwt);
                }
            }
        }

        int maxAchievableValue = 0;
        for (int j = 0; j <= maxPossibleValue; j++) {
            if (dp[j] <= maxCapacity) {
                maxAchievableValue = Math.max(maxAchievableValue, j);
            }
        }
        return maxAchievableValue;
    }

    public static int knapsackSecond2D(int n, int maxCapacity, int[] Weight, int[] value, int maxPossibleValue) {
        long dp2[][] = new long[n + 1][maxPossibleValue + 1];

        for (long[] row : dp2) {
            Arrays.fill(row, INF);
        }

        // Base Case: 0 items for 0 value
        for (int i = 0; i <= n; i++) {

        }
        for (int i = 1; i <= n; i++) {
            int currentVal = value[i - 1];
            int currentWt = Weight[i - 1];
            for (int j = 1; j <= maxPossibleValue; j++) {
                // skip the current element
                long skip = dp2[i - 1][j];

                long pick = INF;
                if (j >= currentVal && dp2[i - 1][j - currentVal] != INF) {
                    pick = dp2[i - 1][j - currentVal] + currentWt;
                }

                dp2[i][j] = Math.min(skip, pick);
            }
        }
        int maxAchievableValue = 0;
        for (int j = 0; j <= maxPossibleValue; j++) {
            if (dp2[n][j] <= maxCapacity) {
                maxAchievableValue = Math.max(maxAchievableValue, j);
            }
        }
        return maxAchievableValue;
    }
}
