import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sh0nkMain implements Solvable {
    private class Edge {
        int u;
        int v;
        int cost;
    }

    @Override
    public int solve(int N, int M, int[][] R) {
        List<Edge> edges = new ArrayList<>();
        List<Integer> entrees = new ArrayList<>();

        // init params
        for (int[] r : R) {
            Edge e = new Edge();
            e.u = r[0];
            e.v = r[1] + N;
            e.cost = r[2];
            edges.add(e);
        }

        Collections.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.cost > o2.cost? -1: 1;
            }
        });

        int cost = 0;
        int men = 0;
        int women = 0;

        for (int i = 0; i < R.length; i++) {
            Edge e = edges.get(i);

            // 終了判定
            if (men == N && women == M) {
                break;
            }
            // 閉路判定
            if (entrees.contains(e.u) && entrees.contains(e.v)) {
                continue;
            }
            if (!entrees.contains(e.u)) {
                men++;
            }
            if (!entrees.contains(e.v)) {
                women++;
            }

            entrees.add(e.u);
            entrees.add(e.v);
            System.out.println(String.format("men %d women %d cost %d", e.u, e.v, e.cost));

            cost += e.cost;
        }

        return (N + M) * PAY - cost;
    }
}
