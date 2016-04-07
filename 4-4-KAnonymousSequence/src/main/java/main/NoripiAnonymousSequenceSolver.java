package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class NoripiAnonymousSequenceSolver implements IAnonymousSequenceSolver {
  @Override
  public int solve(int n, int k, int[] a) {
    System.out.println(Arrays.toString(a));

    int totalStep = 0;

    // i番目を含む長さkの数列
    int pivot = 0;

    while (pivot <= a.length) {
      Map<Integer, Integer[]> splitArrays = this.getSplitArrays(pivot, a, k);

      // 一番小さい選択肢を選ぶ
      System.out.println(splitArrays.values().stream().map(arr -> Arrays.asList(arr))
          .collect(Collectors.toList()));
      int choicedIndex = -1;
      int minStep = Integer.MAX_VALUE;
      for (Integer key : splitArrays.keySet()) {
        int currentStep = this.calcStep(splitArrays.get(key));
        if (minStep > currentStep) {
          choicedIndex = key;
          minStep = currentStep;
        }
      }

      // 値を更新
      int offset = choicedIndex;
      for (int i = 0; i < splitArrays.get(choicedIndex).length; i++) {
        a[i + offset] = splitArrays.get(choicedIndex)[0];
      }
      totalStep += minStep;

      System.out.println("current:" + Arrays.toString(a));
      System.out.println("step:" + totalStep);

      pivot++;
    }

    return totalStep;
  }

  private int calcStep(Integer[] nums) {
    int num0 = nums[0];
    int step = 0;
    for (int i = 1; i < nums.length; i++) {
      step += nums[i] - num0;
    }

    return step;
  }

  private Map<Integer, Integer[]> getSplitArrays(int step, int[] a, int k) {
    Map<Integer, Integer[]> splitArrays = new HashMap<>();
    for (int i = step; i <= step + k; i++) {
      if (i - k < 0) {
        continue;
      }

      if (i > a.length) {
        continue;
      }

      Integer[] splitArray = new Integer[k];
      for (int j = 0; j < k; j++) {
        splitArray[j] = a[j + i - k];
      }

      splitArrays.put(i - k, splitArray);
    }

    return splitArrays;
  }

}
