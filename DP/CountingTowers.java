// Core Intution:
// Blocks can either be separate or linked 
// seperate: width 1
// linked: widhth 2
// Maintain 2 DP array
// 1st DP for linked blocks
// 2nd DP for seperate blocks

// CASE A: Going from i-1 to i we have to keep width 2
// Case 1: i -1 has a linked block:
// a) extend the previous i-1 block to i
// b) start a new linked block from i
// Case 2: i - 1 has seperate blocks
// a) Break the blocks and start new linked block from i
// dp1[i] = 2.dp1[i-1] + dp2[i-1];

// CASE B: Going from i-1 to i we have to keep width 1
// Case 1: i -1 has a linked block:
// a) break and start 2 separate Linked Blocks
// Case 2: i - 1 has separate blocks
// a) break both of them and start new
// b) extend both of them 
// c) extend right block and start a new left block
// d) extend left block and start a new right block
// dp2[i] = 4.dp2[i-1] + dp1[i-1]

public class CountingTowers {
    private static final int MOD = 1000000007;
    private static final int MAX = 1000000;

    // Global arrays to store precomputed answers

    private static long[] dp1 = new long[MAX + 1];
    private static long[] dp2 = new long[MAX + 1];

    /*
     * Precompute the transitions up to max poss N
     * Run once at the beginning so that each test case query
     * can be answered in O(1)
     */

    public static void precompute() {
        // Base case: For height 1
        dp1[1] = 1; // one solid block of width 2
        dp2[1] = 1; // two separate blocks of width 1

        for (int i = 2; i <= MAX; i++) {
            dp2[i] = (4 * dp2[i - 1] + dp1[i - 1]) % MOD;
            dp1[i] = (2 * dp1[i - 1] + dp2[i - 1]) % MOD;
        }
    }

    public static int solveCountingTowers(int n){
        long totalways = (dp1[n] + dp2[n]) % MOD;
        return (int) totalways;
    }
}
