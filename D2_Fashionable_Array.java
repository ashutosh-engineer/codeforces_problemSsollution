public class FashionableArrayOptimized {
    public static void main(String[] args) throws java.io.IOException {
        java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) solve(br);
    }
    private static void solve(java.io.BufferedReader br) throws java.io.IOException {
        int n = Integer.parseInt(br.readLine());
        java.util.StringTokenizer st = new java.util.StringTokenizer(br.readLine());
        int[] a = new int[n];
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int even = 0, odd = 0;
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            min = Math.min(min, a[i]);
            max = Math.max(max, a[i]);
            if (a[i] % 2 == 0) even++;
            else odd++;
        }
        if ((min + max) % 2 == 0)
            System.out.println(0);
        else
            System.out.println(Math.min(even, odd));
    }
}
