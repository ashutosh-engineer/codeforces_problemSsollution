import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int k = sc.nextInt();

            StringBuilder sb = new StringBuilder();
            int ones = 0, zeros = 0;

            for (int i = 0; i < n; i++) {
                if ((i % 2 == 0 && ones < k) || zeros >= n - k) {
                    sb.append('1');
                    ones++;
                } else {
                    sb.append('0');
                    zeros++;
                }
            }

            System.out.println(sb);
        }
    }
}
