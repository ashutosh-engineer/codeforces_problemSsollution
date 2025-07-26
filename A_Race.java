import java.util.*;

public class A_Race {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt(); // Number of test cases
        while (t-- > 0) {
            int a = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();

            boolean possible = false;
            for (int b = 1; b <= 100; b++) {
                if (b == a) continue;
                if (Math.abs(b - x) < Math.abs(a - x) && Math.abs(b - y) < Math.abs(a - y)) {
                    possible = true;
                    break;
                }
            }

            System.out.println(possible ? "YES" : "NO");
        }
    }
}
