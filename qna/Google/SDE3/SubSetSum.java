// https://docs.google.com/document/d/1EYXObTwQzzW_XxB5BTGiVcdKHFb2zyGTGTSAdAod8KE/edit?tab=t.0

import java.util.*;

public class SubSetSum {

    public static void main(String[] args) {
        int N = 5;
        int[] values = {5, 7, -10, 4, 15};
        int[][] edges = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};

        int maxSum = findMaxNonAdjSubSetSum(N, values, edges);
        System.out.println("Maximum Non-Adjacent SubSet Sum: " + maxSum);
    }
    public static int findMaxNonAdjSubSetSum(int n, int[] values, int[][] edges) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        int[] parent = new int[n + 1];
        int[][] dp = new int[n + 1][2];
        dfs(1, -1, values, adjList, parent, dp);
        return Math.max(dp[1][0], dp[1][1]);
    }
    public static void dfs(int node, int par, int[] values, List<List<Integer>> adjList, int[] parent, int[][] dp) {
        parent[node] = par;
        dp[node][1] = values[node - 1];
        for(int neighbor : adjList.get(node)) {
            if(neighbor != par) {
                dfs(neighbor, node, values, adjList, parent, dp);
                dp[node][0] += Math.max(dp[neighbor][0], dp[neighbor][1]);
                dp[node][1] += dp[neighbor][0];
            }
        }

    }

    
}