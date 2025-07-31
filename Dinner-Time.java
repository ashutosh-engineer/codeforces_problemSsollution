import java.util.*;

public class DinnerTime {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt(); // number of test cases

        while (t-- > 0) {
            int n = sc.nextInt(); // length of array
            int m = sc.nextInt(); // total sum of array
            int p = sc.nextInt(); // length of each segment
            int q = sc.nextInt(); // sum of each segment

            // The key observation:
            // Total number of p-length windows is (n - p + 1)
            // Each element appears in exactly "p" of these windows if:
            //     each element is in p windows => total sum = (n - p + 1) * q
            //
            // But each element appears in p windows only when p == 1 or n == p
            // So general condition becomes:
            //     m * (n - p + 1) == q * n

            if ((long) m * (n - p + 1) == (long) q * n) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
