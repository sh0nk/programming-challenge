package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KenjiAnonymousSequenceSolver implements IAnonymousSequenceSolver {

  @Override
  public int solve(int n, int k, int[] a) {

    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    for (int i = 0; i < n; i++) {
      int num = a[i];
      Integer cnt = map.get(num);
      if (cnt == null) {
        map.put(num, 1);
      } else {
        map.put(num, cnt + 1);
      }
    }
    List<Integer> list = new ArrayList<Integer>();
    int prev = 0;
    int key = 0;
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      key = entry.getKey();
      if (prev != 0) {
        for (int i = 1; i < key - prev; i++) {
          list.add(0);
        }
      }
      list.add(entry.getValue());
      prev = key;
    }
    // Collections.reverse(list);

    System.out.println(map);
    System.out.println(list);
    
    int ans = loop(list, k);
    System.out.println(ans);

    return ans;
  }

  private int loop(List<Integer> list, int k) {
    int global_cnt = 0;

    while (!check(list, k)) {
      for (int i = list.size() - 1; i > 0; i--) {
        int first = list.get(i);
        if (first < k) {
          list.remove(i);
          int second = list.remove(i - 1);
          list.add(first + second);
          global_cnt += first;
          System.out.println(list);
          break;
        }
      }
    }
    return global_cnt;
  }

  private boolean check(List<Integer> list, int k) {
    for (int i : list) {
      if (i < k) {
        return false;
      }
    }
    return true;
  }

}
