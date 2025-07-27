import java.util.Scanner;

/**
 * Solves Codeforces Problem 1025A - It's Time To Duel.
 *
 * The solution determines if a given set of player win/loss reports is
 * definitely contradictory.
 */
public class Main {

    /**
     * The main entry point of the program. It handles multiple test cases.
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
     * Solves a single test case for the problem.
     *
     * @param scanner The Scanner instance to read input from.
     */
    private static void solve(Scanner scanner) {
        int n = scanner.nextInt();
        int[] a = new int[n];
        int onesCount = 0;

        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            if (a[i] == 1) {
                onesCount++;
            }
        }

        // Condition 1: More players claim a win than total duels available.
        // There are n-1 duels, so n-1 total wins are possible.
        if (onesCount > n - 1) {
            System.out.println("YES");
            return;
        }

        // Condition 2: Two adjacent players both claim to have won 0 duels.
        // This implies a contradiction in the duel between them: each must
        // lose to the other, which is impossible.
        boolean adjacentZeros = false;
        for (int i = 0; i < n - 1; i++) {
            if (a[i] == 0 && a[i + 1] == 0) {
                adjacentZeros = true;
                break;
            }
        }

        if (adjacentZeros) {
            System.out.println("YES");
            return;
        }

        // If neither contradiction is found, a valid scenario is possible.
        System.out.println("NO");
    }
}