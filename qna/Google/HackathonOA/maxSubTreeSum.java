//https://docs.google.com/document/d/11_n4swLlRGHHgVzZV9BCgpjZ5mpY5BHLuS1gp7PTazk/edit?tab=t.0

import java.util.*;

public class maxSubTreeSum {
    static int maxSum = Integer.MIN_VALUE;
    public static void main(String[] args) {
        // int N = 5;
        // int[] values = {3, -2, 1, 4, 5};
        // int[][] edges = {{1, 2}, {1, 3}, {3, 4}, {3, 5}}; 

        int N = 5;
        int[] values = {5, 7, -10, 4, 15};
        int[][] edges = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};

        int maxSum = findMaxSubtreeSum(N, values, edges);
        System.out.println("Maximum Subtree Sum: " + maxSum);
    }
    public static int findMaxSubtreeSum(int n, int[] values, int[][] edges){
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i=0;i<=n;i++){
            adjList.add(new ArrayList<>());
        }
        for(int[] edge: edges){
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        int[] vis = new int[n+1];
        int[] res = new int[n+1];
        dfs(1, values, adjList, vis, res);
        return maxSum;
    }
    public static int dfs(int n, int[] values, List<List<Integer>> adjList, int[] vis, int[] res){
        vis[n] = 1;
        res[n] = values[n-1];
        for(int neighbor: adjList.get(n)){
            if(vis[neighbor] == 0){
                res[n] += dfs(neighbor, values, adjList, vis, res);
            }
        }
        maxSum = Math.max(maxSum, res[n]);
        return res[n];
    }
}
