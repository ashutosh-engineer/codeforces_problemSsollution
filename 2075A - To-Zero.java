import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ToZero {

    public static void main(String[] args) throws IOException {
        // Use a fast I/O setup for performance
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long n = Long.parseLong(st.nextToken());
            long k = Long.parseLong(st.nextToken());
            
            solve(n, k, pw);
        }
        
        pw.flush();
        pw.close();
        br.close();
    }

    private static void solve(long n, long k, PrintWriter pw) {
        long operations;

        // Since n >= k >= 3, we don't need to check for n=0 cases.

        if (n % 2 == 0) {
            // If n is even, it will always remain even.
            // We always subtract the largest possible even number, which is k-1.
            // Operations = ceil(n / (k-1))
            long divisor = k - 1;
            operations = (n + divisor - 1) / divisor;
        } else {
            // If n is odd, the first operation must be subtracting an odd number.
            // The best move is to subtract k. This takes 1 operation.
            // n becomes n-k, which is an even number.
            long remainingN = n - k;
            
            // Now, we need to reduce the even number 'remainingN' to 0.
            // The number of operations for this part is ceil(remainingN / (k-1)).
            long divisor = k - 1;
            long remainingOps = (remainingN + divisor - 1) / divisor;
            
            operations = 1 + remainingOps;
        }
        
        pw.println(operations);
    }
}