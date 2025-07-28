import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Solves the deck equalization problem.
 */
public class DeckEqualitySolution {

    /**
     * Solves a single test case using the derived logic.
     *
     * @param br The BufferedReader for reading input.
     * @param pw The PrintWriter for writing output.
     * @throws IOException If an I/O error occurs.
     */
    private static void solve(BufferedReader br, PrintWriter pw) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        // Use long to prevent potential overflow as a+b+c can exceed Integer.MAX_VALUE
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long c = Long.parseLong(st.nextToken());

        // 1. Calculate the total number of cards
        long totalSum = a + b + c;

        // 2. Condition 1: Check if the total sum is divisible by 3.
        // If not, it's impossible to form three equal integer-sized decks.
        if (totalSum % 3 != 0) {
            pw.println("NO");
            return;
        }

        // 3. Calculate the required final size for each deck.
        long targetSize = totalSum / 3;

        // 4. Condition 2: Check if the target size is achievable.
        // The sizes of decks 'a' and 'b' can only increase or stay the same.
        // This means the target size must be at least as large as 'b'.
        // If targetSize < b, we would need to take cards from deck 'b', which is not allowed.
        // The condition targetSize >= a is implicitly satisfied since b > a.
        if (targetSize >= b) {
            pw.println("YES");
        } else {
            pw.println("NO");
        }
    }

    public static void main(String[] args) {
        // Use try-with-resources for automatic closing of I/O streams
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)))) {

            // Read the number of test cases
            int t = Integer.parseInt(br.readLine());

            // Process each test case
            for (int i = 0; i < t; i++) {
                solve(br, pw);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}