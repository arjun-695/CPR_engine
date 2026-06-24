package Topics.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // Initializing the 1 indexed graph adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        // Processing edges
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj.get(u).add(v);
            adj.get(v).add(u); // Un-directed link constraints

        }

        // Executing the imported modular BFS utilitly starting at Node
        GraphUtils.BFSResult bfsState = GraphUtils.runBFS(1, n, adj);

        // Check path valadity to destination node N
        if (!bfsState.visited[n]) {
            System.out.println("IMPOSSIBLE");
            return;
        }

        // Reconstructing structural path from destination backwards using parent
        // pointers
        List<Integer> finalRoute = new ArrayList<>();
        int currentElement = n;
        while (currentElement != -1) {
            finalRoute.add(currentElement);
            currentElement = bfsState.parent[currentElement];
        }

        // Reverse to orient the sequence linearly from Source -> Destination
        Collections.reverse(finalRoute);

        // Printing length constraints and sequence mapping
        System.out.println(finalRoute.size());
        StringBuilder sb = new StringBuilder();
        for (int node : finalRoute) {
            sb.append(node).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
