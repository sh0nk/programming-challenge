import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PopularCowsExample implements PopularCowsInterface {
    private static Logger LOGGER = LogManager.getLogger(PopularCowsExample.class);
    private int V; // 頂点数
    private List<List<Integer>> G; // グラフの隣接リスト表現
    private List<List<Integer>> rG; // 辺の向きを逆にしたグラフ
    private List<Integer> vs; // 帰りがけ順の並び
    private boolean used[]; // すでに調べたか
    private int cmp[]; // 属する強連結成分のトポロジカル順序

    @Override
    public int solve(int N, int M, int[]A, int[] B) {
        LOGGER.info("{} is called. ", this.getClass());
        V = N;
        G = new ArrayList<>();
        rG = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            G.add(new ArrayList<>());
            rG.add(new ArrayList<>());
        }
        used = new boolean[N];
        cmp = new int[N];


        for (int i = 0; i < M; i++) {
            addEdge(A[i] - 1, B[i] -1);
        }
        int n = scc();

        // 候補となる点の数を調べる
        int u = 0;
        int num = 0;
        for (int v = 0; v < V; v++) {
            if (cmp[v] == n - 1) {
                u = v;
                num++;
            }
        }

        // すべての点から到達可能か調べる
        Arrays.fill(used, false);
        rdfs(u, 0);
        for (int v = 0; v < V; v++) {
            if (!used[v]) {
                // この点から到達不能
                num = 0;
                break;
            }
        }

        return num;
    }

    private void addEdge(int from, int to) {
        G.get(from).add(to);
        rG.get(to).add(from);
    }

    private void dfs(int v) {
        used[v] = true;
        for (int i = 0; i < G.get(v).size(); i++) {
            if (!used[G.get(v).get(i)]) {
                dfs(G.get(v).get(i));
            }
        }
        vs.add(v);
    }

    private void rdfs(int v, int k) {
        used[v] = true;
        cmp[v] = k;
        for (int i = 0; i < rG.get(v).size(); i++) {
            if (!used[rG.get(v).get(i)]) {
                rdfs(rG.get(v).get(i), k);
            }
        }
    }

    private int scc() {
        Arrays.fill(used, false);
        vs = new ArrayList<Integer>();
        for (int v = 0; v < V; v++) {
            if (!used[v]) {
                dfs(v);
            }
        }
        Arrays.fill(used, false);
        int k = 0;
        for (int i = vs.size() - 1; i >= 0; i--) {
            if (!used[vs.get(i)]) {
                rdfs(vs.get(i), k++);
            }
        }
        return k;
    }
}
