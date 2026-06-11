package Topics.DFS;
// Graph Transformations: Solving problems that involve modifying or navigating graphs based on specific edge rules
// https://codeforces.com/contest/727/problem/A
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Transformations {
    static long a, b = 0;
    static List<Long> adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());
        adj = new ArrayList<>();

        boolean flag = solve(a, b);

        if (flag) {
            System.out.println("YES");
            System.out.println(adj.size());
            System.out.println(adj.reversed().toString());
        } else {
            System.out.println("No");
        }

    }

    private static boolean dfs(Long a, Long b) {
        if (a > b)
            return false;

        adj.addLast(a);

        if (a == b)
            return true;
        if (dfs(a * 2, b))
            return true;
        if (dfs(a * 10 + 1, b))
            return true;

        adj.removeLast();
        return false;
    }

    public static boolean solve(long a, long b) {

        while (b >= a) {
            adj.addLast(b);
            if (b == a)
                return true;
            if (b % 2 == 0) {
                b /= 2;
            } else if (b % 10 == 1) {
                b /= 10;
            } else {
                return false;
            }

        }
        return false;
    }
}
