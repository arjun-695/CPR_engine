// Tree Path and State Propgation: Tracking states as you descend from the root to leaves or move between arbitrary nodes 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class KefaPark {

    static List<List<Integer>> adj;
    static int restCount = 0;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        adj = new ArrayList<>();

        for (int i = 0; i <= n; i++)
            adj.add(new ArrayList<>());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            // st = new StringTokenizer(br.readLine());// Reads a new line every iteration
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        System.out.println(Arrays.toString(arr));
        System.out.println(adj);
        int catCount = 0;
        dfs(1, 0, catCount, k);

        System.out.println(restCount);
    }

    private static void dfs(int node, int parent, int catCount, int maxCatsAll) {
        if (arr[node] == 1)
            catCount++;
        else {
            catCount = 0; // Reset
        }

        if (catCount > maxCatsAll)
            return;
        boolean leafNode = true;
        for (int next : adj.get(node)) {

            if (next != parent) {
                leafNode = false;
                dfs(next, node, catCount, maxCatsAll);
            }
        }

        if (leafNode)
            restCount++;
    }
}
