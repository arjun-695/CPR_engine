package Topics.BinaryLIfting;
import java.util.*;

public class BinaryLIftingLCA {
    private int N;
    private int LOG;
    private List<List<Integer>> adj;
    private int[] depth;
    private int[][] up;

    public BinaryLIftingLCA(int n, List<List<Integer>> adjList, int root){
        this.N= n;
        this.LOG = (int) (Math.log(n)/ Math.log(2)) + 1;
        this.adj = adjList;
        this.depth = new int[n+1];
        this.up = new int[n + 1][ LOG ];

        // Step 1: Precompute depths and immediate parents (up[i][0])
        dfs(root, root, 0);

        // Step 2: Fill the DP table for binary Lifting
        initBinaryLifting();

    }

    private void dfs(int node, int parent, int d){
        depth[node] = d;
        up[node][0] = parent; // Immediate parent

        for( int neighbor: adj.get(node)){
            if(neighbor != parent){
                dfs(neighbor, node, d+1 );
            }
        }
    }


    private void initBinaryLifting(){
        for(int j = 1; j < LOG; j++){
            for(int i = 1; i <= N; i++){
                // Node i ka 2^jth ancestor hai (2^(j-1) acnestor) ka 2^(j-1) acnestor
                up[i][j] = up[up[i][j-1]][j-1];
            }
        }
    }
    // Function to get K-th ancestors of a node 
    public int getKthAncestor(int node, int k){
        for (int i = 0; i < LOG; i++) {
            if((k & (1 << i)) != 0) // check if j-th bit is set in K 
            {
                node = up[node][i];
                if ( node == 0 ) return -1; // If out of bounds/ root's parent
            }
        }
        return node;
    }

    // Function to find LCA of u and v
    public int getLCA(int u, int v)
    {
        if(depth[u] < depth[v])
        {
            // Swap to make sure u is deeper
            int temp = u; u = v; v = temp;
        }

        // Step 1: Bringing u to the same depth as v
        int diff = depth[u] - depth[v];
        u = getKthAncestor(u, diff);

        if( u == v ) return u; // if u and v are in the same branch 

        // Step 2: Lift both nodes simultaneously
        for (int i = LOG - 1; i >= 0; i--) {
            if( up[u][i] != up[v][i])
            {
                u = up[u][i];
                v = up[v][i];
            }
        }

        // Now u and v are just below the LCA
        return up[u][0];
    }
}
