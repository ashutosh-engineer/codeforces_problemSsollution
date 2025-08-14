import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.HashSet;
import java.util.Set;

public class MixMexMax {

    // Function to calculate MEX of a 3-element array
    private static int mex(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int x : arr) {
            set.add(x);
        }
        for (int i = 0; ; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
    }

    // Function to check if a triplet can satisfy the condition
    private static boolean canBeGood(int[] triplet) {
        int unknownCount = 0;
        for (int x : triplet) {
            if (x == -1) {
                unknownCount++;
            }
        }

        if (unknownCount == 0) {
            int maxVal = Arrays.stream(triplet).max().getAsInt();
            int minVal = Arrays.stream(triplet).min().getAsInt();
            return mex(triplet) == maxVal - minVal;
        }

        // Iterate through all possible values for the unknowns
        int[] currentTriplet = new int[3];
        for (int i = 0; i <= 101; i++) {
            if (unknownCount > 1) {
                for (int j = 0; j <= 101; j++) {
                    if (unknownCount > 2) {
                        for (int k = 0; k <= 101; k++) {
                            int unknownIndex = 0;
                            for(int l=0; l<3; l++){
                                if(triplet[l] == -1){
                                    if(unknownIndex == 0) currentTriplet[l] = i;
                                    else if(unknownIndex == 1) currentTriplet[l] = j;
                                    else currentTriplet[l] = k;
                                    unknownIndex++;
                                } else {
                                    currentTriplet[l] = triplet[l];
                                }
                            }
                            if (mex(currentTriplet) == Arrays.stream(currentTriplet).max().getAsInt() - Arrays.stream(currentTriplet).min().getAsInt()) {
                                return true;
                            }
                        }
                    } else {
                        int unknownIndex = 0;
                        for(int l=0; l<3; l++){
                            if(triplet[l] == -1){
                                if(unknownIndex == 0) currentTriplet[l] = i;
                                else currentTriplet[l] = j;
                                unknownIndex++;
                            } else {
                                currentTriplet[l] = triplet[l];
                            }
                        }
                        if (mex(currentTriplet) == Arrays.stream(currentTriplet).max().getAsInt() - Arrays.stream(currentTriplet).min().getAsInt()) {
                            return true;
                        }
                    }
                }
            } else {
                 int unknownIndex = 0;
                 for(int l=0; l<3; l++){
                    if(triplet[l] == -1){
                        currentTriplet[l] = i;
                    } else {
                        currentTriplet[l] = triplet[l];
                    }
                }
                if (mex(currentTriplet) == Arrays.stream(currentTriplet).max().getAsInt() - Arrays.stream(currentTriplet).min().getAsInt()) {
                    return true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            boolean possible = true;
            for (int i = 0; i <= n - 3; i++) {
                int[] triplet = {a[i], a[i + 1], a[i + 2]};
                if (!canBeGood(triplet)) {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}