import java.util.*;

public class A_Gellyfish_and_Tricolor_Pansy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            long a = sc.nextLong(); // Gellyfish HP
            long b = sc.nextLong(); // Flower HP
            long c = sc.nextLong(); // Gellyfish's knight HP
            long d = sc.nextLong(); // Flower's knight HP

            if (c >= b || d >= a) {
                System.out.println("Gellyfish");
            } else if (d >= c && a <= d - c) {
                System.out.println("Flower");
            } else {
                System.out.println("Gellyfish");
            }
        }
        sc.close();
    }
}
