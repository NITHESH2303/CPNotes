//https://www.desiqna.in/dp3
import java.util.*;

public class dp3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array1 = new int[n];
        int[] array2 = new int[n];
        for (int i = 0; i < n; i++) {
            array1[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            array2[i] = scanner.nextInt();
        }
        scanner.close();

        // Call the method to calculate the maximum sum subset
        int maxSum = calculateMaxSumSubset(n, array1, array2);
        System.out.println("Maximum sum of subset: " + maxSum);
    }

    // Method to calculate the maximum sum subset
    public static int calculateMaxSumSubset(int n, int[] nums1, int[] nums2) {
        // Write your logic here
        int[] dp = new int[n];
        dp[0] = Math.max(Math.max(nums1[0], nums2[0]), 0);
        dp[1] = Math.max(Math.max(nums1[1], nums2[1]), dp[0]);
        for(int i=2;i<n;i++){
            dp[i] = Math.max(dp[i-1], Math.max(nums1[i] + dp[i-2], nums2[i] + dp[i-2]));
            System.out.println(dp[i]);
        }
        return dp[n-1]; // Placeholder return value
    }
}
