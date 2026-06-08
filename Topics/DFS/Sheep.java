
// https://www.spoj.com/problems/KOZE/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sheep {

    static boolean visited[][];
    static int currentSheep = 0;
    static int currentWolves = 0;
    static boolean touchBoundary = false;
    static char[][] grid;

    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        grid = new char[n][m];
        visited = new boolean[n][m];
        int totalWolves = 0;
        int totalSheeps = 0;

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                grid[i][j] = str.charAt(j);
                if (grid[i][j] == 'k')
                    totalSheeps++;
                else if (grid[i][j] == 'v')
                    totalWolves++;

            }
        }

        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < m; j++) {
        //         if (grid[i][j] != '#' && visited[i][j] != true) {
        //             currentSheep = 0;
        //             currentWolves = 0;
        //             touchBoundary = false;
        //             dfs(i, j, n, m);

        //             if (!touchBoundary) {
        //                 if (currentSheep > currentWolves)
        //                     totalWolves -= currentWolves;
        //                 else {
        //                     totalSheeps -= currentSheep;
        //                 }
        //             }
        //         }
        //     }
        // }


       for(int i = 0; i< n; i++){
            for(int j = 0 ; j < m ; j++){
            if(grid[i][j] != '#' && visited[i][j] == false){
                currentWolves = 0;;
            currentSheep = 0;
            touchBoundary = false;
            dfs(i,j,n, m);

            if(!touchBoundary){
                if(currentSheep > currentWolves) totalWolves -= currentWolves;
                else  totalSheeps -= currentSheep;
            }
            }
        }
    } 
        System.out.println(totalSheeps + " " + totalWolves);
    }

    private static void dfs(int r, int c, int n, int m) {

        // visited[r][c] = true;
        // if (r == 0 || c == 0 || r == n - 1 || c == m - 1)
        //     touchBoundary = true;

        // if (grid[r][c] == 'k')
        //     currentSheep++;
        // if (grid[r][c] == 'v')
        //     currentWolves++;

        // for (int i = 0; i < 4; i++) {
        //     int nx = r + dx[i];
        //     int ny = c + dy[i];

        //     if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
        //         if (!visited[nx][ny] && grid[nx][ny] != '#') {
        //             dfs(nx, ny, n, m);
        //         }
        //     }
        // }
        visited[r][c] = true;
        if(r == 0 || r == n - 1 || c ==0 || c == m - 1) {touchBoundary = true;}

        if(grid[r][c] == 'k') currentSheep++;
        if(grid[r][c] == 'v') currentWolves++;

        for(int i = 0; i < 4 ; i++){
            int nx = r + dx[i];
            int ny = c + dy[i];

            if(nx >= 0 && nx < n && ny >= 0 && ny < m){
                if(!visited[nx][ny] && grid[nx][ny] != '#')
                dfs(nx, ny, n, m);
            }
        }
    }

}
