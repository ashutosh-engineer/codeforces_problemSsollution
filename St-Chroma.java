import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Solves the "St. Chroma" problem from the prompt.
 * The logic constructs an optimal permutation for the given constraints.
 */
public class Main {

    public static void main(String[] args) {
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int t = sc.nextInt();
        while (t-- > 0) {
            solve(sc, out);
        }
        out.flush();
    }

    private static void solve(FastReader sc, PrintWriter out) {
        int n = sc.nextInt();
        int x = sc.nextInt();

        StringBuilder sb = new StringBuilder();

        // Case 1: Maximize color x = 0
        if (x == 0) {
            // Place 1 to n-1 first, then 0 at the end.
            for (int i = 1; i < n; i++) {
                sb.append(i).append(" ");
            }
            if (n > 0) {
                sb.append(0);
            }
        }
        // Case 2: Maximize color x = n
        else if (x == n) {
            // Only the last cell can be color n. Any permutation works.
            // Simplest is 0, 1, ..., n-1.
            for (int i = 0; i < n; i++) {
                sb.append(i).append(" ");
            }
        }
        // Case 3: Maximize color 0 < x < n
        else {
            // Optimal construction:
            // 1. Place {0, ..., x-1}
            // 2. Place {x+1, ..., n-1}
            // 3. Place {x} at the very end
            for (int i = 0; i < x; i++) {
                sb.append(i).append(" ");
            }
            for (int i = x + 1; i < n; i++) {
                sb.append(i).append(" ");
            }
            sb.append(x);
        }

        out.println(sb.toString().trim());
    }

    // Standard FastReader class for competitive programming
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}