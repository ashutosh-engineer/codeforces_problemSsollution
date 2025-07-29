import java.util.Scanner;
import java.util.StringBuilder;

public class Main {

    /**
     * Main method to handle multiple test cases.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            int t = scanner.nextInt();
            // Loop through each test case.
            while (t-- > 0) {
                solve(scanner);
            }
        } finally {
            scanner.close();
        }
    }

    /**
     * Solves a single test case for Vadim's Collection problem.
     */
    private static void solve(Scanner scanner) {
        // Read the input beautiful phone number as a string.
        String s = scanner.next();

        // Step 1: Count the frequency of each digit in the input string.
        int[] digitCounts = new int[10];
        for (char c : s.toCharArray()) {
            digitCounts[c - '0']++;
        }

        // Step 2: Build the result string greedily from left to right.
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            // Determine the minimum required digit for the current position (i).
            // For the 1st digit (i=0), requirement is >= 10-1 = 9
            // For the 2nd digit (i=1), requirement is >= 10-2 = 8
            // and so on...
            int minRequired = 10 - (i + 1);

            // Find the smallest available digit that meets the requirement.
            for (int d = minRequired; d < 10; d++) {
                if (digitCounts[d] > 0) {
                    // Append the chosen digit to the result.
                    result.append(d);
                    // Decrement its count as it has been used.
                    digitCounts[d]--;
                    // Break the inner loop since we've found the best digit for this position.
                    break;
                }
            }
        }
        
        // Print the final, smallest beautiful number.
        System.out.println(result.toString());
    }
}