import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

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

    /**
     * Solves a single test case for the "Down with Brackets" problem.
     */
    private static void solve(FastReader sc, PrintWriter out) {
        String s = sc.next();
        int n = s.length();
        int balance = 0;
        boolean canBeBroken = false;

        // We check for a "split point" up to the second to last character.
        // If balance becomes 0 before the end, s is a concatenation like s1s2...
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == '(') {
                balance++;
            } else {
                balance--;
            }

            // If balance is zero, we've found a complete top-level component.
            // This means the full string s is a concatenation of s[0...i] and s[i+1...n-1].
            if (balance == 0) {
                canBeBroken = true;
                break; // Found our answer, no need to check further.
            }
        }

        if (canBeBroken) {
            out.println("YES");
        } else {
            // If the loop finishes without finding a split, it means the entire string
            // is one single component like ((...)), which cannot be broken.
            out.println("NO");
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
    }
}