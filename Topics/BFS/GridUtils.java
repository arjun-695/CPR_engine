package Topics.BFS;

import java.util.LinkedList;
import java.util.Queue;

public class GridUtils {
    public static final int[] dx = { -1, 1, 0, 0 };
    public static final int[] dy = { 0, 0, 1, -1 };
    public static final char[] stepDir = { 'U', 'D', 'R', 'L' };

    public static class GridBFSResult {
        public boolean[][] visited;
        public char[][] pathStep;
        public int[][] parentX;
        public int[][] parentY;

        public GridBFSResult(int n, int m) {
            this.visited = new boolean[n][m]; 
            this.pathStep = new char[n][m];
            this.parentX = new int[n][m];
            this.parentY = new int[n][m];
        }
    }

    public static GridBFSResult runGridBFS(int startX, int startY, int n, int m, char grid[][]) {
        GridBFSResult res = new GridBFSResult(n, m);
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[] { startX, startY });
        res.visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int cx = curr[0];
            int cy = curr[1];

            // if we hit target 'B', we could optionally break early - optimization
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // Bound checking and validation

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !res.visited[nx][ny] && grid[nx][ny] != '#') {
                    res.visited[nx][ny] = true;
                    res.parentX[nx][ny] = cx;
                    res.parentY[nx][ny] = cy;
                    res.pathStep[nx][ny] = stepDir[i]; // Remember direction character
                    queue.add(new int[] { nx, ny });
                }
            }
        }
        return res;
    }

}
