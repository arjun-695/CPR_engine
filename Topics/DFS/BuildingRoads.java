package Topics.DFS;
// https://cses.fi/problemset/task/1666 
// DFS- connected comoponents
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
public class BuildingRoads {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < m; i++ ){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] visited = new boolean[n + 1];
        ArrayList<Integer> repre = new ArrayList<>();
        for(int i = 1; i <= n ; i++ )
        {
            if(!visited[i]){
                repre.add(i);
                dfs(i, visited, adj);
            }
        }
        int ans = repre.size() - 1;

        // Fast output using StringBuilder
        StringBuilder sb = new StringBuilder();
        sb.append(ans).append("\n");

        for(int i = 0; i < ans; i++){
            sb.append(repre.get(i)).append(" ").append(repre.get(i+1)).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int i, boolean[] visited, List<List<Integer>> adj){
        visited[i] = true;
        for(int neigh: adj.get(i)){
            if(!visited[neigh])
            {
                dfs(neigh, visited, adj);
            }
        }
    }
}
