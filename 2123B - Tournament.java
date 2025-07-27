import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) {
        FastReader sc = new FastReader();
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            solve(sc, sb);
        }
        System.out.print(sb.toString());
    }

    private static void solve(FastReader sc, StringBuilder sb) {
        int n = sc.nextInt();
        int j = sc.nextInt(); // 1-based index of our player
        int k = sc.nextInt(); // Number of survivors

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        if (k == n) {
            sb.append("YES\n");
            return;
        }

        int playerStrength = a[j - 1];

        List<Integer> others = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i != j - 1) {
                others.add(a[i]);
            }
        }

        Collections.sort(others, Collections.reverseOrder());

        int maxStrengthInG;
        if (k == 1) {
            maxStrengthInG = playerStrength;
        } else {
            maxStrengthInG = Math.max(playerStrength, others.get(0));
        }

        // Safeguard against IndexOutOfBoundsException
        if (k > others.size()) {
            sb.append("YES\n");
            return;
        }

        int maxStrengthInE = others.get(k - 1);

        if (maxStrengthInG >= maxStrengthInE) {
            sb.append("YES\n");
        } else {
            sb.append("NO\n");
        }
    }
}
