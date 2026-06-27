import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int[] dx = { -1, 1, 0, 0 };
    static final int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        boolean[][] visited = new boolean[n+1][m+1];

        Queue<int[]> queue = new LinkedList<>();

        int totalInitailPts = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < totalInitailPts; i++) {
            
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            queue.add(new int[] { u, v });
            visited[u][v] = true;
        }
        int lastX = -1;
        int lastY = -1;
        while(!queue.isEmpty()) {
            int[] cdnt= queue.poll();
            lastX = cdnt[0];
            lastY = cdnt[1];
            for(int i = 0; i< 4; i++){
                int nx = lastX + dx[i];
                int ny = lastY + dy[i];
                if(nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]){
                    queue.add(new int[] {nx,ny});
                    visited[nx][ny] = true;
                }
            }
        }
        pw.println(lastX + " " + lastY);

        br.close();
        pw.close();
    }
}
