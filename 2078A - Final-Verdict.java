import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FinalVerdict {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            solve(br);
        }
    }

    private static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        long sumOfElements = 0;
        for (int i = 0; i < n; i++) {
            sumOfElements += Integer.parseInt(st.nextToken());
        }

        // The average of the array is an invariant.
        // The final array is [x], so its average is x.
        // Therefore, the initial average must also be x.
        // sumOfElements / n = x  =>  sumOfElements = n * x
        if (sumOfElements == (long) n * x) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}