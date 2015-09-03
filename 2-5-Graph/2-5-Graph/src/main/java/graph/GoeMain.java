package graph;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class GoeMain implements Solvable {
  public static void main(String[] args) {
    System.out.println("Hello World!");
  }

  @Override
  public int solve(int N, int M, int[][] R) {
    int num = N + M;
    List<Edge> edges = new ArrayList<Edge>();
    for (int i =0; i < R.length; i++){
      // M should be after N
      edges.add(new Edge(R[i][0], N+R[i][1], -R[i][2]));
    }
    return kruskal(num, edges) + num * PAY;
  }

  private int kruskal(int num, List<Edge> edges) {
    UnionFind uf = new UnionFind(num);
    PriorityQueue<Edge> pQueue = new PriorityQueue<Edge>(edges);

    int totalCost = 0;
    while (!pQueue.isEmpty()) {
      Edge edge = pQueue.poll();
      if (!uf.isSame(edge.source, edge.target)) {
        totalCost += edge.cost;
        uf.unite(edge.source, edge.target);
      }
    }
    return totalCost;
  }


  private class Edge implements Comparable<Edge> {
    public int source = 0;
    public int target = 0;
    public int cost = 0;

    public Edge(int source, int target, int cost) {
      this.source = source;
      this.target = target;
      this.cost = cost;
    }

    @Override
    public int compareTo(Edge another) {
      return this.cost - another.cost;
    }
  }

  private class UnionFind {
    private int[] parent;

    UnionFind(int n) {
      parent = new int[n];
      for (int i = 0; i < n; i++)
        parent[i] = i;
    }

    // find root
    public int find(int x) {
      if (parent[x] == x)
        return x;
      return parent[x] = find(parent[x]);
    }

    public Boolean isSame(int x, int y) {
      return find(x) == find(y);
    }

    // find root and connect
    public void unite(int x, int y) {
      if (find(x) == find(y))
        return;
      // change parent root
      parent[find(x)] = find(y);
    }

  }
}
