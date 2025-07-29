import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            int numTestCases = scanner.nextInt();
            while (numTestCases-- > 0) {
                solve(scanner);
            }
        } finally {
            scanner.close();
        }
    }

    private static void solve(Scanner scanner) {
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        if (n <= 1) {
            System.out.println(n);
            return;
        }

        // 1. Generate all unique products from pairs (a[i], a[j])
        Set<Long> productsToTest = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                productsToTest.add((long) a[i] * a[j]);
            }
        }

        int maxSize = 0;
        // A single element is always a beautiful subsequence.
        if (n > 0) {
            maxSize = 1;
        }

        // 2. For each potential product, find the max subsequence size
        for (long product : productsToTest) {
            Set<Long> usedY = new HashSet<>();
            int currentCount = 0;

            // 3. Count how many a[k] can form a valid pair for this product
            for (int k = 0; k < n; k++) {
                // An element cannot be 0 based on constraints, but this is safe
                if (a[k] == 0) continue;

                if (product % a[k] == 0) {
                    long y = product / a[k];
                    // Check if this y value is unique for the current product
                    if (!usedY.contains(y)) {
                        usedY.add(y);
                        currentCount++;
                    }
                }
            }
            // 4. Update the overall maximum size
            if (currentCount > maxSize) {
                maxSize = currentCount;
            }
        }

        System.out.println(maxSize);
    }
}