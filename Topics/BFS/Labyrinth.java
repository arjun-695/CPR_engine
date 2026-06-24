package Topics.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Labyrinth {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] grid = new char[n][m];
        int startX = 0, startY= 0, endX = 0, endY = 0;

        for(int i = 0; i < n; i++){
            String line = br.readLine();
            for(int j = 0; j < m; j++){
                grid[i][j] = line.charAt(j);
                if( grid[i][j] == 'A' ) { startX = i; startY = j;}
                if( grid[i][j] == 'B' ) { endX = i; endY = j;}
            }
        }

        GridUtils.GridBFSResult result = GridUtils.runGridBFS(startX, startY, n, m, grid);

        if( !result.visited[endX][endY]) {
            System.out.println("NO");
            return;
        }

        System.out.println("Yes");

        //Reconstruct string direction path backwards 
        StringBuilder sb = new StringBuilder();
        int currX = endX, currY = endY;

        while(currX != startX || currY != startY){
            sb.append(result.pathStep[currX][currY]);
            int prevX = result.parentX[currX][currY];
            int prevY = result.parentY[currX][currY];
            currX = prevX;
            currY = prevY;

        }
        sb.reverse();
        System.out.println(sb.length());
        System.out.println(sb.toString());
    }
}

