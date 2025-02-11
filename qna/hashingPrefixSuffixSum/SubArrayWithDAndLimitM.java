import java.util.*;

public class SubArrayWithDAndLimitM {
    public static void main(String[] args) {
        // Sample input
        int[] nums = {2, 5, 2, 5, 2, 5, 1, 1};
        int D = 5;
        int M = 2;

        int result = countSubarraysWithD(nums, D, M);
        System.out.println("Number of subarrays: " + result);
    }

    public static int countSubarraysWithD(int[] nums, int D, int M) {
        int n = nums.length;
        int[] prefix = new int[n + 1];
        Map<Integer, Integer> prefixCount = new HashMap<>();

        prefix[0] = 0;
        int count = 0;

        // Precompute the prefix array
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + (nums[i - 1] == D ? 1 : 0);
        }

        System.out.println(Arrays.toString(prefix));


        for (int j = 0; j <= n; j++) {
            int target = prefix[j] - M;
            if (prefixCount.containsKey(target)) {
                count += prefixCount.get(target);
            }
            prefixCount.put(prefix[j], prefixCount.getOrDefault(prefix[j], 0) + 1);
        }

        return count;
    }

    // public static int countSubarraysWithD(int[] nums, int D, int M) {
    //     int n = nums.length;
    //     int[] p = new int[n + 1];

    //     // Calculate the prefix array
    //     for (int i = 1; i <= n; i++) {
    //         if (nums[i - 1] == D) {
    //             p[i] = 1;
    //         } else {
    //             p[i] = 0;
    //         }
    //     }

    //     // Accumulate the prefix array
    //     for (int i = 1; i <= n; i++) {
    //         p[i] += p[i - 1];
    //     }

    //     // Count valid subarrays
    //     int t = 0;
    //     for (int j = 1; j <= n; j++) {
    //         for (int i = j - 1; i >= 0; i--) {
    //             if (p[i] == p[j] - M) {
    //                 // [i+1....j] is a valid subarray
    //                 t++;
    //             }
    //         }
    //     }

    //     // Print the prefix array for debugging
    //     System.out.println("Prefix array: " + Arrays.toString(p));

    //     return t;
    // }
}
