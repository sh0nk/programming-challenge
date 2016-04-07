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
    // System.out.println(map);

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

    int ans = loop(list, k);
    System.out.println(ans);

    return ans;
  }

  private int loop(List<Integer> list, int k) {
    int global_cnt = 0;
    boolean flag = false;

    System.out.println("k = " + k);
    System.out.println(list + ":" + global_cnt);

    while (!check(list, k)) {
      for (int i = list.size() - 1; i >= 0; i--) {
        int first = list.get(i);
        if (!flag && first < k && first > 0) {
          if (i - 1 < 0) {
            flag = true;
            break;
          }
          list.remove(i);
          int second = list.remove(i - 1);
          list.add(i - 1, 0);
          list.add(i - 1, first + second);
          global_cnt += first;
          System.out.println(list + ":" + global_cnt);
          break;
        } else if (flag && first > 0) {
          list.remove(i);
          int second = list.remove(i - 1);
          list.add(i - 1, first - 1);
          list.add(i - 1, second + 1);
          global_cnt++;
          System.out.println(list + ":" + global_cnt);
          flag = false;
        }
      }
    }
    return global_cnt;
  }

  private boolean check(List<Integer> list, int k) {
    for (int i : list) {
      if (i == 0)
        continue;
      if (i < k)
        return false;
    }
    return true;
  }

}
