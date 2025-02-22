// https://docs.google.com/document/d/1tEbY2U6-csqe17-S4dpBAegeEvSoy8CWXI5_tCNTI-Y/edit?tab=t.0

import java.util.*;

public class MinimumBuses {
    public static void main(String[] args) {
        int N = 4;
        int[] passengers = {1, 1, 1, 1};
        int[][] edges = {{1, 2}, {2, 3}, {3, 4}};
        // int N = 3;
        // int[] passengers = {1, 1, 1};
        // int[][] edges = {{1, 2}, {1, 3}};
        int res = minBuses(N, passengers, edges);
        System.out.println("Minimum Buses: " + res);
    }
    public static int minBuses(int n, int[] passengers, int[][] edges) {
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i=0;i<=n;i++){
            adjList.add(new ArrayList<>());
        }
        for(int[] edge: edges){
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        int[] parent = new int[n+1];
        return dfs(1, -1, passengers, adjList, parent);
    }

    public static int dfs(int node, int par, int[] passengers, List<List<Integer>> adjList, int[] parent){
        parent[node] = par;
        int busCount = 0;
        boolean isLeaf = true;
        for(int neighbor : adjList.get(node)){
            if(neighbor != par){
                isLeaf = false;
                busCount += dfs(neighbor, node, passengers, adjList, parent);
            }
        }
        if(isLeaf && passengers[node-1]!=0){
            busCount = passengers[node-1];
        }
        return busCount;
    }
}
