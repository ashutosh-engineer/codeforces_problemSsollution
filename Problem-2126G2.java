// This is the hard version of the problem. The difference between the versions is that in this version ai≤n
// .

// You are given an array of n
//  integers a1,a2,…,an
// .

// Your task is to find a subarray a[l,r]
//  (a continuous sequence of elements al,al+1,…,ar
// ) for which the value of the expression med(a[l,r])−min(a[l,r])
//  is maximized.

// Here:

// med
//  is the median of the subarray, that is, the element at position ⌈k+12⌉
//  after sorting the subarray, where k
//  is its length;
// min
//  is the minimum element of this subarray.
// For example, consider the array a=[1,4,1,5,3,3]
//  and choose the subarray a[2,5]=[4,1,5,3]
// . In sorted form, it looks like [1,3,4,5]
// .

// med(a[2,5])=4
// , since ⌈4+12⌉=
//  the third element in the sorted subarray is 4
// ;
// min(a[2,5])=1
// , since the minimum element is 1
// .
// In this example, the value med−min=4−1=3
// .

// Input
// The first line contains an integer t
//  (1≤t≤104
// ) — the number of test cases.

// The first line of each test case contains one integer n
//  (1≤n≤2⋅105
// ) — the length of the array.

// The second line of each test case contains n
//  integers a1,a2,…,an
//  (1≤ai≤n
// ) — the elements of the array.

// It is guaranteed that the sum of n
//  across all test cases does not exceed 2⋅105
// .


import java.io.*;
import java.util.*;

public class BigWinsHard {
    static int N;
    static int[] a;
    // Segment-tree node stores {best, pref, suff, sum}
    static int[][] seg;
    
    static int[] L, R;
    static List<Integer>[] idx;

    static int[] combine(int[] A, int[] B) {
        int best = Math.max(A[0], B[0]);
        int pref = Math.max(A[1], A[3] + B[1]);
        int suff = Math.max(B[2], A[2] + B[3]);
        best = Math.max(best, Math.max(pref, suff));
        int sum = A[3] + B[3];
        return new int[]{best, pref, suff, sum};
    }

    static void update(int v, int tl, int tr, int pos, int val) {
        if (tl == tr) {
            int x = Math.max(val, 0);
            seg[v] = new int[]{x, x, x, val};
        } else {
            int tm = (tl + tr) >> 1;
            if (pos <= tm) update(v*2, tl, tm, pos, val);
            else update(v*2+1, tm+1, tr, pos, val);
            seg[v] = combine(seg[v*2], seg[v*2+1]);
        }
    }

    static int[] query(int v, int tl, int tr, int l, int r) {
        if (l > r) return new int[]{0,0,0,0};
        if (l == tl && r == tr) return seg[v];
        int tm = (tl + tr) >> 1;
        if (r <= tm) return query(v*2, tl, tm, l, r);
        if (l > tm) return query(v*2+1, tm+1, tr, l, r);
        return combine(
            query(v*2, tl, tm, l, tm),
            query(v*2+1, tm+1, tr, tm+1, r)
        );
    }

    static int solveCase(int n, int[] arr) {
        N = n;
        a = arr;
        seg = new int[4 * N][4];
        L = new int[N];
        R = new int[N];

        int maxVal = 0;
        for (int x : a) maxVal = Math.max(maxVal, x);

        idx = new ArrayList[maxVal+1];
        for (int i = 0; i <= maxVal; i++) idx[i] = new ArrayList<>();
        for (int i = 0; i < N; i++) idx[a[i]].add(i);

        // Build L using monotonic stack
        Deque<Integer> st = new ArrayDeque<>();
        st.push(-1);
        for (int i = 0; i < N; i++) {
            while (st.peek() >= 0 && a[st.peek()] >= a[i]) st.pop();
            L[i] = st.peek() + 1;
            st.push(i);
        }

        // Build R
        st.clear();
        st.push(N);
        for (int i = N - 1; i >= 0; i--) {
            while (st.peek() < N && a[st.peek()] >= a[i]) st.pop();
            R[i] = st.peek() - 1;
            st.push(i);
        }

        // Initial segment tree: all +1
        for (int i = 0; i < N; i++)
            update(1, 0, N-1, i, 1);
        // Remove positions where value == 1
        for (int p : idx[1])
            update(1, 0, N-1, p, -1);

        int ans = 0;
        int med = 1;

        for (int mn = 1; mn <= maxVal; mn++) {
            for (int p : idx[mn]) {
                int l = L[p], r = R[p];
                while (med < maxVal) {
                    int[] left = (p > l) ? query(1, 0, N-1, l, p-1) : new int[]{0,0,0,0};
                    int[] right = (p < r) ? query(1, 0, N-1, p+1, r) : new int[]{0,0,0,0};
                    int balance = left[2] + right[1] + (a[p] < med ? -1 : 1);
                    if (balance >= 0) {
                        med++;
                        for (int pp : idx[med])
                            update(1, 0, N-1, pp, -1);
                    } else break;
                }
            }
            ans = Math.max(ans, med - mn);
        }

        return ans;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            int[] arr = Arrays.stream(br.readLine().split(" "))
                              .mapToInt(Integer::parseInt).toArray();
            System.out.println(solveCase(n, arr));
        }
    }
}
