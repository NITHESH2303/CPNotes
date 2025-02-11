import java.util.*;

public class Frog2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read the number of stones
        int N = scanner.nextInt();

        int k = scanner.nextInt();
        
        // Read the heights of the stones
        int[] heights = new int[N];
        for (int i = 0; i < N; i++) {
            heights[i] = scanner.nextInt();
        }
        
        // Call the method to calculate the minimum cost
        int result = minCost(N, k, heights);
        
        // Print the result
        System.out.println(result);
    }
    
    // Method to calculate the minimum cost
    public static int minCost(int n, int k, int[] nums) {
        // Your logic here
        int[] dp = new int[n];
        dp[0] = 0;
        dp[1] = Math.abs(nums[1] - nums[0]);
        for(int i=2;i<n;i++){
            int j=1;
            int minCost = Integer.MAX_VALUE;
            while(j<=k && i-j>=0){
                minCost = Math.min(dp[i-j] + Math.abs(nums[i]-nums[i-j]), minCost);
                j++;
            }
            dp[i] = minCost;
        }
        return dp[n-1]; // Placeholder return value
    }
}
