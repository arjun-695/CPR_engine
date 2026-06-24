package Topics.BFS;

import java.util.*;

public class GraphUtils {
    public static class BFSResult {
        public int[] parent;
        public boolean[] visited;

        public BFSResult(int n){
            this.parent = new int[n+1];
            Arrays.fill(this.parent, -1);
            this.visited = new boolean[n + 1];
        }
    }

    public static BFSResult runBFS(int startNode, int numNodes, List<List<Integer>> adj){
        BFSResult result = new BFSResult(numNodes);
        Queue<Integer> queue = new LinkedList<>();

        // Initialize queue and state markers for startNode
        queue.add(startNode);
        result.visited[startNode] = true;

        while( !queue.isEmpty()) {
            int current = queue.poll();

            for(int neighbor: adj.get(current)){
                if(!result.visited[neighbor])
                {
                    result.visited[neighbor] = true;
                    result.parent[neighbor] = current;
                    queue.add(neighbor);
                }
            }
        }
        return result;
    }
}