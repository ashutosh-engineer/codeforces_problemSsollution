import java.util.Scanner;

/**
 * Solution for the B. Bobritto Bandito problem.
 */
public class BobrittoBandito {

    /**
     * Solves a single test case.
     * @param sc The scanner to read input from.
     */
    private static void solve(Scanner sc) {
        // Read the input values for the test case.
        int n = sc.nextInt();
        int m = sc.nextInt();
        int l = sc.nextInt();
        int r = sc.nextInt();

        // The total number of houses the segment must shrink by to go from day n to day m.
        int totalShrinkAmount = n - m;

        // The new segment [l', r'] must be a sub-segment of [l, r].
        // It must also contain 0, so l' <= 0 and r' >= 0.

        // We can shrink from the left by at most -l houses (from l up to, but not including, 0).
        // Let's greedily shrink from the left as much as possible.
        // The amount to shrink is the smaller of the total required shrinkage
        // and the available houses on the left side.
        int shrinkFromLeft = Math.min(totalShrinkAmount, -l);

        // The new left boundary after shrinking.
        int lPrime = l + shrinkFromLeft;

        // The remaining shrinkage must come from the right side.
        int shrinkFromRight = totalShrinkAmount - shrinkFromLeft;

        // The new right boundary after shrinking.
        int rPrime = r - shrinkFromRight;

        // Print the resulting segment [l', r'] for day m.
        System.out.println(lPrime + " " + rPrime);
    }

    /**
     * Main method to handle multiple test cases.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            // Read the number of test cases.
            int t = sc.nextInt();
            // Loop through each test case.
            for (int i = 0; i < t; i++) {
                solve(sc);
            }
        } finally {
            // Close the scanner to prevent resource leaks.
            sc.close();
        }
    }
}