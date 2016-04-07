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
    System.out.println(map);

    int[] array = new int[a[n - 1] - a[0] + 1];
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

    // ここまでで、
    // { 2, 2, 3, 4, 4, 5, 5 } これを
    // {2=2, 3=1, 4=2, 5=2} こうして
    // [2, 1, 2, 2] この配列を作成

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
          // とりあえず、最上位から走査して、kに満たない項を下にマージする
          if (i - 1 < 0) {
            flag = true;
            break;
          }
          array[i] = 0;
          array[i - 1] = first + array[i - 1];
          global_cnt += first;
          System.out.println(Arrays.toString(array) + ":" + global_cnt);
          break;
        } else if (flag && first > k) {
          // 最後まで走査してなおkに満たさない項が残った場合、最上位から再度走査して、既に確定済みで、kより大きいのを下に下げる
          // TODO: first > k だと一度k個確定したものを下に流さないが、これで通らないテストがありそう
          array[i] = array[i] - 1;
          array[i - 1] = array[i - 1] + 1;
          global_cnt++;
          System.out.println(Arrays.toString(array) + ":" + global_cnt);
          flag = false;
        }
        // TODO: 多分他にも条件が必要、上位のどこを下に流すかで、再帰的に走査しないといけなさそう
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
