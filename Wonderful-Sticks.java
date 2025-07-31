import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

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
                    // This is unlikely to happen in a controlled environment like a judge.
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    /**
     * Reverses a sub-array in place.
     * @param arr The array to modify.
     * @param start The starting index of the sub-array (inclusive).
     * @param end The ending index of the sub-array (inclusive).
     */
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * Solves a single test case.
     * @param sc The FastScanner instance for reading input.
     * @param out The PrintWriter instance for writing output.
     */
    private static void solve(FastScanner sc, PrintWriter out) {
        int n = sc.nextInt();
        String s = sc.next();

        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = n - i;
        }

        int l = 0;
        while (l < n - 1) {
            if (s.charAt(l) == '<') {
                l++;
                continue;
            }
            
            // Found the start of a '>' block at index l
            int r = l;
            while (r + 1 < n - 1 && s.charAt(r + 1) == '>') {
                r++;
            }

            // The block s[l...r] corresponds to permutation indices p[l...r+1]
            reverse(p, l, r + 1);
            
            // Move pointer to the position after the processed block
            l = r + 2;
        }

        for (int i = 0; i < n; i++) {
            out.print(p[i]);
            if (i < n - 1) {
                out.print(" ");
            }
        }
        out.println();
    }

    /**
     * The main method to handle all test cases.
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