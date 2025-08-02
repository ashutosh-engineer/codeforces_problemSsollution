import java.util.Scanner;

public class TreasureHunt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt(); // number of test cases

        while (t-- > 0) {
            int x = sc.nextInt(); // Little B digs x
            int y = sc.nextInt(); // Little K digs y
            int a = sc.nextInt(); // Treasure at a.5 meters

            double target = a + 0.5;
            double dug = 0;
            boolean bTurn = true;

            while (dug <= target) {
                if (bTurn) {
                    dug += x;
                    if (dug > target) {
                        System.out.println("NO"); // Little B digs it up
                        break;
                    }
                } else {
                    dug += y;
                    if (dug > target) {
                        System.out.println("YES"); // Little K digs it up
                        break;
                    }
                }
                bTurn = !bTurn;
            }
        }
    }
}
