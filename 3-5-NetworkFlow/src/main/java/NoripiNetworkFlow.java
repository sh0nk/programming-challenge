import java.util.ArrayList;
import java.util.List;

public class NoripiNetworkFlow implements NetworkFlow {
  @Override
  public int getMaxFow(int S, int T, int[][] G) {
    // 線形計画法を使う
    // 目的関数: z = Σx_ij (i=S, j=0..len(G)-1)

    // 変数群
    List<String> variables = new ArrayList<>();
    List<String> slackVariables = new ArrayList<>();

    // 変数の定義
    variables.add("z");
    for (int i = 0; i < G.length; i++) {
      for (int j = 0; j < G[i].length; j++) {
        // つながってない
        if (G[i][j] == 0) {
          continue;
        }

        String variable = "x" + i + "_" + j;
        String slackVariable = "s" + i + "_" + j;

        variables.add(variable);
        slackVariables.add(slackVariable);
      }
    }
    variables.addAll(slackVariables);

    List<int[]> equations = new ArrayList<>();

    // 各辺の最大通信量制約
    for (int i = 0; i < G.length; i++) {
      for (int j = 0; j < G[i].length; j++) {
        // つながってない
        if (G[i][j] == 0) {
          continue;
        }

        String variable = "x" + i + "_" + j;
        String slackVariable = "s" + i + "_" + j;

        int[] equation = new int[variables.size() + 1];
        equation[variables.indexOf(variable)] = 1;
        equation[variables.indexOf(slackVariable)] = 1;
        equation[variables.size()] = G[i][j];

        equations.add(equation);
      }
    }

    // 目的関数
    int[] objectEquation = new int[variables.size() + 1];
    for (int i = 0; i < G[S].length; i++) {
      // つながってない
      if (G[S][i] == 0) {
        continue;
      }

      String variable = "x" + S + "_" + i;
      objectEquation[variables.indexOf(variable)] = -1;
    }
    objectEquation[0] = 1;
    equations.add(objectEquation);

    // 流入量 = 流出量
    int[][] flowEquations = new int[G.length][variables.size() + 1];
    for (int i = 0; i < G.length; i++) {
      for (int j = 0; j < G[i].length; j++) {
        // つながってない
        if (G[i][j] == 0) {
          continue;
        }

        String variable = "x" + i + "_" + j;

        flowEquations[i][variables.indexOf(variable)] = 1;
        flowEquations[j][variables.indexOf(variable)] = -1;
      }
    }
    for (int i = 0; i < flowEquations.length; i++) {
      if (i == S || i == T) {
        continue;
      }

      // 最初に1 or -1が出現するindexを求める
      int firstIndex = -1;
      for (int j = 0; j < flowEquations[i].length; j++) {
        if (flowEquations[i][j] != 0) {
          firstIndex = j;
          break;
        }
      }

      for (int j = 0; j < equations.size(); j++) {
        int[] equation = equations.get(j);
        if (equation[firstIndex] == 0) {
          continue;
        }

        int div = equation[firstIndex] / flowEquations[i][firstIndex];
        // 加算して変数消去
        for (int k = 0; k < equation.length; k++) {
          equation[k] -= div * flowEquations[i][k];
        }

        equations.set(j, equation);
      }
    }

    int[][] result = simplex(equations.toArray(new int[0][0]));

    return result[result.length - 1][result[0].length - 1];
  }

  private int[][] simplex(int[][] equations) {
    // 最後の行を目的関数行とする
    int[] lastEquation = equations[equations.length - 1];

    // ピボット列を決定（負の数になってる一番最初の列）
    int pivotCol = -1;
    for (int i = 0; i < lastEquation.length; i++) {
      if (lastEquation[i] < 0) {
        pivotCol = i;
        break;
      }
    }

    // 既に最適なのでそのまま返す
    if (pivotCol == -1) {
      return equations;
    }

    // ピボット行を決定（右辺/係数が0以上かつ最小のところ）
    int pivotRow = -1;
    int currentValue = Integer.MAX_VALUE;
    for (int i = 0; i < equations.length - 1; i++) {
      if (equations[i][pivotCol] == 0) {
        continue;
      }

      int nextValue = equations[i][equations[i].length - 1] / equations[i][pivotCol];
      if (nextValue > 0 && nextValue < currentValue) {
        currentValue = nextValue;
        pivotRow = i;
      }
    }

    // 掃き出し法を実行
    for (int i = 0; i < equations.length; i++) {
      // 0なら何もしない
      if (equations[i][pivotCol] == 0) {
        continue;
      }

      // ピボット行なら何もしない
      if (i == pivotRow) {
        continue;
      }

      // 0以外なら消去する
      int div = equations[i][pivotCol] / equations[pivotRow][pivotCol];
      for (int j = 0; j < equations[i].length; j++) {
        equations[i][j] -= div * equations[pivotRow][j];
      }
    }

    return simplex(equations);
  }

}
