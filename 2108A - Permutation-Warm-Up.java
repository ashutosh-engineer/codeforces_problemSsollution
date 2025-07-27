import java.util.Scanner;

/**
 * Solves the problem of finding the number of distinct values for the function
 * f(p) = sum(|p[i] - i|) over all permutations p of length n.
 *
 * The solution is based on the mathematical properties of f(p):
 * 1. f(p) is always an even number.
 * 2. The minimum value is 0 and the maximum is floor(n^2 / 2).
 * 3. f(p) can take any even value between the minimum and maximum.
 *
 * This reduces the problem to calculating (max_f / 2) + 1.
 */
public class Main {

    /**
     * Main method to handle test cases.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            solve(scanner);
        }
        scanner.close();
    }

    /**
     * Solves a single test case.
     *
     * @param scanner The Scanner to read input from.
     */
    private static void solve(Scanner scanner) {
        long n = scanner.nextLong();

        // The maximum value of f(p) is floor(n^2 / 2).
        // Using integer arithmetic, this is simply (n * n) / 2.
        long max_f = (n * n) / 2;

        // The number of distinct values is the count of even numbers
        // from 0 to max_f, which is (max_f / 2) + 1.
        long distinctValuesCount = max_f / 2 + 1;

        System.out.println(distinctValuesCount);
    }
}