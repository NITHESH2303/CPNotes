//https://docs.google.com/document/d/1m1FyXGdP_y6perxSZ70HZAHIVVbMDNy_kmgVlnYUE7s/edit?tab=t.0
public class starlight {
    public static long maxSubarraySumWithMultiplication(int[] nums, int x) {
        int n = nums.length;
        long[] dpnot = new long[n];
        long[] dpmul = new long[n];
        long[] k = new long[n];

        k[0] = Math.max(0, nums[0]);
        dpnot[0] = Math.max(0, nums[0]);
        dpmul[0] = Math.max(0, nums[0]*x);

        for(int i=1;i<n;i++){
            k[i] = Math.max(0, Math.max(nums[i], nums[i]+k[i-1]));
            dpnot[i] = Math.max(0, Math.max(nums[i], Math.max(k[i], Math.max(nums[i]+dpnot[i-1], nums[i]+dpmul[i-1]))));
            dpmul[i] = Math.max(nums[i]*x, Math.max(k[i-1] + nums[i]*x, nums[i]*x+dpmul[i-1]));
        }
        long maxSum = 0;
        for (int i = 0; i < n; i++) {
            maxSum = Math.max(maxSum, Math.max(k[i], Math.max(dpmul[i], dpnot[i])));
        }
        return maxSum;
    }

    public static void main(String[] args) {
        // Test example
        int[] arr1 = {3, 2};
        int x1 = 2;
        System.out.println(maxSubarraySumWithMultiplication(arr1, x1)); // Output: 10

        // Additional test cases
        int[] arr2 = {1, 2, -3, 4, 5};
        int x2 = 3;
        System.out.println(maxSubarraySumWithMultiplication(arr2, x2)); // Output: 21

        int[] arr3 = {-1, -2, -3, -4};
        int x3 = 5;
        System.out.println(maxSubarraySumWithMultiplication(arr3, x3)); // Output: 0

        int[] arr4 = {1, 2, 3, 4};
        int x4 = 2;
        System.out.println(maxSubarraySumWithMultiplication(arr4, x4)); // Output: 20

        int[] arr5 = {3, -1, 4, -2, 5};
        int x5 = 10;
        System.out.println(maxSubarraySumWithMultiplication(arr5, x5)); // Output: 60

        int[] arr6 = {-1, 3, 4, 5, -10};
        int x6 = -1;
        System.out.println(maxSubarraySumWithMultiplication(arr6, x6)); // Output: 60
    }
}
