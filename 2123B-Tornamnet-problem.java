// B. Tournament
// time limit per test2 seconds
// memory limit per test256 megabytes
// You are given an array of integers a1,a2,…,an
// . A tournament is held with n
//  players. Player i
//  has strength ai
// .

// While more than k
//  players remain,

// Two remaining players are chosen at random;
// Then the chosen player with the lower strength is eliminated. If the chosen players have the same strength, one is eliminated at random.
// Given integers j
//  and k
//  (1≤j,k≤n
// ), determine if there is any way for player j
//  to be one of the last k
//  remaining players.

// Input
// The first line contains an integer t
//  (1≤t≤104
// )  — the number of test cases.

// The first line of each test case contains three integers n
// , j
// , and k
//  (2≤n≤2⋅105
// , 1≤j,k≤n
// ).

// The second line of each test case contains n
//  integers, a1,a2,…,an
//  (1≤ai≤n
// ).

// It is guaranteed that the sum of n
//  over all test cases does not exceed 2⋅105
// .


import java.util.*;

public class Tournament {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();  // Number of test cases

        while (t-- > 0) {
            int n = sc.nextInt();  // Number of players
            int j = sc.nextInt();  // Player index (1-based)
            int k = sc.nextInt();  // Required survivors

            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }

            int strengthJ = a[j - 1];  // Player j's strength
            int stronger = 0;

            // Count how many are strictly stronger than player j
            for (int i = 0; i < n; i++) {
                if (a[i] > strengthJ) stronger++;
            }

            System.out.println(stronger <= k - 1 ? "YES" : "NO");
        }

        sc.close();
    }
}
