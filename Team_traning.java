import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Solves the Strong Teams problem using a greedy approach.
 * The solution runs in O(N log N) time, dominated by the sorting step.
 */
public class StrongTeams {

    public static void main(String[] args) throws IOException {
        // Using BufferedReader for faster I/O, which can be helpful in competitive programming.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());
        
        while (t-- > 0) {
            solve(reader);
        }
    }

    private static void solve(BufferedReader reader) throws IOException {
        // Read n and x
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        long x = Long.parseLong(st.nextToken()); // x can be large

        // Read the skills array
        int[] skills = new int[n];
        st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            skills[i] = Integer.parseInt(st.nextToken());
        }

        // 1. Sort the skills array in ascending order.
        // This is the most crucial part of the greedy strategy.
        Arrays.sort(skills);

        int strongTeamsCount = 0;
        int currentTeamSize = 0;

        // 2. Iterate from the most skilled student (end of the sorted array) downwards.
        for (int i = n - 1; i >= 0; i--) {
            // Add the current student to the group we are forming.
            currentTeamSize++;

            // The minimum skill in this potential team is skills[i],
            // because everyone we've added so far (in this group) is more skilled.
            
            // 3. Check if the current group forms a strong team.
            // We must use 'long' for the multiplication to prevent integer overflow,
            // as currentTeamSize * skills[i] can exceed Integer.MAX_VALUE.
            if ((long) currentTeamSize * skills[i] >= x) {
                // If it's strong, count it and reset for the next team.
                strongTeamsCount++;
                currentTeamSize = 0;
            }
        }

        System.out.println(strongTeamsCount);
    }
}