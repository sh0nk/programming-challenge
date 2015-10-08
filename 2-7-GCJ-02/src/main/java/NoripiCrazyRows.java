import java.util.Arrays;
import java.util.stream.Collectors;

public class NoripiCrazyRows implements CrazyRows {

  @Override
  public int solve(int N, int[][] V) {
    int[] largestIndex = new int[V.length];

    // original rows
    int max = 0;
    for (int i = 0; i < V.length; i++) {
      largestIndex[i] = Arrays.stream(V[i]).boxed().collect(Collectors.toList()).lastIndexOf(1);
      max = Math.max(max, largestIndex[i]);
    }

    int count = 0;
    int idx = -1;
    for (int i = 0; i < largestIndex.length; i++) {
      // search nearest and small one
      for (int j = i;; j++) {
        if (largestIndex[j] <= i) {
          idx = j;
          break;
        }
      }

      // swap
      int orig = largestIndex[idx];
      for (int j = idx; j >= i + 1; j--) {
        largestIndex[j] = largestIndex[j - 1];
        count++;
      }
      largestIndex[i] = orig;
    }

    return count;

  }

}
