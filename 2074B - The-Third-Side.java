import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TheThirdSide {

    public static void main(String[] args) throws IOException {
        // Fast I/O setup for performance
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
        // Read the size of the sequence
        int n = Integer.parseInt(br.readLine());

        // Read the sequence elements and compute their sum
        StringTokenizer st = new StringTokenizer(br.readLine());
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Long.parseLong(st.nextToken());
        }

        // To maximize the final element, we always choose the largest possible third side, x.
        // When combining two elements a and b, the maximum valid x is a + b - 1.
        // This operation is performed n-1 times. Each time we combine, we sum the numbers
        // and subtract 1.
        // After n-1 operations, we have summed all n initial numbers and subtracted 1 n-1 times.
        // The final result is (sum of all elements) - (n - 1).
        long result = sum - (n - 1);

        pw.println(result);
    }
}