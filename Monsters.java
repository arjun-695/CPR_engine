
// https://cses.fi/problemset/task/1194/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Queue;
import java.util.Arrays;
import java.util.LinkedList;

class Monsters {
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static char[] dir = { 'D', 'U', 'R', 'L' };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        char[][] grid = new char[row][col];
        Queue<int[]> monsterQueue = new LinkedList<>();
        int[][] monsterTime = new int[row][col];
        int[][] playerTime = new int[row][col];

        for (int i = 0; i < row; i++) {
            Arrays.fill(monsterTime[i], Integer.MAX_VALUE);
            Arrays.fill(playerTime[i], Integer.MAX_VALUE);
        }

        int startX = -1, startY = -1;

        for (int i = 0; i < row; i++) {
            String line = br.readLine();

            for (int j = 0; j < col; j++) {
                grid[i][j] = line.charAt(j);
                if (grid[i][j] == 'M') {
                    monsterQueue.add(new int[] { i, j });
                    monsterTime[i][j] = 0;
                } else if (grid[i][j] == 'A') {
                    startX = i;
                    startY = j;
                    playerTime[i][j] = 0;

                }
            }
        }

        while (!monsterQueue.isEmpty()) {
            int[] current = monsterQueue.poll();
            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {
                int nX = x = dx[i];
                int nY = y + dy[i];

                if (nX >= 0 && nX < row && nY >= 0 && nY < col && grid[nX][nY] != '#'
                        && monsterTime[nX][nY] == Integer.MAX_VALUE) {
                    monsterTime[nX][nY] = monsterTime[x][y] + 1;
                    monsterQueue.add(new int[] { nX, nY });
                }

            }

        }

        Queue<int[]> playerQueue = new LinkedList<>();
        playerQueue.add(new int[] { startX, startY });

        int[][] parent = new int[row][col];
        for (int i = 0; i < row; i++) {
            Arrays.fill(parent[i], -1);
        }

        int endX = -1, endY = -1;

        while (!playerQueue.isEmpty()) {
            int[] curr = playerQueue.poll();
            int x = curr[0];
            int y = curr[1];

            if (x == 0 || x == row - 1 || y == 0 || y == col - 1) {
                endX = x;
                endY = y;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < row && ny >= 0 && ny < col && grid[nx][ny] != '#') {
                    if (playerTime[nx][ny] == Integer.MAX_VALUE && playerTime[x][y] + 1 < monsterTime[nx][ny]) {
                        playerTime[nx][ny] = playerTime[x][y] + 1;
                        parent[nx][ny] = i;
                        playerQueue.add(new int[] { nx, ny });
                    }
                }
            }
        }

        if (endX == -1) {
            System.out.println("NO");

        } else {
            System.out.println("YES");

            StringBuilder path = new StringBuilder();
            while (endX != startX || endY != startY) {
                int p = parent[endX][endY];
                path.append(dir[p]);
                endX -= dx[p];
                endY -= dy[p];
            }

            System.out.println(path.length());
            System.out.println(path.reverse().toString());
        }
    }
}