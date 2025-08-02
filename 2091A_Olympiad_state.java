import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Solves the problem of finding the minimum number of drawn digits
 * to form the date "01.03.2025".
 */
public class DateFormation {

    /**
     * The main method reads the number of test cases and processes each one.
     *
     * @param args Command line arguments (not used).
     * @throws IOException If an I/O error occurs.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        // Read the total number of test cases.
        int testCases = Integer.parseInt(reader.readLine().trim());

        // Process each test case.
        for (int t = 0; t < testCases; t++) {
            solve(reader);
        }
    }

    /**
     * Solves a single test case.
     * It reads 'n' and the sequence of digits, then calculates and prints
     * the minimum index at which the digits for "01032025" are all available.
     *
     * @param reader The BufferedReader to read input from.
     * @throws IOException If an I/O error occurs.
     */
    public static void solve(BufferedReader reader) throws IOException {
        // Read the number of digits for the current test case.
        int n = Integer.parseInt(reader.readLine().trim());

        // Read the sequence of digits using StringTokenizer for efficiency.
        StringTokenizer st = new StringTokenizer(reader.readLine());

        // The target date "01.03.2025" requires the digits from "01032025".
        // We use an array to store the counts of required digits.
        // Index of the array corresponds to the digit.
        // requiredCounts[0] = 3 (for three '0's)
        // requiredCounts[1] = 1 (for one '1')
        // requiredCounts[2] = 2 (for two '2's)
        // etc.
        int[] requiredCounts = new int[10];
        requiredCounts[0] = 3;
        requiredCounts[1] = 1;
        requiredCounts[2] = 2;
        requiredCounts[3] = 1;
        requiredCounts[5] = 1;
        
        // This counter tracks the total number of required digits remaining.
        // It starts at 8 (3+1+2+1+1).
        int totalDigitsNeeded = 8;

        // This will store the 1-based index when the date can be formed.
        // It is initialized to 0, which is the output for impossible cases.
        int resultIndex = 0;

        // Iterate through the drawn digits one by one.
        for (int i = 1; i <= n; i++) {
            int drawnDigit = Integer.parseInt(st.nextToken());
            
            // If the drawn digit is one we still need, decrement its required count
            // and the total count of digits needed.
            if (requiredCounts[drawnDigit] > 0) {
                requiredCounts[drawnDigit]--;
                totalDigitsNeeded--;
            }
            
            // If we have just found all required digits and the result has not been set yet,
            // record the current 1-based index. We don't break the loop, to ensure all
            // input for the current test case is consumed.
            if (totalDigitsNeeded == 0 && resultIndex == 0) {
                resultIndex = i;
            }
        }

        // Print the result for the current test case.
        System.out.println(resultIndex);
    }
}