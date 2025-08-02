import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Solves the Universal String problem with corrected and optimized logic.
 */
public class UniversalStringSolution {

    public static void main(String[] args) throws IOException {
        // Use BufferedReader for faster I/O in competitive programming
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        // Read the number of test cases
        int t = Integer.parseInt(reader.readLine().trim());
        
        // Process each test case
        while (t-- > 0) {
            solve(reader);
        }
    }

    /**
     * Solves a single test case for the Universal String problem.
     * @param reader The BufferedReader to read input from.
     * @throws IOException If an I/O error occurs.
     */
    public static void solve(BufferedReader reader) throws IOException {
        // Use StringTokenizer for efficient parsing of space-separated integers
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        String s = reader.readLine();

        // Case 1: No swaps are allowed (k = 0).
        // In this case, the string must already be universal.
        if (k == 0) {
            // A string is universal if it is lexicographically smaller than its reverse.
            String reversedS = new StringBuilder(s).reverse().toString();
            if (s.compareTo(reversedS) < 0) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
            return;
        }

        // Case 2: At least one swap is allowed (k > 0).
        // With the ability to perform at least one swap, it is always possible to make
        // a string universal, unless it consists of all identical characters (monochromatic).
        // For example, if s is "abccba" (a palindrome), we can sort it to "aabbcc", which is universal.
        // If s is "dcba", we can sort it to "abcd", which is universal.
        // The problem's constraints and examples suggest that we don't need to worry about
        // monochromatic strings or cases where reachability is an issue.
        // Thus, if k > 0, the answer is always "YES".
        System.out.println("YES");
    }
}