//https://docs.google.com/document/d/155E_2S7FabNMwPyQT4qcPJYug8gLOJsu37UsUNMrOCE/edit?tab=t.0

package Uber;

import java.util.*;

public class TaroVacation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] happiness = new int[n][3];
        for (int i = 0; i < n; i++) {
            happiness[i][0] = scanner.nextInt(); // a_i
            happiness[i][1] = scanner.nextInt(); // b_i
            happiness[i][2] = scanner.nextInt(); // c_i
        }
        scanner.close();

        // Call the method to calculate the maximum happiness
        int maxHappiness = calculateMaxHappiness(n, happiness);
        System.out.println("Maximum possible total points of happiness: " + maxHappiness);
    }

    // Method to calculate the maximum happiness
    public static int calculateMaxHappiness(int n, int[][] nums) {
        // Write your logic here
        int[][] dp = new int[n][3];
        int maxi = Integer.MIN_VALUE;
        for (int j = 0; j < 3; j++) {
          dp[0][j] = nums[0][j]; 
          maxi = Math.max(maxi, dp[0][j]);
        }
        dp[1][0] = nums[1][0] + maxi;
        dp[1][1] = nums[1][1] + maxi;
        dp[1][2] = nums[1][2] + maxi;
        for(int i = 2; i< n;i++){
          dp[i][0] = Math.max(nums[i][0] + Math.max(dp[i-1][1], dp[i-1][2]), nums[i][0] + nums[i-1][0] + Math.max(dp[i-2][1], dp[i-2][2]));
          dp[i][1] = Math.max(nums[i][1] + Math.max(dp[i-1][0], dp[i-1][2]), nums[i][1] + nums[i-1][1] + Math.max(dp[i-2][0], dp[i-2][2]));
          dp[i][2] = Math.max(nums[i][2] + Math.max(dp[i-1][0], dp[i-1][1]), nums[i][2] + nums[i-1][2] + Math.max(dp[i-2][0], dp[i-2][1]));
        }
        return Math.max(dp[n-1][0], Math.max(dp[n-1][1], dp[n-1][2])); // Placeholder return value
    }
}
