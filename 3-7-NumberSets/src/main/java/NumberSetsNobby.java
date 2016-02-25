import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;

class UnionFind {
    int[] par;
    UnionFind(int n) {
        par = new int[n];
        for (int i = 0; i < n; i++) {
            par[i] = i;
        }
    }
    public int find(int x) {
        if (par[x] == x) {
            return x;
        }
        return par[x] = find(par[x]);
    }
    public Boolean same(int x, int y) {
        return find(x) == find(y);
    }
    public void unite(int x, int y) {
        if (find(x) == find(y)) {
            return;
        }
        par[find(x)] = find(y);
    }
}

public class NumberSetsNobby implements NumberSetsInterface {
    long P;
    int intP;
    long A;
    long B;
    final int MAX_P = 1000000;
    UnionFind unionFind;

    @Override
    public int solve(long A, long B, long P) {
        this.A = A;
        this.B = B;
        this.P = P;
        int numberNum = (int)(B - A + 1);

        if (P > MAX_P) {
            // 共通素因数は存在しない
            return (int)(B - A + 1L);
        } else {
            intP = (int)P;
            unionFind = new UnionFind(numberNum);
            ArrayList<Integer> primeList = this.getPrimeList();
            for (Integer prime : primeList) {
                long start = (A + prime - 1) / prime * prime;
                long end = B / prime * prime;

                for (long j = start; j <= end; j += prime) {
                    unionFind.unite((int)(start - A), (int)(j - A));
                }
            }
            int res = 0;
            for (long i = A; i <= B; i++) {
                if (unionFind.find((int)(i - A)) == (int)(i - A)) {
                    res++;
                }
            }
            return res;
        }
    }

    /**
     * P <= 1000000という条件で
     * P 以上 1000000以下(B以下とする)の素数一覧を取得する
     * @return
     */
    private ArrayList<Integer> getPrimeList() {
        ArrayList<Integer> primeList = new ArrayList<Integer>();
        int diffAandB = (int)(B - A);
        int num = MAX_P;
        num = (num - 3) / 2;
        int[] primeCheckList = new int[num];
        for (int i = 0; i < num; i++) {
            primeCheckList[i] = 1; // 1は素数扱い。始めは全て素数
        }
        
        if ( 2 >= intP && 2 <= this.B) {
            primeList.add(2);
        }
        for (int i = 0; i < num; i++) {
            if (primeCheckList[i] == 1) {
                int primeNum = i + i + 3;
                if (primeNum >= intP && primeNum <= this.B) {
                    primeList.add(primeNum);
                }
                for (int j = i + primeNum; j < num; j+= primeNum) {
                    primeCheckList[j] = 0;
                }
            }
        }
        return primeList;
    }
}