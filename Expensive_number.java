import java.util.Scanner;

/**
 * Solves the "B. Expensive Number" problem.
 *
 * The logic is as follows:
 * 1. The minimum possible cost for any number X is 1 (since Value(X) >= SumOfDigits(X)).
 * 2. This minimum cost is achieved only if the resulting number has a value of a single
 *    digit (e.g., a subsequence like '7' or '007').
 * 3. To minimize removals, we need to find the LONGEST subsequence that has a cost of 1.
 * 4. This means finding the longest subsequence of the form "0...0d" where d is a non-zero digit.
 * 5. We can iterate through the input string, and for each non-zero digit, calculate the
 *    length of the subsequence we could form with it and all preceding zeros. The maximum
 *    of these lengths is our goal.
 */
public class ExpensiveNumber {

    /**
     * Main method to handle multiple test cases.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int t = scanner.nextInt();
            for (int i = 0; i < t; i++) {
                solve(scanner);
            }
        }
    }

    /**
     * Solves a single test case.
     * @param scanner The Scanner instance to read input.
     */
    private static void solve(Scanner scanner) {
        String n = scanner.next();
        int totalLength = n.length();
        
        int maxKeptLength = 0;
        int zeroCount = 0;

        // Iterate through each digit of the number string.
        for (int i = 0; i < totalLength; i++) {
            char currentChar = n.charAt(i);

            if (currentChar == '0') {
                zeroCount++;
            } else {
                // We found a non-zero digit. We can form a subsequence with
                // this digit and all the zeros we have seen so far.
                // The length of this subsequence is zeroCount + 1.
                int currentKeptLength = zeroCount + 1;
                
                // We want to find the maximum possible length we can keep.
                if (currentKeptLength > maxKeptLength) {
                    maxKeptLength = currentKeptLength;
                }
            }
        }
        
        // If the number consists only of zeros, the result must be 0 removals
        // as we can't form a positive integer. But the constraint n >= 1
        // ensures there's at least one non-zero digit.
        if (maxKeptLength == 0 && totalLength > 0) {
             // This case happens if there's only one non-zero digit at the start,
             // or if n is just one digit.
             maxKeptLength = 1;
        }

        // The number of digits to remove is the total length minus the
        // maximum number of digits we can keep.
        System.out.println(totalLength - maxKeptLength);
    }
}