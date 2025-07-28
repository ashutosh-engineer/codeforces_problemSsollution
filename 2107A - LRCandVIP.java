import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Solves the array partition problem by finding a partition B, C
 * such that gcd(B) != gcd(C).
 */
public class ArrayPartitionSolution {

    /**
     * Computes the greatest common divisor (GCD) of two non-negative integers
     * using the Euclidean algorithm.
     *
     * @param a The first integer.
     * @param b The second integer.
     * @return The GCD of a and b.
     */
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /**
     * Solves a single test case.
     *
     * @param br The BufferedReader for reading input.
     * @param pw The PrintWriter for writing output.
     * @throws IOException If an I/O error occurs.
     */
    private static void solve(BufferedReader br, PrintWriter pw) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        // 1. Check if all elements are identical. If so, no solution exists.
        boolean allSame = true;
        for (int i = 1; i < n; i++) {
            if (a[i] != a[0]) {
                allSame = false;
                break;
            }
        }
        if (allSame) {
            pw.println("No");
            return;
        }

        // 2. A solution is guaranteed to exist.
        pw.println("Yes");

        // 3. Precompute prefix and suffix GCDs for O(n*logA) solution.
        int[] prefixGcd = new int[n];
        prefixGcd[0] = a[0];
        for (int i = 1; i < n; i++) {
            prefixGcd[i] = gcd(prefixGcd[i - 1], a[i]);
        }

        int[] suffixGcd = new int[n];
        suffixGcd[n - 1] = a[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixGcd[i] = gcd(suffixGcd[i + 1], a[i]);
        }

        // 4. Find the first valid "one vs. rest" partition.
        // Such a partition is guaranteed to exist if not all elements are the same.
        for (int i = 0; i < n; i++) {
            int restGcd;
            if (i == 0) {
                // GCD of all elements except the first one
                restGcd = suffixGcd[1];
            } else if (i == n - 1) {
                // GCD of all elements except the last one
                restGcd = prefixGcd[n - 2];
            } else {
                // GCD of elements before and after a[i]
                restGcd = gcd(prefixGcd[i - 1], suffixGcd[i + 1]);
            }
            
            // Check if the partition {a[i]} and {rest} is valid.
            // gcd({a[i]}) is a[i]. We need a[i] != gcd({rest}).
            if (a[i] != restGcd) {
                StringBuilder result = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (j == i) {
                        result.append("1 "); // Element a[i] is in group B
                    } else {
                        result.append("2 "); // All other elements are in group C
                    }
                }
                pw.println(result.toString().trim());
                return;
            }
        }
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)))) {
            int t = Integer.parseInt(br.readLine());
            for (int i = 0; i < t; i++) {
                solve(br, pw);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}```