import java.util.Arrays;

public class NoripiMSP implements MSP {

  @Override
  public int solve(int[] v1, int[] v2) {
    Arrays.sort(v1);
    Arrays.sort(v2);

    int result = 0;
    for (int i = 0; i < v1.length; i++) {
      result += v1[i] * v2[v2.length - 1 - i];
    }

    return result;
  }

}
