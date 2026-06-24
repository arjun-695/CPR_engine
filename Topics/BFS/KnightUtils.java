package Topics.BFS;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class KnightUtils {
    public static final int[] dx = {-2, -2, -1,-1, 1, 1, 2, 2};
    public static final int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};

    public static int[][] computeKnightDistances(int n){
        int[][] dist = new int[n][n];
        for(int i = 0; i < n; i++){
            Arrays.fill(dist[i], -1);            
        }

        Queue<int[]> queue = new LinkedList<>();

        // Single-source initialization at the top-left
        dist[0][0] = 0;
        queue.add(new int[] {0,0});
        
        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int cx = curr[0];
            int cy = curr[1];

            for(int i = 0; i < 8; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx >= 0 && nx < n && ny >= 0 && ny < n && dist[nx][ny] == -1){
                    dist[nx][ny] = dist[cx][cy] + 1;
                    queue.add(new int[] {nx, ny});
                }
            }
        }
        return dist;
    }
    
}
