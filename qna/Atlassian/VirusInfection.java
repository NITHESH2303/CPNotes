//https://docs.google.com/document/d/1WwoegxRueHsX_SBUSRRpSAq1uEQERJ120pIAHOptBsA/edit?tab=t.0

import java.util.*;

public class VirusInfection {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        
        int[] nodeValues = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            nodeValues[i] = scanner.nextInt();
        }

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int G = scanner.nextInt();
        int K = scanner.nextInt();

        List<Integer> infectedNodes = new ArrayList<>();
        for (int i = 0; i < G; i++) {
            infectedNodes.add(scanner.nextInt());
        }

        scanner.close();

        makeUniversalNode(graph, infectedNodes);
        spreadInfections(graph, nodeValues, N, K+1);
        bfs(graph, nodeValues, 1, N);
    }

    public static void makeUniversalNode(List<List<Integer>> graph, List<Integer> infectedNodes) {
        for (int infectedNode : infectedNodes) {
            graph.get(0).add(infectedNode);
            graph.get(infectedNode).add(0);
        }
    }

    public static void spreadInfections(List<List<Integer>> graph, int[] nodes, int n, int k) {
        Queue<Integer> q = new LinkedList<>();
        int[] lvl = new int[n + 1];
        Arrays.fill(lvl, Integer.MAX_VALUE);

        q.add(0);
        lvl[0] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int node : graph.get(cur)) {
                if (lvl[cur] < k && lvl[node] == Integer.MAX_VALUE) {
                    lvl[node] = lvl[cur] + 1;
                    nodes[node] = 0;
                    q.add(node);
                }
            }
        }
    }

    private static void bfs(List<List<Integer>> graph, int[] nodes, int start, int n) {
        Queue<Integer> queue = new LinkedList<>();
        int[] lvl = new int[n + 1];
        Arrays.fill(lvl, Integer.MAX_VALUE);

        if (nodes[start] == 1) {
            queue.offer(start);
            lvl[start] = 0;
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int node : graph.get(cur)) {
                if (nodes[node] == 1 && lvl[node] == Integer.MAX_VALUE) {
                    queue.offer(node);
                    lvl[node] = lvl[cur] + 1;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(lvl[i] + " ");
        }
    }
}
