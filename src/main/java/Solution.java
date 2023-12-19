import java.util.*;

public class Solution {
    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        List<List<Edge>> adjList = new ArrayList<>();
        List<List<Edge>> reversedAdjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
            reversedAdjList.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            int weight = edges[i][2];
            adjList.get(from).add(new Edge(to, weight));
            reversedAdjList.get(to).add(new Edge(from, weight));
        }

        long[] fromSource1ToX = dijkstra(adjList, src1, new long[n]);
        long[] fromSource2ToX = dijkstra(adjList, src2, new long[n]);
        long[] fromXToDest = dijkstra(reversedAdjList, dest, new long[n]);

        long result = Long.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if(fromSource1ToX[i] != Long.MAX_VALUE && fromSource2ToX[i] != Long.MAX_VALUE && fromXToDest[i] != Long.MAX_VALUE){
                result = Math.min(result, fromSource1ToX[i] + fromSource2ToX[i] + fromXToDest[i]);
            }
        }


        return result == Long.MAX_VALUE ? -1 : result;
    }
    private long[] dijkstra(List<List<Edge>> adjList, int src, long[] costsFromStart){
        Arrays.fill(costsFromStart, Long.MAX_VALUE);
        costsFromStart[src] = 0;

        PriorityQueue<Path> pq = new PriorityQueue<>(Comparator.comparingLong(path -> path.totalCost));
        pq.offer(new Path(src, 0));

        while (!pq.isEmpty()){
            Path current = pq.poll();
            int curNode = current.lastNode;
            long curCost = current.totalCost;

            if(costsFromStart[curNode] < curCost){
                continue;
            }
            for (Edge edge : adjList.get(curNode)){
                if(curCost + edge.cost < costsFromStart[edge.to]){
                    costsFromStart[edge.to] = curCost + edge.cost;
                    pq.add(new Path(edge.to, curCost + edge.cost));
                }
            }

        }

        return costsFromStart;
    }

}
class Edge{
    int to;
    int cost;

    public Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}
class Path{
    int lastNode;
    long totalCost;

    public Path(int prevNode, long totalCost) {
        this.lastNode = prevNode;
        this.totalCost = totalCost;
    }
}
