package main;

import java.util.Arrays;
import java.util.HashMap;
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

    int[] array = new int[map.size()];
    int prev = 0;
    int key = 0;
    int cnt = 0;
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      key = entry.getKey();
      if (prev != 0) {
        for (int i = 1; i < key - prev; i++) {
          array[cnt] = 0;
          cnt++;
        }
      }
      array[cnt] = entry.getValue();
      cnt++;
      prev = key;
    }

    int ans = loop(array, k);
    System.out.println(ans);
    return ans;
  }

  // main
  private int loop(int[] array, int k) {

    int global_cnt = 0;
    boolean flag = false;

    System.out.println("k = " + k);
    System.out.println(Arrays.toString(array) + ":" + global_cnt);

    while (!check(array, k)) {
      for (int i = array.length - 1; i >= 0; i--) {
        int first = array[i];
        if (!flag && first < k && first > 0) {
          if (i - 1 < 0) {
            flag = true;
            break;
          }
          array[i] = 0;
          array[i - 1] = first + array[i - 1];
          global_cnt += first;
          System.out.println(Arrays.toString(array) + ":" + global_cnt);
          break;
        } else if (flag && first > 0) {
          array[i] = array[i] - 1;
          array[i - 1] = array[i - 1] + 1;
          global_cnt++;
          System.out.println(Arrays.toString(array) + ":" + global_cnt);
          flag = false;
        }
      }
    }
    return global_cnt;
  }

  // check if everything is fine
  private boolean check(int[] array, int k) {
    for (int i : array) {
      if (i == 0)
        continue;
      if (i < k)
        return false;
    }
    return true;
  }

}
