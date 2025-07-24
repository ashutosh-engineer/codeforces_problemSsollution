import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Solution for the "A. False Alarm" problem.
 * This program determines if it's possible to traverse a series of doors,
 * some of which may be closed, using a one-time button that opens all doors
 * for a specific duration.
 */
public class Main {

    /**
     * Main method to handle test cases.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        FastReader sc = new FastReader();
        int t = sc.nextInt();
        while (t-- > 0) {
            solve(sc);
        }
    }

    /**
     * Solves a single test case.
     * @param sc FastReader instance for input.
     */
    public static void solve(FastReader sc) {
        int n = sc.nextInt();
        int x = sc.nextInt();
        
        int firstClosed = -1;
        int lastClosed = -1;

        for (int i = 0; i < n; i++) {
            int doorState = sc.nextInt();
            if (doorState == 1) {
                if (firstClosed == -1) {
                    firstClosed = i;
                }
                lastClosed = i;
            }
        }

        // The problem guarantees at least one closed door, so firstClosed will not be -1.
        
        // The optimal strategy is to press the button at the moment of reaching the first closed door.
        // Let's use 0-based indexing. Yousef reaches door `i` at time `t=i`.
        // He reaches the first closed door (index `firstClosed`) at time `t = firstClosed`.
        // He presses the button. It stays active for the time interval [firstClosed, firstClosed + x).
        // To pass the last closed door (index `lastClosed`), he must reach it at time `t = lastClosed`
        // while the button is still active.
        // The condition is: lastClosed < firstClosed + x
        // This is equivalent to: lastClosed - firstClosed < x

        // If there is only one closed door, firstClosed == lastClosed.
        // The difference is 0. Since x >= 1, 0 < x is always true, which is correct.
        
        if (lastClosed - firstClosed < x) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    /**
     * A utility class for faster input reading.
     */
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
