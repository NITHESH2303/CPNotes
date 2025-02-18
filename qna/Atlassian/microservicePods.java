//https://www.desiqna.in/17770/atlassian-oa-ctc-75-l-sep-21-kumar-k-q2
//https://docs.google.com/document/d/1rFC7PjgJqqh5DJ-gHVfkcZRTm7NhK7AVshCcGJozg9c/edit?tab=t.0

import java.util.*;

public class microservicePods {
    public static void main(String[] args) {
        int n = 13;
        int[] pods = {1, 1, 1, 1, 3, 3, 3, 4, 4, 4, 5, 9, 9};
        int[] cost = {1, 2, 3, 4, 1, 2, 3, 1, 2, 3, 1, 1, 2};
        // int n = 5;
        // int[] pods = {5, 2, 5, 3, 3};
        // int[] cost = {3, 7, 8, 6, 9};
        // int n = 6;
        // int[] pods = {1, 1, 1, 2, 2, 3};
        // int[] cost = {5, 3, 2, 8, 1, 4};
        // int n = 4;
        // int[] pods = {4, 4, 4, 4};
        // int[] cost = {1, 2, 3, 4};        

        System.out.println(minCostToMakeDistinctPods(n, pods, cost));
    }
    public static int minCostToMakeDistinctPods(int n, int[] pods, int[] cost){
        Pair[] nums = new Pair[n];
        Map<Integer, Integer> totalCost = new HashMap<>();
        Map<Integer, PriorityQueue<Integer>> maxCost = new HashMap<>();
        Map<Integer, Integer> freqMap = new TreeMap<>();
        int minCost = 0;
        for(int i=0;i<n;i++){
            nums[i] = new Pair(pods[i], cost[i]);
            System.out.println("pod: "+pods[i]+" cost: "+cost[i]);
            freqMap.put(pods[i], freqMap.getOrDefault(pods[i], 0) + 1);
            totalCost.put(pods[i], totalCost.getOrDefault(pods[i], 0) + cost[i]);
            maxCost.putIfAbsent(pods[i], new PriorityQueue<>(Collections.reverseOrder()));
            maxCost.get(pods[i]).add(cost[i]);
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.x));
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            pq.add(new Pair(entry.getKey(), entry.getValue()));
        }
        while (!pq.isEmpty()){
            Pair p = pq.poll();
            int pod = p.x;
            int c = p.y;
            System.out.println("pod: "+pod+" freq: "+c);
            if(freqMap.get(pod) == 1) continue;
            int curCost = totalCost.get(pod) - maxCost.get(pod).poll();
            System.out.println("curCost: "+curCost);
            minCost += curCost;
            System.out.println("minCost: "+minCost);
            if(curCost>0){
                maxCost.putIfAbsent(pod+1, new PriorityQueue<>(Collections.reverseOrder()));
                maxCost.get(pod+1).addAll(maxCost.get(pod));
                totalCost.put(pod+1, totalCost.getOrDefault(pod+1, 0) + curCost);
                freqMap.put(pod+1, freqMap.getOrDefault(pod+1, 0) + freqMap.get(pod) -1);
                if(!pq.isEmpty() && pq.peek().x == pod+1){
                    System.out.println("pq.peek().x: " + pq.peek().x + " pq.peek().y: "+pq.peek().y);
                    Pair p1 = pq.poll();
                    p1.y += freqMap.get(pod) - 1;
                    pq.add(p1);
                }else{
                    pq.add(new Pair(pod+1, freqMap.get(pod+1)));
                }
                freqMap.put(pod, 1);
            }
        }
        return minCost;
    }
}

class Pair implements Comparable<Pair>{
    int x;
    int y;
    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int compareTo(Pair p){
        return this.x - p.x;
    }
}