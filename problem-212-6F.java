// Given a tree∗
//  with n
//  vertices numbered from 1
//  to n
// . Each vertex has an initial color ai
// .

// Each edge of the tree is defined by three numbers: ui
// , vi
// , and ci
// , where ui
//  and vi
//  are the endpoints of the edge, and ci
//  is the edge parameter. The cost of the edge is defined as follows: if the colors of vertices ui
//  and vi
//  are the same, the cost is 0
// ; otherwise, the cost is ci
// .

// You are also given q
//  queries. Each query has the form: repaint vertex v
//  to color x
// . The queries depend on each other (after each query, the color change is preserved). After each query, you need to output the sum of the costs of all edges in the tree.

// ∗
// A tree is a connected graph without cycles.

// Input
// The first line contains an integer t
//  (1≤t≤104
// ) — the number of test cases.

// The first line of each test case contains two integers n
//  and q
//  (1≤n,q≤2⋅105
// ) — the number of vertices and the number of queries, respectively.

// The second line contains n
//  integers a1,a2,…,an
//  (1≤ai≤n
// ), where the i
// -th number specifies the initial color of vertex i
// .

// The next n−1
//  lines describe the edges of the tree. Each line contains three integers u
// , v
// , and c
// , denoting an edge between vertices u
//  and v
//  with parameter c
//  (1≤u,v≤n
// , 1≤c≤109
// ).

// The following q
//  lines contain the queries. Each query contains two integers v
//  and x
//  — repaint vertex v
//  to color x
//  (1≤v,x≤n
// ).

// It is guaranteed that the sum of n
//  and the sum of q
//  across all test cases do not exceed 2⋅105
// .

import java.io.*;
import java.util.*;

public class FreeTreeF {
    static int n, q;
    static int[] col, parent;
    static long totalCost;
    static long[] edgeWeight;
    static List<int[]>[] adj;
    static Map<Integer, Long>[] subCost;

    // DFS to establish parent/edgeWeight/subCost and initial totalCost
    static void dfs(int u, int p) {
        parent[u] = p;
        for (int[] e : adj[u]) {
            int v = e[0], w = e[1];
            if (v == p) continue;
            if (col[u] != col[v]) totalCost += w;
            edgeWeight[v] = w;
            dfs(v, u);
            subCost[u].merge(col[v], w, Long::sum);
        }
    }

    // Repaint vertex v to new color x, adjust totalCost accordingly
    static void repaint(int v, int x) {
        int old = col[v];
        int p = parent[v];
        
        // Update edge to parent
        if (p != 0) {
            subCost[p].merge(old, -edgeWeight[v], Long::sum);
            if (col[p] == old) totalCost += edgeWeight[v];
            subCost[p].merge(x, edgeWeight[v], Long::sum);
            if (col[p] == x) totalCost -= edgeWeight[v];
        }

        // Update edges to children
        long oldSum = subCost[v].getOrDefault(old, 0L);
        long newSum = subCost[v].getOrDefault(x, 0L);
        totalCost += oldSum - newSum;

        // Finally, repaint
        col[v] = x;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());

            col = new int[n + 1];
            parent = new int[n + 1];
            edgeWeight = new long[n + 1];
            adj = new ArrayList[n + 1];
            subCost = new HashMap[n + 1];
            for (int i = 1; i <= n; i++) {
                adj[i] = new ArrayList<>();
                subCost[i] = new HashMap<>();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++)
                col[i] = Integer.parseInt(st.nextToken());

            for (int i = 0; i < n - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                adj[u].add(new int[]{v, c});
                adj[v].add(new int[]{u, c});
            }

            totalCost = 0;
            dfs(1, 0);

            for (int i = 0; i < q; i++) {
                st = new StringTokenizer(br.readLine());
                int v = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                repaint(v, x);
                out.append(totalCost).append('\n');
            }
        }
        System.out.print(out);
    }
}
