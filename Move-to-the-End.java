import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Solves the "Move to the End" problem efficiently using precomputation.
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
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextLong();
        }

        // --- Precomputation Step (O(N)) ---

        // 1. Calculate Prefix Maximums
        long[] prefixMax = new long[n];
        prefixMax[0] = a[0];
        for (int i = 1; i < n; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], a[i]);
        }

        // 2. Calculate Suffix Sums
        // suffixSum[i] will store the sum of a[i]...a[n-1]
        // Use an array of size n+1 where suffixSum[n] = 0 as a sentinel value.
        long[] suffixSum = new long[n + 1];
        suffixSum[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + a[i];
        }

        // --- Calculation Step (O(N)) ---
        StringBuilder sb = new StringBuilder();
        for (int k = 1; k <= n; k++) {
            // The prefix to consider is a[0]...a[n-k] (length n-k+1)
            int prefixEndIndex = n - k;
            
            // The suffix to consider is a[n-k+1]...a[n-1] (length k-1)
            int suffixStartIndex = n - k + 1;

            long maxInPrefix;
            // Handle edge case where prefix is empty (when k=n)
            if (prefixEndIndex < 0) {
                maxInPrefix = 0; // The element moved must be from the empty prefix, value doesn't exist.
                                 // But the sum is the total sum, which our logic handles.
                                 // Let's analyze k=n more carefully.
                                 // We choose from a[0..0]. Max is a[0]. Sum of suffix of length n-1 is sum(a[1..n-1])
                                 // ans = a[0] + sum(a[1..n-1]) = total sum.
                                 // Our general logic: k=n -> prefix_idx = 0, suffix_idx = 1
                                 // ans = prefixMax[0] + suffixSum[1] = a[0] + sum(a[1..n-1]). Perfect.
            } else {
                maxInPrefix = prefixMax[prefixEndIndex];
            }
            
            long sumOfSuffix = suffixSum[suffixStartIndex];

            long currentAns = maxInPrefix + sumOfSuffix;
            sb.append(currentAns).append(" ");
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