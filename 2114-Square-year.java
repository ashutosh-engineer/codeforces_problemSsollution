// A. Square Year
// time limit per test1 second
// memory limit per test256 megabytes
// One can notice the following remarkable mathematical fact: the number 2025
//  can be represented as (20+25)2
// .

// You are given a year represented by a string s
// , consisting of exactly 4
//  characters. Thus, leading zeros are allowed in the year representation. For example, "0001", "0185", "1375" are valid year representations. You need to express it in the form (a+b)2
// , where a
//  and b
//  are non-negative integers, or determine that it is impossible.

// For example, if s
//  = "0001", you can choose a=0
// , b=1
// , and write the year as (0+1)2=1
// .

// Input
// The first line of the input contains a single integer t
//  (1≤t≤104
// ) — the number of test cases.

// The following lines describe the test cases.

// The only line of each test case contains a string s
// , consisting of exactly 4
//  characters. Each character is a digit from 0
//  to 9
// .

// Output
// On a separate line for each test case, output:

// Two numbers a
//  and b
//  (a,b≥0
// ) such that (a+b)2=s
// , if they exist. If there are multiple suitable pairs, you may output any of them.
// The number −1
//  otherwise.
// Example
// InputCopy
// 5
// 0001
// 1001
// 1000
// 4900
// 2025
// OutputCopy
// 0 1
// -1
// -1
// 34 36
// 20 25




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Solution for the "A. Square Year" problem.
 * This program determines if a given 4-digit year can be expressed as (a+b)^2.
 *
 * Time Complexity: O(1) per test case.
 * Space Complexity: O(1) per test case.
 */
public class Main {

    public static void main(String[] args) {
        FastReader sc = new FastReader();
        int t = sc.nextInt();
        while (t-- > 0) {
            solve(sc);
        }
    }

    /**
     * Solves a single test case.
     * @param sc FastReader instance for input.
     */
    public static void solve(FastReader sc) {
        String s = sc.next();
        
        // 1. Convert the 4-digit string to an integer.
        int year = Integer.parseInt(s);
        
        // 2. Calculate the integer square root.
        int root = (int) Math.sqrt(year);
        
        // 3. Check if the year is a perfect square.
        //    If root * root is not equal to the original year, it's impossible.
        if (root * root != year) {
            System.out.println(-1);
        } else {
            // 4. If it is a perfect square, we need to find non-negative a and b
            //    such that a + b = root.
            //    The simplest valid pair is a = root and b = 0.
            //    Since year <= 9999, root <= 99, so a and b will be non-negative.
            int a = root;
            int b = 0;
            System.out.println(a + " " + b);
        }
    }

    /**
     * A utility class for faster input reading in competitive programming.
     */
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
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
}

