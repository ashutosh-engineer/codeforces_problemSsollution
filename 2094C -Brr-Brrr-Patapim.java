import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Solves the "Brr Brrr Patapim" problem.
 * The logic is based on the strict guarantee that G[i][j] = p[i + j],
 * which implies all cells on an anti-diagonal i+j=k must have the same value, p[k].
 * We can therefore reconstruct p[k] by reading any single valid cell on that anti-diagonal.
 */
public class Main {

    /**
     * A standard Fast I/O class for competitive programming in Java.
     */
    static class FastScanner {
        private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    // This is highly unlikely in a controlled environment.
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    /**
     * Executes the logic for a single test case.
     * @param sc The FastScanner instance for input.
     * @param out The PrintWriter instance for output.
     */
    private static void solve(FastScanner sc, PrintWriter out) {
        int n = sc.nextInt();
        // Using 0-based indexing for the grid array in Java.
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        // The permutation p will be stored 1-indexed for clarity and problem alignment.
        int[] p = new int[2 * n + 1];
        boolean[] isValueUsed = new boolean[2 * n + 1];

        // Reconstruct p[k] for each k from 2 to 2n.
        // For each k, we find one valid (i, j) pair where i+j=k and read G[i][j].
        for (int k = 2; k <= 2 * n; k++) {
            int val;
            // A convenient (i,j) can always be found on the first row or last column.
            if (k <= n + 1) {
                // To get sum k, we use row 1 (0-indexed i=0).
                // The required column is j = k-1 (0-indexed j=k-2).
                val = grid[0][k - 2];
            } else { // k > n + 1
                // To get sum k, we use column n (0-indexed j=n-1).
                // The required row is i = k-n (0-indexed i=k-n-1).
                val = grid[k - n - 1][n - 1];
            }
            p[k] = val;
            isValueUsed[p[k]] = true;
        }

        // Find p[1]: it's the only number from 1 to 2n not yet used.
        for (int val = 1; val <= 2 * n; val++) {
            if (!isValueUsed[val]) {
                p[1] = val;
                break;
            }
        }

        // Print the final reconstructed permutation.
        for (int k = 1; k <= 2 * n; k++) {
            out.print(p[k]);
            if (k < 2 * n) {
                out.print(" ");
            }
        }
        out.println();
    }

    /**
     * Main method to handle all test cases.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        try (PrintWriter out = new PrintWriter(System.out)) {
            int t = sc.nextInt();
            while (t-- > 0) {
                solve(sc, out);
            }
        }
    }
}