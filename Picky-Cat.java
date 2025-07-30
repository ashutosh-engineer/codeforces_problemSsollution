import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /**
     * Main method to read the number of test cases and process each one.
     */
    public static void main(String[] args) throws IOException {
        // Use BufferedReader for fast input reading.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // Read the total number of test cases.
        int t = Integer.parseInt(br.readLine());
        
        // Loop through each test case.
        while (t-- > 0) {
            solve(br);
        }
    }

    /**
     * Solves a single test case.
     * @param br BufferedReader to read input for the test case.
     * @throws IOException If an input error occurs.
     */
    private static void solve(BufferedReader br) throws IOException {
        // Read the size of the array.
        int n = Integer.parseInt(br.readLine());
        
        // Use StringTokenizer to read the array elements efficiently.
        StringTokenizer st = new StringTokenizer(br.readLine());

        // Edge case: If there is only one element, it is always the median.
        if (n == 1) {
            System.out.println("YES");
            return;
        }

        // The absolute value of the first element, which is our target.
        long targetAbs = Math.abs(Long.parseLong(st.nextToken()));

        // Count how many of the other n-1 elements have an absolute value
        // smaller than our target's absolute value.
        int k_smaller = 0;
        for (int i = 1; i < n; i++) {
            long currentAbs = Math.abs(Long.parseLong(st.nextToken()));
            if (currentAbs < targetAbs) {
                k_smaller++;
            }
        }
        
        // The number of elements that must be strictly smaller than the median.
        // For an array of size n, the median is at position ceil(n/2).
        // L = ceil(n/2) - 1. In integer arithmetic, this is (n-1) / 2.
        int L = (n - 1) / 2;
        
        // The number of elements with an absolute value larger than our target.
        int k_larger = (n - 1) - k_smaller;

        // Possibility 1: Can we make 'targetAbs' the median?
        // This is possible if the number of unavoidably smaller elements (k_smaller)
        // is not more than the required number of smaller elements (L).
        boolean canBePositiveMedian = (k_smaller <= L);

        // Possibility 2: Can we make '-targetAbs' the median?
        // This is possible if we can source L smaller elements by negating them.
        // These must come from the k_larger pool, so we need at least L of them.
        boolean canBeNegativeMedian = (k_larger >= L);

        // If either possibility is true, it is possible.
        if (canBePositiveMedian || canBeNegativeMedian) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}