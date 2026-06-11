
// https://cses.fi/problemset/task/1668
// Graph Coloring Applications: Solving partitioning and structural constraints by leveraging the properties of bipartite graphs

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BuildingTeams {
    static int a, b = 0;
    static List<List<Integer>> adj;
    static int arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        adj = new ArrayList<>();
        arr = new int[a + 1];
        for (int i = 0; i <= a; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < b; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj.get(u).add(v);
            adj.get(v).add(u);

        }
        // System.out.println(adj.toString());
        for (int i = 1; i <= a; i++) {
            if (arr[i] != 0)
                continue;
            arr[i] = 1;
            for (int child : adj.get(i)) {
                arr[child] = 2;
            }
        }
        for (int arr1 : arr)
            System.out.print(arr1 + " ");
    }
}
