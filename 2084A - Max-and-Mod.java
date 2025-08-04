import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MaxAndMod {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            solve(br, pw);
        }
        pw.flush();
        pw.close();
        br.close();
    }

    private static void solve(BufferedReader br, PrintWriter pw) throws IOException {
        int n = Integer.parseInt(br.readLine());

        if (n % 2 == 0) {
            pw.println("-1");
        } else {
            // For odd n, the construction p = [n, 1, 2, ..., n-1] works.
            // i=2: max(n, 1) = n. n is odd, so n mod 2 = 1. Correct.
            // i>2: max(i-2, i-1) = i-1. (i-1) mod i = i-1. Correct.
            
            // The sample for n=3 is "3 2 1", which is another valid solution.
            // Our simpler construction [n, n-1, ..., 1] also works for n=3.
            // But let's find one that works for all odd n.
            // The construction p = [n, n-1, ... 1] fails for n=5 (i=3).
            // The construction p = [n, 1, 2, ... n-1] works for all odd n.

            StringBuilder sb = new StringBuilder();
            sb.append(n).append(" ");
            for (int i = 1; i < n; i++) {
                sb.append(i).append(" ");
            }
            pw.println(sb.toString().trim());
        }
    }
}