import java.util.ArrayDeque;
import java.util.Deque;

public class NoripiJackStraw implements JackStrawInterface {

  @Override
  public String[] solve(int n, int[][] p, int[][] q, int m, int[][] ab) {
    // y = ax + bの形式にする
    double[][] coeff = new double[p.length][2];
    for (int i = 0; i < p.length; i++) {
      double[] co = this.getCoefficients(p[i], q[i]);
      coeff[i] = co;
    }

    // それぞれの交点を求める
    boolean[][] hasDirectConnections = new boolean[p.length][q.length];
    for (int i = 0; i < p.length; i++) {
      for (int j = 0; j < q.length; j++) {
        // 同じindexは交わってるとみなす
        if (i == j) {
          hasDirectConnections[i][j] = true;
          continue;
        }

        // 同じ係数は交わってるとみなす
        if (coeff[i][0] == coeff[j][0] && coeff[i][1] == coeff[j][1]) {
          hasDirectConnections[i][j] = true;
          continue;
        }

        // xの係数が同じで定数項が違えば平行
        if (coeff[i][0] == coeff[j][0] && coeff[i][1] != coeff[j][1]) {
          hasDirectConnections[i][j] = false;
          continue;
        }

        // 交点のx座標を求める
        double crossX = (coeff[j][1] - coeff[i][1]) / (coeff[i][0] - coeff[j][0]);
        hasDirectConnections[i][j] =
            (crossX >= Math.min(p[i][0], q[i][0]) && crossX <= Math.max(p[i][0], q[i][0])
                && crossX >= Math.min(p[j][0], q[j][0]) && crossX <= Math.max(p[j][0], q[j][0]));
      }
    }

    // 間接コネクションがあるかどうかを調べる
    boolean[][] hasIndirectConnections = new boolean[p.length][q.length];
    for (int i = 0; i < p.length; i++) {
      hasIndirectConnections[i] = this.getIndirectConnection(i, hasDirectConnections);
    }

    String[] result = new String[ab.length];
    for (int i = 0; i < ab.length; i++) {
      result[i] =
          hasIndirectConnections[ab[i][0] - 1][ab[i][1] - 1] ? "CONNECTED" : "NOT CONNECTED";
    }

    return result;
  }

  private boolean[] getIndirectConnection(int idx, boolean[][] hasDirectConnections) {
    Deque<Integer> deque = new ArrayDeque<>();
    for (int i = 0; i < hasDirectConnections[idx].length; i++) {
      if (hasDirectConnections[idx][i]) {
        deque.add(i);
      }
    }

    boolean[] result = new boolean[hasDirectConnections[idx].length];
    while (!deque.isEmpty()) {
      int num = deque.pop();

      // 既に処理済みのindex
      if (result[num]) {
        continue;
      }

      // そのindexが持ってるDirectConnectionをdequeに追加
      for (int i = 0; i < hasDirectConnections[num].length; i++) {
        if (hasDirectConnections[num][i]) {
          deque.add(i);
        }
      }
      result[num] = true;
    }

    return result;
  }

  private double[] getCoefficients(int[] p, int[] q) {
    double a = (q[1] - p[1] * 1.0) / (q[0] - p[0]);
    double b = p[1] - a * p[0];

    return new double[] {a, b};
  }

}
