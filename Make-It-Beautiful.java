import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Solves Codeforces Round 1030 (Div. 2), Problem C - "Make It Beautiful".
 * The solution uses dynamic programming on the "gain" in beauty.
 *
 * 1.  For each number `a[i]`, we precompute a table `costs[g]`, storing the
 *     minimum cost to achieve a beauty gain of `g`. This is a knapsack-style DP
 *     where items are "operations to set a bit `j`".
 *
 * 2.  The tables for all `n` numbers are merged. `DP[g]` will store the minimum
 *     cost to achieve a total gain of `g` across all numbers. This merge step is
 *     a convolution of the individual cost tables.
 *
 * 3.  The final maximum gain is the largest `g` where `DP[g] <= k`.
 */
public class MakeItBeautiful {

    private static final int MAX_BITS = 35; // a[i] < 2^30, k is large, can go up to ~30+
    private static final int MAX_GAIN_PER_NUM = 40; // Heuristic bound on gain
    private static final int OFFSET = MAX_GAIN_PER_NUM;
    private static final long INF = Long.MAX_VALUE / 2;

    public static void main(String[] args) {
        FastReader reader = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int t = reader.nextInt();
        while (t-- > 0) {
            solve(reader, out);
        }
        out.close();
    }

    private static void solve(FastReader reader, PrintWriter out) {
        int n = reader.nextInt();
        long k = reader.nextLong();
        int[] a = new int[n];
        long initialBeauty = 0;
        for (int i = 0; i < n; i++) {
            a[i] = reader.nextInt();
            initialBeauty += Integer.bitCount(a[i]);
        }

        // DP[g] = min cost for total gain g. g is stored with an offset.
        long[] totalCosts = new long[n * MAX_GAIN_PER_NUM + 1];
        Arrays.fill(totalCosts, INF);
        totalCosts[0] = 0;

        for (int i = 0; i < n; i++) {
            // For each number, calculate its cost-for-gain table
            long[] costForNum = new long[2 * OFFSET + 1];
            Arrays.fill(costForNum, INF);
            costForNum[OFFSET] = 0;

            for (int j = 0; j < MAX_BITS; j++) {
                long currentVal = a[i];
                long cost;
                
                long mask = 1L << j;
                if ((currentVal & mask) != 0) { // Bit is already set
                    continue; // Simplified: only consider setting currently-zero bits
                }

                // Smallest y >= currentVal with bit j set
                long y = (currentVal & (~(mask - 1))) | mask;
                if (y < currentVal) {
                    y += (1L << (j+1));
                }
                cost = y - currentVal;
                
                int gain = Integer.bitCount((int)y) - Integer.bitCount((int)currentVal);

                if (gain + OFFSET >= 0 && gain + OFFSET < costForNum.length) {
                    // Update costs table for this single number
                    if (gain >= 0) {
                        for (int g = 2 * OFFSET; g >= gain + OFFSET; g--) {
                           if (costForNum[g - gain] != INF) {
                               costForNum[g] = Math.min(costForNum[g], costForNum[g - gain] + cost);
                           }
                        }
                    } else { // Negative gain
                         for (int g = 0; g <= 2 * OFFSET + gain; g++) {
                           if (costForNum[g - gain] != INF) {
                               costForNum[g] = Math.min(costForNum[g], costForNum[g - gain] + cost);
                           }
                        }
                    }
                }
            }
            
            // Merge the cost table for the current number into the total costs table
            long[] nextTotalCosts = new long[totalCosts.length];
            Arrays.fill(nextTotalCosts, INF);
            for (int total_g = 0; total_g < totalCosts.length; total_g++) {
                if (totalCosts[total_g] == INF) continue;
                for (int num_g_offset = 0; num_g_offset < costForNum.length; num_g_offset++) {
                    if (costForNum[num_g_offset] == INF) continue;
                    int num_g = num_g_offset - OFFSET;
                    int next_total_g = total_g + num_g;
                    if (next_total_g >= 0 && next_total_g < nextTotalCosts.length) {
                         nextTotalCosts[next_total_g] = Math.min(nextTotalCosts[next_total_g], totalCosts[total_g] + costForNum[num_g_offset]);
                    }
                }
            }
            totalCosts = nextTotalCosts;
        }

        long maxGain = 0;
        for (int g = totalCosts.length - 1; g >= 0; g--) {
            if (totalCosts[g] <= k) {
                maxGain = g;
                break;
            }
        }
        
        out.println(initialBeauty + maxGain);
    }

    // Fast I/O class
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
