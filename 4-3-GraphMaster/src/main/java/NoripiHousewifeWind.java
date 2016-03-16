import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoripiHousewifeWind implements HousewifeWindInterface {


  @Override
  public String solve(int n, int q, int s, List<int[]> abw, List<List> query) {
    List<List<Integer>> tree = this.topologicalSort(n, abw);

    // 計算量を減らせそうなので各ノードのレベル番号を保持しておく
    Map<Integer, Integer> levelMap = new HashMap<>();
    for (int i = 0; i < tree.size(); i++) {
      for (int node : tree.get(i)) {
        levelMap.put(node, i);
      }
    }

    // 各ルートのindex番号
    Map<String, Integer> routeMap = new HashMap<>();
    // 親リスト
    Map<Integer, Integer> parentMap = new HashMap<>();
    for (int i = 0; i < abw.size(); i++) {
      int[] values = abw.get(i);
      int from = values[0] - 1;
      int to = values[1] - 1;

      String route = from + "-" + to;
      routeMap.put(route, i);

      parentMap.put(to, from);
    }

    // クエリ実行
    String result = "";
    int currentPosition = s - 1;
    for (List eachQuery : query) {
      String type = (String) eachQuery.get(0);
      switch (type) {
        case "A": {
          int idx = (int) eachQuery.get(1) - 1;
          int cost = this.getCost(currentPosition, idx, levelMap, routeMap, parentMap, abw);
          result += cost + "\n";
          currentPosition = idx;
          break;
        }
        case "B": {
          int idx = (int) eachQuery.get(1) - 1;
          int cost = (int) eachQuery.get(2);
          abw.get(idx)[2] = cost;
          break;
        }
      }

    }

    return result;
  }

  private int getCost(int start, int goal, Map<Integer, Integer> levelMap,
      Map<String, Integer> routeMap,
      Map<Integer, Integer> parentMap, List<int[]> abw) {

    int startLevel = levelMap.get(start);
    int goalLevel = levelMap.get(goal);

    int cost = 0;

    // まずは同じレベルまで降下する
    while (goalLevel > startLevel) {
      int nextGoal = parentMap.get(goal);
      cost += abw.get(routeMap.get(nextGoal + "-" + goal))[2];
      goal = nextGoal;
      goalLevel--;
    }
    while (startLevel > goalLevel) {
      int nextStart = parentMap.get(start);
      cost += abw.get(routeMap.get(nextStart + "-" + start))[2];
      start = nextStart;
      startLevel--;
    }

    // 同じレベルまで来たら、両方が同じになるまで下る
    while (start != goal) {
      int nextStart = parentMap.get(start);
      int nextGoal = parentMap.get(goal);

      cost += abw.get(routeMap.get(nextStart + "-" + start))[2];
      cost += abw.get(routeMap.get(nextGoal + "-" + goal))[2];

      start = nextStart;
      goal = nextGoal;
    }

    return cost;
  }

  private List<List<Integer>> topologicalSort(int n, List<int[]> abw) {
    // まず各ノードの出力先をリストにする
    Map<Integer, List<Integer>> vertexMap = new HashMap<>();
    for (int i = 0; i < abw.size(); i++) {
      int from = abw.get(i)[0] - 1;
      int to = abw.get(i)[1] - 1;

      if (!vertexMap.containsKey(from)) {
        vertexMap.put(from, new ArrayList<>());
      }
      vertexMap.get(from).add(to);
    }

    // 根 (=親がいない) を探す
    int[] hasParent = new int[n];
    for (int from : vertexMap.keySet()) {
      for (int to : vertexMap.get(from)) {
        hasParent[to] = 1;
      }
    }

    // indexを取得
    int rootIndex = -1;
    for (int i = 0; i < hasParent.length; i++) {
      if (hasParent[i] == 0) {
        rootIndex = i;
        break;
      }
    }

    List<List<Integer>> tree = new ArrayList<>();

    // 根ノード
    List<Integer> root = new ArrayList<>();
    root.add(rootIndex);
    tree.add(root);
    while (true) {
      List<Integer> currentLeafs = tree.get(tree.size() - 1);
      if (currentLeafs.size() == 0) {
        break;
      }

      List<Integer> childNode = new ArrayList<>();
      for (int i = 0; i < currentLeafs.size(); i++) {
        int from = currentLeafs.get(i);
        List<Integer> to = vertexMap.containsKey(from) ? vertexMap.get(from) : new ArrayList<>();
        childNode.addAll(to);
      }
      tree.add(childNode);
    }

    return tree;
  }

}
