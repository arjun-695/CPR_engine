
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class subordinates {
    static ArrayList<Integer>[] adj; // Building ArrayList for dfs
    static int[] subordinateCount;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        // Initialize the adjacency list and the ans arr
        adj = new ArrayList[n+1];
        subordinateCount = new int[n+1];

        for(int i = 1; i <= n ; i++){
            adj[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 2; i <= n; i++){
            int boss = Integer.parseInt(st.nextToken());
            adj[boss].add(i);
        }


        new Thread(null, new Runnable(){
            public void run(){
                dfs(1);
                StringBuilder sb = new StringBuilder();
                for(int i = 1; i <= n; i++){
                    sb.append(subordinateCount[i]).append(" ");
                }
                System.out.println(sb.toString().trim());
            }       
        }, "DFS-Thread", 1 << 26).start(); // 64MB Stack size allocation
        
    }

    public static int dfs(int node){
        // int count = 0;
        // for(int child : adj[node]){
        //     count +=1 + dfs(child);
        // }

        // subordinateCount[node] = count;

        // return count;

        int count = 0;
        for(int child : adj[node]){
            count += 1 + dfs(child);
        }

        subordinateCount[node] = count;

        return count;
    }
}
