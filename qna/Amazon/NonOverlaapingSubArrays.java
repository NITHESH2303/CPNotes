

public class NonOverlaapingSubArrays {
    public static void main(String[] args) {
        // Scanner scanner = new Scanner(System.in);
        // int n = scanner.nextInt();
        // int[] nums = new int[n+1];
        // for (int i = 1; i <= n; i++) {
        //     nums[i] = scanner.nextInt();
        // }
        int[] nums = new int[]{-10, -5, 2, 4, -15, -20, 1, 2};
        System.out.println("Maximum sum of two non-overlapping subarrays: " + maxTwoNonOverlappingSubarraysSum(nums));
    }
    private static int maxTwoNonOverlappingSubarraysSum(int[] nums){
        int sum = 0;
        int n = nums.length;
        int[] prefixMaxSum = calculatePrefixMaxSum(nums, n);
        int[] suffixMaxSum = calculateSuffixMaxSum(nums, n);
        int[] maxPrefix = new int[n+1];
        int[] maxSuffix = new int[n+1];
        maxPrefix[0] = prefixMaxSum[0];
        for(int i=1;i<n;i++){
            maxPrefix[i] = Math.max(maxPrefix[i-1], prefixMaxSum[i]);
        }
        maxSuffix[n-1] = suffixMaxSum[n-1];
        for(int i=n-2;i>=0;i--){
            maxSuffix[i] = Math.max(maxSuffix[i+1], suffixMaxSum[i]);
        }
        for(int i=0;i<n-1;i++){
            sum = Math.max(sum, maxPrefix[i] + maxSuffix[i+1]);
        }
        return sum;
    }
    private static int[] calculatePrefixMaxSum(int[] nums, int n) {
        int[] prefixSum = new int[n];
        prefixSum[0] = nums[0];
        int max = nums[0];
        for(int i=1;i<nums.length;i++){
            max = Math.max(0,Math.max(max + nums[i], nums[i]));
            prefixSum[i] = max;
        }
        return prefixSum;
    }
    private static int[] calculateSuffixMaxSum(int[] nums, int n){
        int[] suffixSum = new int[n];
        suffixSum[n-1] = nums[n-1];
        int max = nums[n-1];
        for(int i=n-2;i>=0;i--){
            max = Math.max(0, Math.max(max + nums[i], nums[i]));
            suffixSum[i] = max;
        }
        return suffixSum;
    }
}
