import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Solves the Binary String Grid problem by calculating the total number of ones
 * using a mathematical formula.
 */
public class BinaryGridOnesSolution {

    /**
     * Solves a single test case.
     *
     * @param sc The Scanner for reading input.
     * @param pw The PrintWriter for writing output.
     */
    private static void solve(Scanner sc, PrintWriter pw) {
        // Read input for the test case
        int n = sc.nextInt();
        String s = sc.next();

        // 1. Count the number of '1's in the original string s.
        int countOnes = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                countOnes++;
            }
        }

        // 2. Calculate the number of '0's.
        int countZeros = n - countOnes;

        // 3. Apply the derived formula.
        // Start with n * countOnes (as if all n rows were identical to s).
        // Subtract 1 for each '1' in s, because it gets flipped to '0' in its row.
        // Add 1 for each '0' in s, because it gets flipped to '1' in its row.
        long totalOnes = (long) n * countOnes - countOnes + countZeros;

        // Print the final result for the test case.
        pw.println(totalOnes);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Using PrintWriter for potentially faster and buffered output.
        PrintWriter pw = new PrintWriter(System.out);

        // Read the number of test cases.
        int t = sc.nextInt();

        // Loop through all test cases.
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }

        // It's important to flush the PrintWriter to ensure all output is written.
        pw.flush();
        pw.close();
        sc.close();
    }
}