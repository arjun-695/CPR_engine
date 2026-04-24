import java.io.*;
import java.util.*;

public class Main{

    // Fast I/O class
    static class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader (new InputStreamReader(System.in)); 
        }

        String next() {
            while(st == null || !st.hasMoreElements()){
                try {
                    String line = br.readLine();
                    if(line == null) break;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st != null && st.hasMoreTokens() ? st.nextToken() : "";
        }

        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextdouble() { return Double.parseDouble(next()); }

        String nextLine(){
            String str = "";
            try {
                if( st != null && st.hasMoreTokens()) {
                    str = st.nextToken("\n");
                } else {
                    str = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

    }

    public static void main(String[] args) {
        FastReader in = new FastReader();
        // PrintWriter handles buffered output , which is much faster
        PrintWriter out = new PrintWriter(System.out);

        int t = 1;
        t = in.nextInt(); //Comment if the problem has only 1 testcase
        while( t-- > 0)
        {
            solve(in,out);
        }

        //CRITICAL: Flust the output stream at the end
        out.flush();
    }

    static void solve(FastReader in, PrintWriter out) {
        // int n = in.nextInt();
        // out.println(n * 2);
    }
}