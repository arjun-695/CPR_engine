import java.util.Arrays;
public class RemovingDigits {
    public static int Removing(int n){
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1000000000);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            int temp = i;
            while( temp != 0){
                int digit = temp % 10;
                temp /= 10;

                if(digit == 0) continue;

                dp[i] = Math.min( dp[i], dp[i - digit] + 1 );
            }
        }

        return dp[n];
    }

    // Greedy Approach: faster than dp

    public static int GreedyRemove(int n){
        int steps = 0;

        while( n > 0){
            int temp = n; 
            int digit = 0;

            while (temp > 0){
                digit = Math.max(digit, temp % 10);
                temp /= 10;
            }

            n -= digit;
            steps++;
        }
        return steps;
    }
}
