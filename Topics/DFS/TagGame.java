package Topics.DFS;
// MultiSource DFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TagGame {
    static int a, b = 0;
    static List<List<Integer>> adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        int[] distA = new int[a + 1];
        int[] distB = new int[a + 1];
        adj = new ArrayList<>();
        for (int i = 0; i <= a; i++)
            adj.add(new ArrayList<>());

        for (int i = 1; i < a; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj.get(u).add(v);
            adj.get(v).add(u);

        }

        dfs(1, 0, 0, distA);
        dfs(b, 0, 0, distB);

        int ans = 0;
        for (int i = 1; i <= a; i++) {
            if (distB[i] < distA[i]) {
                ans = Math.max(ans, 2 * distA[i]);
            }
        }
        System.out.println(ans);
    }

    private static void dfs(int u, int parent, int dist, int[] distMat) {
        distMat[u] = dist;

        for (int child : adj.get(u)) {
            if (child != parent) {
                dfs(child, u, dist + 1, distMat);
            }
        }
    }
}
