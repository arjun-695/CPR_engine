package DP;

import java.util.Arrays;
import java.util.Scanner;

public class Frog_II {
    static int memo[];
    static int dp[];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int k = sc.nextInt();

        int[] h = new int[N];

        memo = new int[N];
        Arrays.fill(memo, -1);

        for (int i = 0; i < N; i++) {
            h[i] = sc.nextInt();
        }
        dp = new int[N];
        Arrays.fill(dp, (int)1e9 );

        System.out.println(solve(N, k, h, 0));
        System.out.println(solveII(N, k, h));
    }

    static int solve(int N, int k, int[] h, int i) {
        if (i == N - 1) {
            return 0;
        }

        if (memo[i] != -1)
            return memo[i];

        int sum = Integer.MAX_VALUE;
        for (int j = 1; j <= k && i + j < N ; j++) {
            int cost = Math.abs(h[i] - h[i + j]) + solve(N, k, h, i + j);
            sum = Math.min(cost, sum);
        }
        return memo[i] = sum;
    }

    static int solveII(int N , int k, int[] h){
        if(N == 0 ) return 0;

        dp[0] = 0;
        for(int i= 0; i < N; i++ ){
            for( int j = 1; j <= k && i + j < N ; j++){
                dp[i+j] = Math.min(dp[i + j] , dp[i] + Math.abs(h[i] - h[i+j]));
            }
        }
        return dp[ N-1 ];
    }
}
