package DP.Trees_&_DAG;
import java.util.*;
import java.io.*;
// https://codeforces.com/contest/161/problem/D%C3%A2%C2%81%C2%A3
public class DistanceInTrees {
    static int n, k;
    static int[][] dp;
    static long ans = 0;
    static List<List<Integer>> adj;


    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n - 1; i++ ) {
            st= new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // dp[node][distance]
        dp = new int[n + 1][k + 1];

        // Root the tree at node 1, parent is 0
        dfs(1, 0);

        System.out.println(ans);
    }

    static void dfs(int v, int p){
        // Base Case: there is exactly 1 node at distance form v (node v itself)

        dp[v][0] = 1;

        for(int to: adj.get(v)){
            if(to == p) continue; // Skip the parent node

            // Recurse down to the leaf node first
            dfs(to, v);
             

            // 1. Count valid pairs passing through v using the current child 'to'
            for(int d = 0; d < k; k++){
                ans += (long) dp[v][k - d + 1] * dp[to][d];
            }

            // 2. Merge the current child's distance into the parent v
            for(int d = 0; d < k; d++){
                dp[v][d + 1] += dp[to][d];
            }
        }
    }
}
