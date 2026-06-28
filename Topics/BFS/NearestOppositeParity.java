
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class NearestOppositeParity {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            int left = i - arr[i];
            int right = i + arr[i];

            if (left >= 0) {
                adj[left].add(i);
            }

            if (right < n)
                adj[right].add(i);
        }

        int[] result = new int[n];
        Arrays.fill(result, -1);

        // Run Bfs from Odds(1) to find shortest path to Evens
        solveBFS(adj, arr, n, result, 1);
        // Run Bfs from Evens(0) to find shorteset path to Odds
        solveBFS(adj, arr, n, result, 0);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb.toString());
    }

    public static void solveBFS(List<Integer>[] adj, int[] arr, int n, int[] result, int parity) {
        Queue<Integer> queue = new LinkedList<>();
        int[] dist = new int[n];
        Arrays.fill(dist, -1);

        for (int i = 0; i < n; i++) {
            if (arr[i] % 2 == parity) {
                queue.add(i);
                dist[i] = 0;
            }
        }

        // Look at all the nodes that can jump to the current node
        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int neighbor : adj[curr]) {
                if (dist[neighbor] == -1) {
                    dist[neighbor] = dist[curr] + 1;
                    queue.add(neighbor);

                    if (arr[neighbor] % 2 != parity) {
                        result[neighbor] = dist[neighbor];
                    }
                }
            }
        }
    }
}
