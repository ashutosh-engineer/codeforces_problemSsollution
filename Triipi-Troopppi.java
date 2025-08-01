import java.util.Scanner;

/**
 * Solves the "Trippi Troppi" problem from Codeforces Round 1017 (Div. 4).
 * This solution is written for Java 21.
 */
public class TrippiTroppi {

    /**
     * The main method that serves as the entry point for the program.
     * It handles the overall flow, including reading the number of test cases
     * and looping through them.
     *
     * @param args Command line arguments (not used in this solution).
     */
    public static void main(String[] args) {
        // Use a try-with-resources statement to ensure the Scanner is automatically
        // closed after use, preventing resource leaks.
        try (Scanner scanner = new Scanner(System.in)) {
            
            // 1. Read the total number of test cases.
            int t = scanner.nextInt();
            
            // 2. Loop 't' times to process each test case.
            for (int i = 0; i < t; i++) {
                solve(scanner);
            }
        }
    }

    /**
     * Solves a single test case.
     * This method reads the three words, processes them, and prints the result.
     *
     * @param scanner The Scanner instance to read input from.
     */
    private static void solve(Scanner scanner) {
        // 3. Read the three space-separated words for the current test case.
        // The scanner.next() method conveniently reads the next token (word).
        String word1 = scanner.next();
        String word2 = scanner.next();
        String word3 = scanner.next();

        // 4. Extract the first character from each word.
        // String.charAt(0) gets the character at the first position (index 0).
        // Since the input is guaranteed to be lowercase, no conversion is needed.
        char firstLetter1 = word1.charAt(0);
        char firstLetter2 = word2.charAt(0);
        char firstLetter3 = word3.charAt(0);

        // 5. Concatenate the three characters to form the modern name and print it.
        // Starting the concatenation with an empty string "" implicitly converts
        // the following characters to a String.
        System.out.println("" + firstLetter1 + firstLetter2 + firstLetter3);
    }
}