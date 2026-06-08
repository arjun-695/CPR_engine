
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TreeDiameter {
    static List<List<Integer>> adj;
    static int maxDiameter = 0;
    static int[] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        dp = new int[n+1];
        adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());

        for(int i = 0; i < n-1; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // Starting DFS from root node 1, with parent 0
        dfs(1, 0);

        System.out.println(maxDiameter);
    }

    private static int dfs(int v, int parent){
        int max1 = 0;// Deepest Branch
        int max2 = 0;// Second Deepest Branch
        for(int child: adj.get(v)){
            if(child == parent)continue;

            int depth = dfs(child, v) + 1;

            maxDiameter = Math.max(maxDiameter, max1 + depth);

            if(depth > max1){
                max2 = max1; // Old champ drops to 2nd place
                max1 = depth;// new champ takes 1st place
            }else if(depth > max2){
                max2 = depth;
            }
        }
        return max1;
    } 

    private static void dfsDP(int v, int parent){
        int max1 = 0;
        int max2 = 0;

        for(int u : adj.get(v))
        {
            if(u == parent) continue;

            // Post order traversal (bottom-up processing)
            dfs(u, v);

            //Current child depth including the edge from v to u
            int currentDepth = dp[u] + 1;

            // track top 2 max depth from children
            if(currentDepth > max1){
                max2 = max1;
                max1 = currentDepth;
            } else if( currentDepth > max2){
                max2 = currentDepth;
            }
        }

        // Update global diameter
        maxDiameter = Math.max(maxDiameter, max1 + max2);

        // store max height of subtree rooted at v in DP table 
        dp[v] = max1;

    }
}
