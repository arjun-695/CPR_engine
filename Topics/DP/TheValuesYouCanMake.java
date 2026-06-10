
import java.util.ArrayList;
import java.util.Scanner;

public class TheValuesYouCanMake {
    static boolean dp[][][];
    static boolean dp2[][];

    public static void solve(int n, int k, int[] coins){
        dp = new boolean[n+1][k+1][k+1];
        dp[0][0][0] = true;

        for(int i = 0; i < n; i++){
            for(int j = 0; j <= k; j++){
                for( int x = 0; x <= j; x++){
                    if (dp[i][j][x]) {
                        dp[i+1][j][x] = true;

                        if( j + coins[i] <= k){
                            dp[i+1][j + coins[i]][x] = true;
                        
                            dp[i+1][j + coins[i]][x + coins[i]] = true;
                        
                    }
                }
            }
        }}

        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 0; i <= k; i++){
            if (dp[n][k][i]) {
                arr.add(i);
            }        
        }
        System.out.println(arr.size());
        for(int i: arr) System.out.print(i+ " ");
    }

    public static void solve2D(int n, int k, int[] coins){
        dp2 = new boolean[k+1][k+1];
        dp2[0][0] = true;
        for (int i = 0; i < n; i++) {
            int coin = coins[i];
            for(int j = k; j >= coin; j--){
                for( int x = j; x >= 0; x--){
                    if(dp2[j - coin][x]){
                        dp2[j][x] = true;
                    }

                    if( (x >= coin) && (dp2[j - coin][x - coin])){
                        dp2[j][x] = true;
                    }
                }
            }
        }

        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 0; i <= k; i++){
            if (dp2[k][i]) {
                arr.add(i);
            }        
        }
        System.out.println(arr.size());
        for(int i: arr) System.out.print(i+ " ");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }

        solve2D(n, k, coins);
    }
}
