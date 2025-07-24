// A. Add or XOR
// time limit per test1 second
// memory limit per test256 megabytes
// r-906 & IA AI - Psychologic Disco
// You are given two non-negative integers a,b
// . You can apply two types of operations on a
//  any number of times and in any order:

// a←a+1
// . The cost of this operation is x
// ;
// a←a⊕1
// , where ⊕
//  denotes the bitwise XOR operation. The cost of this operation is y
// .
// Now you are asked to make a=b
// . If it's possible, output the minimum cost; otherwise, report it.

// Input
// Each test contains multiple test cases. The first line contains the number of test cases t
//  (1≤t≤104
// ). The description of the test cases follows.

// The only line of each test case contains four integers a,b,x,y
//  (1≤a,b≤100,1≤x,y≤107
// ) — the two integers given to you and the respective costs of two types of operations.

// Output
// For each test case, output an integer — the minimum cost to make a=b
// , or −1
//  if it is impossible.

// Example


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Solution for the "A. Add or XOR" problem.
 * This program finds the minimum cost to transform an integer 'a' into 'b'
 * using two operations: increment (cost x) and bitwise XOR with 1 (cost y).
 *
 * The problem can be modeled as finding the shortest path in a graph where
 * the nodes are integers and the edges represent the allowed operations.
 * Dijkstra's algorithm is a perfect fit for this, as it finds the shortest
 * path from a single source ('a') to all other nodes in a weighted graph with
 * non-negative edge weights.
 */
public class Main {

    static final long INF = Long.MAX_VALUE;
    // A safe upper bound for the numbers we might visit. Since a, b <= 100,
    // a path might go slightly above max(a, b). A bound of 400 is sufficient.
    static final int MAX_NODE = 400;

    /**
     * Main method to handle multiple test cases.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        FastReader sc = new FastReader();
        int t = sc.nextInt();
        while (t-- > 0) {
            solve(sc);
        }
    }

    /**
     * Solves a single test case using Dijkstra's algorithm.
     * @param sc FastReader instance for efficient input.
     */
    public static void solve(FastReader sc) {
        int a = sc.nextInt();
        int b = sc.nextInt();
        long x = sc.nextLong();
        long y = sc.nextLong();

        if (a == b) {
            System.out.println(0);
            return;
        }

        long[] dist = new long[MAX_NODE];
        Arrays.fill(dist, INF);

        // Priority Queue stores {cost, node} and is ordered by cost (min-heap).
        PriorityQueue<long[]> pq = new PriorityQueue<>((p1, p2) -> Long.compare(p1[0], p2[0]));

        dist[a] = 0;
        pq.add(new long[]{0, a});

        while (!pq.isEmpty()) {
            long[] current = pq.poll();
            long cost = current[0];
            int u = (int) current[1];

            // If we've found a shorter path to u since this entry was queued, skip it.
            if (cost > dist[u]) {
                continue;
            }

            // Optimization: if we extract the target, we've found the shortest path.
            if (u == b) {
                break;
            }

            // Explore neighbor 1: Operation a -> a + 1
            int v1 = u + 1;
            if (v1 < MAX_NODE) {
                if (dist[u] + x < dist[v1]) {
                    dist[v1] = dist[u] + x;
                    pq.add(new long[]{dist[v1], v1});
                }
            }

            // Explore neighbor 2: Operation a -> a ^ 1
            int v2 = u ^ 1;
            // The problem statement implies we work with positive integers.
            if (v2 > 0 && v2 < MAX_NODE) {
                if (dist[u] + y < dist[v2]) {
                    dist[v2] = dist[u] + y;
                    pq.add(new long[]{dist[v2], v2});
                }
            }
        }
        
        if (dist[b] == INF) {
            System.out.println(-1);
        } else {
            System.out.println(dist[b]);
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}```
