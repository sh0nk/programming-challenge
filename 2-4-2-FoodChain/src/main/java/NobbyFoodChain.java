/**
 * Created by nobuhiro.onishi
 */
public class NobbyFoodChain implements Interface {
    private int N;
    private int K;
    private int[] Type;
    private int[] X;
    private int[] Y;
    private int Par[];
    private int Rank[];

    public int execute(int n, int k, int[] type, int[] x, int[] y) {
        N = n;
        K = k;
        Type = type;
        X = x;
        Y = y;
        Par = new int[n * 3];
        Rank = new int[n * 3];
        return solve();
    }

    private int solve() {
        init(N * 3);

        int ans = 0;
        for (int i = 0; i < K; i++) {
            int t = Type[i];
            int x = X[i] - 1;
            int y = Y[i] - 1;

            // 正しくない番号
            if (x < 0 || N <= x || y < 0 || N <= y) {
                ans++;
                continue;
            }

            if (t == 1) {
                // xとyは同じ種類　タイプ
                if (same(x, y + N) || same(x, y + 2 * N)) {
                    ans++;
                } else {
                    unite(x, y);
                    unite(x + N, y + N);
                    unite(x + N * 2, y + N * 2);
                }
            } else {
                // xはyを食べる　タイプ
                if (same(x, y) || same(x, y + 2 * N)) {
                    ans++;
                } else {
                    unite(x, y + N);
                    unite(x + N, y + 2 * N);
                    unite(x + 2 * N, y);
                }
            }
        }
        return ans;
    }

    // n要素で初期化
    private void init(int n) {
        for (int i = 0; i < n; i++) {
            Par[i] = i;
            Rank[i] = 0;
        }
    }

    // 木の根を求める
    private int find(int x) {
        if (Par[x] == x) {
            return x;
        } else {
            return Par[x] = find(Par[x]);
        }
    }

    // xとyの属する集合を併合
    private void unite(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) {
            return;
        }

        if (Rank[x] < Rank[y]) {
            Par[x] = y;
        } else {
            Par[y] = x;
            if (Rank[x] == Rank[y]) {
                Rank[x]++;
            }
        }
    }

    // xとyが同じ集合に属するか否か
    private boolean same(int x, int y) {
        return find(x) == find(y);
    }
}


