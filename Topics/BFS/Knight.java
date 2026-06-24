package Topics.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Knight {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        if (line == null || line.trim().isEmpty())
            return;
        int n = Integer.parseInt(line.trim());

        int[][] resultGrid = KnightUtils.computeKnightDistances(n);

        // Fast I/O using StringBuilder for large grids
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(resultGrid[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}
