import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Solves the Vlad and a Good Pair problem.
 */
public class GoodPairSolution {

    /**
     * Solves a single test case.
     */
    private static void solve() {
        // Reading n and k
        int n = FastIO.nextInt();
        int k = FastIO.nextInt();
        
        // Reading the binary string
        String s = FastIO.next();

        // 1. Count the number of '0's and '1's in the string.
        int count0 = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                count0++;
            }
        }
        int count1 = n - count0;

        // 2. Calculate the minimum and maximum possible good pairs.
        
        // k_min: Forced good pairs after making max possible '01' pairs.
        // We are left with abs(count0 - count1) identical characters,
        // which must form abs(count0 - count1) / 2 good pairs.
        int k_min = Math.abs(count0 - count1) / 2;

        // k_max: Good pairs from all available '00' and '11' pairs.
        // Integer division naturally handles floor().
        int k_max = (count0 / 2) + (count1 / 2);

        // 3. Check if the target 'k' is achievable.
        // It must be within the [k_min, k_max] range.
        // Also, we can only reduce k_max by 2 at a time (by converting
        // a '00' and a '11' into two '01's), so the parity must match.
        if (k >= k_min && k <= k_max && (k_max - k) % 2 == 0) {
            FastIO.println("YES");
        } else {
            FastIO.println("NO");
        }
    }

    public static void main(String[] args) {
        // Initialize fast I/O
        FastIO.init();
        
        // Read the number of test cases
        int t = FastIO.nextInt();
        
        // Process each test case
        for (int i = 0; i < t; i++) {
            solve();
        }
        
        // Flush the output buffer
        FastIO.close();
    }

    /**
     * A utility class for fast input and output in Java.
     */
    static class FastIO {
        private static BufferedReader br;
        private static StringTokenizer st;
        private static PrintWriter pw;

        public static void init() {
            br = new BufferedReader(new InputStreamReader(System.in));
            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        }

        public static String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public static int nextInt() {
            return Integer.parseInt(next());
        }

        public static void println(Object o) {
            pw.println(o);
        }

        public static void close() {
            pw.close();
        }
    }
}