import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class NoripiJessicasReadingProblem implements JessicasReadingProblemI {

  @Override
  public int solve(int P, int[] a) {
    Map<Integer, Integer> existCount = new HashMap<>();
    existCount = Arrays.stream(a).boxed().distinct().collect(Collectors.toMap(i -> i, i -> 0));

    int startIndex = 0;
    int length = P;

    int pageKindLength = existCount.keySet().size();
    for (int i = 0; i < a.length; i++) {
      existCount.put(a[i], existCount.get(a[i]) + 1);

      // decrease value as much as possible
      while (existCount.values().stream().filter(c -> c > 0).count() == pageKindLength) {
        existCount.put(a[startIndex], existCount.get(a[startIndex]) - 1);
        startIndex++;

        if (existCount.values().stream().filter(c -> c > 0).count() == pageKindLength) {
          length = Math.min(length, i - startIndex + 1);
        }
      }
    }

    return length;
  }

}
