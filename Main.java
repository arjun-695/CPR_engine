import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in ));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        // Processing edges
        for (int i = 1; i <= n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj.get(u).add(v);
            adj.get(v).add(u); // Un-directed link constraints

        }

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n+1];
        int[] pos = new int[n+1];

        for(int i = 1; i <= n ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            pos[arr[i]] = i;
        }

        // Sort each node's neighbors based on their priority
        for(int i = 1; i <= n; i++){
            Collections.sort(adj.get(i), (a, b) -> Integer.compare(pos[a], pos[b]));
        }

        // Standard BFS Execution 
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        List<Integer> bfsOrder = new ArrayList<>();

        queue.add(1);
        visited[1] = true;

        while(!queue.isEmpty()){
            int curr = queue.poll();
            bfsOrder.add(curr);
            for(int num: adj.get(curr)) {
                if(!visited[num]) {
                    visited[num] = true;
                    queue.add(num);
                }
            }
        }

        boolean isValid = true;
        for (int i = 1; i <= n; i++) {
            if (bfsOrder.get(i - 1) != arr[i]) {
                isValid = false;
                break;
            }
        }

        if (isValid) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
    
}
