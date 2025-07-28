import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Solves the derangement subsequence problem.
 */
public class DerangementSubsequenceSolution {

    /**
     * Solves a single test case.
     *
     * @param sc The Scanner for reading input.
     * @param pw The PrintWriter for writing output.
     */
    private static void solve(Scanner sc, PrintWriter pw) {
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] sortedA = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            sortedA[i] = a[i];
        }

        // 1. Sort a copy of the original array.
        Arrays.sort(sortedA);

        // 2. Build the candidate subsequence 'b' from "unstable" elements.
        // These are elements where a[i] is not in its sorted position.
        List<Integer> b = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (a[i] != sortedA[i]) {
                b.add(a[i]);
            }
        }

        // 3. If 'b' is empty, 'a' was already sorted. No derangement is possible.
        if (b.isEmpty()) {
            pw.println("NO");
            return;
        }

        // 4. Verify if our candidate 'b' is a derangement.
        // Create a sorted version of 'b'.
        List<Integer> sortedB = new ArrayList<>(b);
        Collections.sort(sortedB);

        boolean isDerangement = true;
        for (int i = 0; i < b.size(); i++) {
            // A fixed point is found if b[i] == sorted_b[i].
            if (b.get(i).equals(sortedB.get(i))) {
                isDerangement = false;
                break;
            }
        }

        // 5. Output the result based on the verification.
        if (isDerangement) {
            pw.println("YES");
            pw.println(b.size());
            for (int i = 0; i < b.size(); i++) {
                pw.print(b.get(i) + (i == b.size() - 1 ? "" : " "));
            }
            pw.println();
        } else {
            pw.println("NO");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}