package graph;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class NoripiMain implements Solvable {

  @Override
  public int solve(int N, int M, int[][] R) {
    int[][] graph = new int[N + M][N + M];

    // initialize
    for (int i = 0; i < graph.length; i++) {
      for (int j = 0; j < graph[i].length; j++) {
        if (i == j) {
          graph[i][j] = Integer.MAX_VALUE;
        } else {
          graph[i][j] = Solvable.PAY;
        }
      }
    }

    // set relationship value
    for (int i = 0; i < R.length; i++) {
      int nIndex = R[i][0];
      int mIndex = R[i][1];
      int relation = R[i][2];

      graph[nIndex][N + mIndex] = Integer.min(Solvable.PAY - relation, graph[nIndex][N + mIndex]);
      graph[N + mIndex][nIndex] = Integer.min(Solvable.PAY - relation, graph[N + mIndex][nIndex]);
    }

    // sort with index
    List<Entry<String, Integer>> entrySet = new ArrayList<>();
    for (int i = 0; i < graph.length; i++) {
      for (int j = 0; j < graph[i].length; j++) {
        entrySet.add(new AbstractMap.SimpleEntry<String, Integer>(i + "," + j, graph[i][j]));
      }
    }
    Collections.sort(entrySet, new Comparator<Entry<String, Integer>>() {
      @Override
      public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
        return o1.getValue() - o2.getValue();
      }
    });

    // start search
    List<List<Integer>> existingTreeList = new ArrayList<>();
    int cost = 0;
    for (Entry<String, Integer> entry : entrySet) {
      // if count is enough, return
      int allCount = existingTreeList.stream().map((List<Integer> existingTree) -> {
        return existingTree.size();
      }).reduce(0, (Integer carry, Integer size) -> {
        return carry + size;
      });
      if (allCount == N + M) {
        break;
      }

      Integer[] newEdges = Arrays.stream(entry.getKey().split(",")).map((String s) -> {
        return Integer.parseInt(s);
      }).collect(Collectors.toList()).toArray(new Integer[0]);

      // find tree that has new edge
      boolean closingPath = false;
      List<Integer> existenceList = new ArrayList<>();
      int existingTreeIndex = -1;

      for (int i = 0; i < existingTreeList.size(); i++) {
        List<Integer> existingTree = existingTreeList.get(i);

        // same tree
        if (existingTree.contains(newEdges[0]) && existingTree.contains(newEdges[1])) {
          closingPath = true;
          break;
        }

        // existence check
        if (existingTree.contains(newEdges[0])) {
          existingTreeIndex = i;
          existenceList.add(newEdges[0]);
        }
        if (existingTree.contains(newEdges[1])) {
          existingTreeIndex = i;
          existenceList.add(newEdges[1]);
        }
      }

      // if closing path, do nothing
      if (closingPath) {
        continue;
      }

      switch (existenceList.size()) {
        // no data
        case 0:
          existingTreeList.add(new ArrayList<>(Arrays.asList(newEdges)));
          cost += Solvable.PAY + entry.getValue();
          break;

        // one data
        case 1:
          int newEdge = existenceList.contains(newEdges[0]) ? newEdges[1] : newEdges[0];
          existingTreeList.get(existingTreeIndex).add(newEdge);
          cost += entry.getValue();
          break;
      }
    }

    return cost;
  }

}
