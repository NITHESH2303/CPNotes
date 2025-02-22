// https://docs.google.com/document/d/1S8zLM0nvHY3OrIUwTjJAkg6misgu7CjIbIqYPMtdbnY/edit?tab=t.0
import java.util.*;

public class OnesInTree {
    public static void main(String[] args) {
        // int N = 5;
        // int[] values = {0, 1, 0, 1, 0};
        // int[][] edges = {{1, 2}, {1, 3}, {3, 4}, {3, 5}};

        int N = 5;
        int[] values = {1, 0, 0, 1, 1, 1};
        int[][] edges = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};

        int[] res = countOnesOnPath(N, values, edges);
        System.out.println(Arrays.toString(res));
    }

    public static int[] countOnesOnPath(int n, int[] values, int[][] edges) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        System.out.println(Arrays.toString(adjList.toArray()));
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        int[] res = new int[n];

        queue.add(1);
        visited[1] = true;
        res[0] = values[0];

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            for (int neighbor : adjList.get(currentNode)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    res[neighbor - 1] = res[currentNode - 1] + values[neighbor - 1];
                    queue.add(neighbor);
                }
            }
        }

        return res;
    }
}
